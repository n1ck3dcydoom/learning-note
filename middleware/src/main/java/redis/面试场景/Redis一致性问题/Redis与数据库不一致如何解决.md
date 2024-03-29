## 数据库与缓存不一致如何解决

1. 使用分布式事务（意义不大，提高了复杂度）仅仅适用于读多，写极少的情况



### 单机（缓存和磁盘）的一致性问题

对于程序来说，即使使用了flush指令，其数据也不一定会持久化到磁盘上

此时系统掉电，内核当中的缓存仍然也会丢实数据



### 缓存数据库双写一致性问题

**怎样定义一个请求得到的数据是准确的？**

一个请求查询，看到的数据，是数据库此时的数据状态吗？

**性能问题**

不通过硬件驱动的刷写，数据都是不可靠的，一旦掉电都有丢失数据的风险

如果使用了硬件驱动的刷写，性能将会急剧下降

**单机模式下，都没法保证内存 - 磁盘的强数据一致性**

在集群模式下更难实现



### 简单一致性解决办法和弊端

1. 如果读写操作尽量都发生在redis当中，此时的性能将是极高的；类似于单机模式下读写全部通过内存
2. 如果写完redis，再写db；已有的经验告诉我们，可能会发生数据不一致，可能有数据丢失

3. 如果先写db，再跟新缓存；

* 数据将会存在时差，再从数据库更新缓存的过程中，后续读缓存的请求可能读到仍然是老数据

* 如果客户端写完db后挂掉了没有更新到缓存里面去，后续所有请求都将永远得到缓存的老数据

解决数据时差 - 数据不一致：使用**Canal binlog**方式来解放客户端，客户端仅仅更新db，然后通过**Canal来将db的数据更新到缓存**

简单来说，Canal将自己**伪装成MySQL从节点**，模拟mysql的主从交互协议，向主节点发送dump请求

当主节点mysql接收到从节点的dump请求后，开始将自己的binlog推送给Canal，然后Canal开始解析binlog，再发送到目的存储例如kafka、es、redis等其他中间件

Canal是基于binlog**增量的订阅和消费**，在订阅主节点binlog时，指定binlog的偏移量，从此以后的binlog改动都可以被Canel监听到，再配合MQ消息队列，将这些增量的改动发送给MQ，最后MQ消费这些增量的数据来刷新缓存

##### 读写分离开

读操作直接打到redis当中；写操作通过mq来完全异步

![image-20220305132455630](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220305132455630.png)

客户端发送写请求到MQ当中，然后有专门负责消费写请求的服务来从MQ中取出消息，再由这些专门的服务来负责将写请求更新到缓存和db当中

简单来说就是实现客户端的读写分离

1. redis是缓存，更倾向于有时差的业务
2. 减少db的操作，即使在写的时候，先删缓存的数据再写入db；万一出现穿透、击穿、雪崩了呢
3. 真要项目落地，还是直接使用Canal







### 主从不一致的问题

### 

Redis默认是弱一致性的，主从同步默认是异步的

1. 如果要使用分布式锁，不能使用主从部署，使用单实例/分片集群/RedLock  --> 直接使用Redission客户端
2. 配置中提供多个client连接才能同步，配置redis的同步因子，趋向于强一致性
3. wait 2 5000  小心使用
4. 有点违背redis的设计初衷



#### 考虑常见的集群部署方案

1. 一主多从

* 主库提供写服务，从库提供读服务，主从之间同步数据

由于主从同步**有延迟**，这个时候从库的读请求可能获取到不一致的数据

##### 分布式锁的问题

当向主库写入一个分布式锁key时，正在往从库同步的过程中，主节点挂掉了导致同步失败

这是从节点完成主备切换升级到了主节点，这是另外的客户端发起加锁请求时也能成功

这就导致了**多个客户端同时持有了一个分布式锁**

这是主从部署的redis集群的最大缺陷

##### 如何避免主从延迟导致的数据不一致

1. 如果业务能够接收，忽略是最简单做法
2. RedLock分布式锁