## 更新sql也会把查询sql的流程都执行一遍

连接器 -> 分析器 -> 优化器 -> 执行器 -> 存储引擎

过程中多了两个重要的日志模块 **重做日志（redo log）** 和 **归档日志（bin log）**

只要是DML语句（增updat 删delete 改update）就涉及到这两个日志

DDL语句（建表create 删表drop 修改alter 截断truncate 重命名rename）





### Redo log 重做日志

每次需要修改db的数据时

InnoDB先把对应的数据从磁盘当中读取到内存的缓冲区里进行修改

当内存的修改完成后，和磁盘中的原始数据就存在了差异

InnoDB不会立即刷新这个差异去更新磁盘，这样会产生大量的IO操作，严重影响InnoDB的性能 



**前置基础**

#### Mysql的日志类型

1. 物理日志（存储数据被修改的值）redo log
2. 逻辑日志（存储逻辑SQL修改语句）bin log

物理日志的恢复速度远快于逻辑日志

MySQL有个关键技术：**写前日志**，即**先写日志，再操作数据**，区别于Redis的AOF写后日志（先操作数据，再写日志）

当数据更新时，InnoDB先将记录写入redo log中（redo log也在磁盘上，但是时顺序IO追加写，效率较高）

在引擎比较空闲时，再将redo log中的内容写入磁盘（这里是随机IO，因为每条记录在磁盘上的位置都是随机的）



redo log是循环写的，如果redo log写满了都还没来得及写入磁盘持久化。此时InnoDB会把最老部分的redo log先写入磁盘当中，再把当前内存缓冲区里面修改的记录写入redo log覆盖写

![image-20220307230836928](D:\learning-note\middleware\src\main\java\mysql\极客时间\pic\image-20220307230836928.png)

* write_pos：表示当前写入记录的位置
* check_point：表示从redo log中写入磁盘的位置

write pos和check point之间的为空闲空间

如果wpos和cpos相遇了，则说明redo log被写满，此时不能再执行新的更新操作，需要将部分redo log中的记录写入磁盘，推进cpos前进，以便空出空间来

有了redo log，InnoDB可以保证数据库发生异常重启，也可以通过之前记录的redo log来恢复之前提交过的记录



### Bin log 归档日志

所处位置的区别：

* redo log重做日志处于引擎层，且是InnoDB独有的
* bin log归档日志处于Server层，所有引擎都可以使用

日志类型的区别：

* redo log是物理日志，记录**在某个数据上做了什么修改**
* bin log是逻辑日志，记录**给ID=2的记录的count字段+1**



##### Update的内部流程

1. 执行器从引擎中获取 ID=2 的行，ID是逐渐，引擎直接通过主键索引找到这一行

如果ID=2**所在的内存页**已经在内存当中，引擎直接返回到执行器；否则需要从磁盘中先取出内存页，再返回给执行器

什么叫**所在内存页**？

引擎不会把某条记录放到内存里做修改，而是把以 **Page（页）** 为单位，把数据所在的 **内存页** 全部加载到内存

2. 执行器获得引擎提供的对应数据，对这个值做计算操作（count+1），然后调用引擎的接口把计算的结果写回
3. 引擎将这个新数据更新到内存，同时记录这个更新操作到 **redo log（在ID=2的记录上列count+1）**，此时redo log处于 **prepare** 预备状态。然后告知执行器执行完成，可以提交事务
4. 执行器生成当前操作的bin log，并把bin log写入磁盘
5. 执行器调用引擎的提交事务接口，引擎把刚刚写入的redo log改成 **提交（commit）** 状态，完成数据更新

![image-20220307232415464](D:\learning-note\middleware\src\main\java\mysql\极客时间\pic\image-20220307232415464.png)

最后3步将redo log拆成了两个阶段

* prepare
* commit

即 **两阶段提交**



### 两阶段提交

为什么要redo log的两阶段提交？

**为了保证两份日志之间的逻辑一致性**

##### 1 先写redo log 后写 binlgo

假设redo log写完，但是bin log没有写完，此时mysql服务挂掉

有redo log的存在，仍然可以将ID=2的count恢复到1

由于bin log里面没有这条记录，后面通过bin log备份日志的时候，bin log里面就没有count+1的语句

这样通过bin log恢复的临时表，跟原始表的count=0产生冲突

##### 2 先写bin log 后写redo log

如果bin log写完后，mysql挂掉，由于redo log还没有写，恢复的时候这个事务无效，恢复后count仍然等于0

由于bin log里面已经有count+1的记录了，所以使用bin log恢复临时表之后，就多了一个count+1的事务，与原始表的count=0产生冲突



##### 两阶段提交如何保证两个事务的逻辑一致？

真正的两阶段提交，其实是对 redo log 的两阶段提交，**先prepare，在commit**

当mysql crash需要重启恢复时：

1. 先检查redo log当中是否有commit，如果有，则当前事务有效，可以恢复
2. 如果redo log仅仅有prepare，则检查对应的bin log是否有记录，如果bin log有，则认为redo log的事务有效
3. 如果bin log里面没有，则认为redo log里面的prepare事务无效，不恢复













