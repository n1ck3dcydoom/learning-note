## epoll的本质

1. 从硬件层面来说，**网卡会把接收到的数据写入内存**
2. 从cpu层面来说

中断：由硬件产生的中断信号需要cpu立马做出响应。中断和函数调用很类似，都是进入另一段程序内存空间。

只不过触发条件不同：

* 函数调用由程序决定，存在于程序的固定位置，是事先就知道的
* 中断的产生是由“中断信号”决定的，没法事先预知

**网卡向cpu发出一个中断信号，这样操作系统就能得知由新数据到来，再交由网卡的中断程序取处理数据**

3. 从操作系统调度来说：数据接收的过程，有一个**阻塞**等待的操作，而 `recv, select, epoll` 都是阻塞方法

**阻塞是不占用cpu资源的** 为什么？

#### 工作队列

操作系统会把进程分为 “运行” “等待” 等等几种状态。

* 运行：进程获得cpu的使用权，执行代码
* 等待：进程阻塞中，等待cpu的调度

处于工作队列当中的进程，会有cpu做时间片的调度执行

#### 等待队列

当cpu执行代码遇到需要进入**等待**状态的代码块时，将会把当前进程放入等待队列，所以**处于等待队列的进程，是不会继续执行后续的代码的。所以阻塞并不会占用cpu资源**

每个socket创建出来后，都有自己的 `输入缓冲区，输出缓冲区，等待队列` ，等待队列里面保存了需要处理当前socket数据而阻塞的线程

![image-20220303162958025](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303162958025.png)

#### 唤醒进程

当**等待**的事件发生时，操作系统将处于等待队列中的进程重新放入工作队列，进程变为运行状态，继续执行代码，同时处理对应的事件

4. 内核接收网络数据的过程

![image-20220303161903672](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303161903672.png)

① 网卡接收网络数据，并且写入内存

② 网卡向cpu发出中断信号，通知cpu有新数据到来

③ cpu处理中断信号，执行中断程序；假设cpu此时正在执行进程A，那么会把进程A放入等待队列当中

④ 网卡的中断程序将接收到的数据，写入socket输入缓冲区当中

⑤ 中断程序重新唤醒被中断处于等待的进程A，将进程A放入工作队列，等待cpu时间片到来继续执行

![image-20220303162340837](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303162340837.png)

**操作系统如何知道网络数据对应哪个具体的socket？**

socket有端口号/ip等等数据，操作系统可以通过端口号/ip找到对应的socket

**操作系统如何监听多个socket的数据？**



### select的做法监听多个socket

准备一个socket列表，存放着所有需要监听的socket。调用select方法，如果列表里面的socket都没有数据，调用select函数的进程将会阻塞。直到有一个socket接收到数据，select函数返回唤醒进程。

如果进程A同时监听3个socket，那么在调用select进程之后，**操作系统** 将会把进程A分别加入3个socket的等待队列当中，进程A处于阻塞状态，直到select函数返回唤醒进程。

![image-20220303163123371](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303163123371.png)

假设socket2接收到了数据，并且产生了对应的**中断信号**

那么中断程序将会唤醒处于socket2等待队列里面的进程A，同时将进程A从所有等待队列里面移除，加入工作队列，等待cpu时间片的到来。

![image-20220303163328547](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303163328547.png)

此时，进程A被唤醒，知道了至少有一个socket里面有数据到来，那么进程A可以遍历所有的socket有，找到有数据socket进行处理。

**select的不足之处**：

* 每次调用select，都会遍历所有的socket的将进程加入到等待列表里面（1次遍历）
* 每次唤醒进程后，又需要遍历所有的socket得到需要处理的socket对象（2次遍历）

每次遍历都需要把完整的socket列表传递给内核，存在开销，所以select才会有默认只能监听最大1024个socket的限制

### epoll的设计思路

1. 步骤解耦：

select将 “维护等待队列” 和 “阻塞进程” 两个步骤放到一起

epoll先用ctl维护等待队列，再用wait阻塞进程

2. 就绪列表：

select在某个socket接收到数据后，只能遍历所有socket列表才能知道具体哪一个socket就绪了

而epoll维护了一个 “就绪列表”，当socket就绪后会被 “就绪列表” 引用，所以epoll只需要读取 “就绪列表” 就能知道哪些socket有数据需要处理了

![image-20220303164449353](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303164449353.png)



简单总结来说：

* select函数返回就绪socket的数量，但是不告诉你哪些socket就绪了

这样进程A在被select重新唤醒之后，还需要遍历所有的socket才能知道哪些socket就绪了

* epoll则在socket就绪后，将对应socket添加到就绪队列，然后返回到调用进程A

这样进程A被唤醒后，只需要遍历epoll维护的就绪队列就可以知道哪些socket就绪了



#### epoll的本质实现

1. 创建epoll对象

当进程A调用epoll_create函数时，内核回创建一个eventpoll对象，和socket一样，eventpoll也会有自己的等待队列。这个eventpoll里面就包含了 “就绪队列” 等关键成员

![image-20220303165404768](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303165404768.png)

2. 维护监听列表

进程A在创建epoll对象之后，使用epoll_ctl函数将epoll对象添加到需要监听的socket的等待队列里面**（将epoll对象，添加到，需要监听的socket对象等待队列里）**

![image-20220303165649488](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303165649488.png)

**区别于select**

**select将进程添加到socket的等待队列里面，而epoll将进程创建的epoll对象添加到socket的等待队列里面**

3. 接收数据

当socket接收到数据后，由“中断程序”将就绪的socket添加到epoll对象的 “就绪列表” 里面

![image-20220303165917118](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303165917118.png)

4. 阻塞和唤醒进程

当进程A执行到了epoll_wait函数，内核会把进程A放入epoll对象的等待队列里面，同时阻塞进程A

![image-20220303170814283](E:\learning-note\middleware\src\main\java\redis\极客时间\pic\image-20220303170814283.png)

当某几个socket接收到数据后，对应的中断程序就绪的socket写入就绪队列当中，然后唤醒处于epoll对象的等待队列中的所有进程，将其移出epoll对象的等待队列里面

当进程A被唤醒后，就可以直接通过epoll对象的就绪队列来得到所有已经就绪的socket对象

**需要注意的几个时间点和操作顺序：**

* 进程A调用epoll_create创建epoll对象
* 进程A调用epoll_ctl添加自己需要监听的socket到epoll对象的等待队列里面（此时进程A仍然处于运行状态）
* **中断程序** 负责将就绪的socket添加到epoll对象的等待队列里面，这一步是由内核操作的，而非由进程A操作
* 进程A调用epoll_wait进入阻塞状态直到有socket就绪，然后被中断程序唤醒



#### epoll具体实现

关注epoll对象的两个重要成员

##### 1. 就绪列表

由于就绪列表可能会频繁地被修改，所以它需要能够快速插入和删除数据

epoll使用 **双向链表** 维护就绪队列

##### 2. 监听socket的结构

维护监听socket的列表，意味着有一个能够快速添加、删除、查找（避免重复添加）的数据结构

epoll使用 **红黑树** 来维护socket的索引结构

其实在执行epoll_ctl添加socket到红黑树的时候，还会给当前socket注册一个回调函数，作用是如果这个socket就绪了，就调用这个回调函数来把socket放到就绪队列里面























































































