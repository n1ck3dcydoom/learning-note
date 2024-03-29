## 什么是充血模型,什么是贫血模型

### MVC 典型的贫血模型

传统的 MVC 架构,Controller 层包含(DTO 或者 View Object),Service 层包含 BO 对象,Dao 层或者 Repository 层包含 Entity 对象

其中 Controller 层负责对外暴露接口,并且接收和返回 DTO 对象给调用方

会将接收到的 DTO 转化为 Service 层所需要 BO,也会将 Service 层返回的 BO 转化为 DTO 返回给调用方

同理,Service 层负责各种复杂的业务逻辑,接收和返回 BO 给 Controller 层

如果有访问持久层的操作,则将 BO 转化为 Dao 层的 Entity 对象,也将 Dao 层返回的 Entity 对象转化为 BO 返回给 Controller 层

同理,Dao 层负责纯粹的持久化操作,接收和返回 Entity 给 Service 层

其中每一层划分的很清楚: 只和自己层的 Object 对象打交道,对于上下其它层的 Object 对象,仅仅做转化处理,不做任何业务处理

可以得到以下结论:
1. DTO,VO,BO,Entity 都是纯粹的数据结构,不包含任何业务逻辑
2. 各个层的 Service 类仅包含当前层的所有业务,且与之打交道的数据都是当前层自己的 Object 对象

这种完全把数据和业务逻辑分离的模型,叫做 **贫血模型**,而贫血模型完全破坏了面向对象的特性,是一种典型的面向过程风格

### 如何说贫血模型破坏了 oop 的特性

贫血模型破坏了数据的封装,因为其数据和算法(业务逻辑)完全分离,其 Object 类完全无法保护自身数据的隐私

这使得每个 Service 类都可以操作和修改当前层的 Object 对象,破坏了 Object 类中数据的访问隔离

### 什么是基于充血模型的 DDD 开发模式

充血模型和贫血模型完全相反,其 **数据** 和 **算法** 放在一个类中,或者一个结构体当中

因此访问当前数据结构的算法,一定是和数据处于同一个类或者结构体当中

在外部看来这个类是透明的,类里面的算法对数据做了什么操作都属于类的封装之下

DDD 的实践并不是说对 DDD 这套理论掌握的多么充分,而是基于 **对当前业务模型的熟悉程度,只有对业务充分熟悉和了解,才能做到合理的领域拆分**

### 为什么 MVC 的贫血模型会被大家接受

1. 日常的开发 80% 的工作都是简单的 CRUD,不必要花费大量心血做领域拆分,简单的几个 Service 类+上 VO,BO 等 POJO 的贫血模型就能满足开发需要
2. 简单的业务如果完全按照领域进行细致的拆分,势必导致最终的各个领域显得十分单薄,与贫血模型相差无几

贫血模型简单的多

贫血模型只需要定义好纯粹的数据结构后,具体的代码业务都可以放到 Service 类中慢慢迭代

只要基本数据结构不发生变化,那么业务就可以尽情地添加到 Service 的各个方法当中

而充血模型就复杂多了,在将 **数据** 和 **算法** 封装到一块之后,首先需要考虑的就是应该暴露那些接口,提供哪些能力给外部

而这些接口如果设计不够抽象的话,后续的业务迭代很大可能会破坏已有的接口设计,甚至发现原来的接口不能满足新的需求,完全推倒重来实现一遍

这里面的成本,在没有遇到一定痛点的情况下,大多数人都不愿意轻易接受 DDD 的设计成本

### 什么情况下使用 DDD

一般简单,稳定的系统,使用贫血模型就能够很好的完成任务

而对于复杂的系统,例如计费,对账等金融系统;其变化多,迭代快,就更加适合 DDD 的领域开发模式

平时的开发当中,几乎都是 SQL 驱动的业务开发; 各种需求其本质就在于,需要查询那几张表? 组合哪些字段? 使用哪些数据计算?

经常会出现一条大 SQL 完成很多 Service 应该做的业务处理,而 Service 层就简单的做一点将复杂 Entity 对象解析一下的工作

如果需求调整一小点,但又不是完全独立,很有可能就会编写出另一个满足业务的大 SQL(因为上一个 SQL 包含了太多之前的业务,完全无法复用)

这样慢慢导致整个系统中充满了大量 **相似** 但是又 **不能服用** 长得差不多的大 SQL 语句,最终导致系统完全无法维护

如果使用 DDD 领域驱动设计,一开始就设计好某类领域包含的数据和提供的能力,由于拆分粒度细,领域划分纯粹,那么领域内部的算法就完全可以复用

例如一个车库有行人通道,车辆通道,还有停车,缴费等能力

以前的贫血模型,一条 SQL 就将进入车库,停车,缴费,离开车库等所有事完成

加入有一天引入了停车优惠,那么原来的 SQL 完全不能复用,因为其不包含优惠的 SQL,所以一条新的包含 进入,停车,优惠,缴费,离开的大 SQL 随之诞生

慢慢地,车库的能力越来越多,越来越多和业务绑定的 SQL 不断产生,最后整个系统不得不停止迭代进行重构

假如使用 DDD 一开始就对车库进行领域划分:

闸机:负责车辆和行人的通行

收费机:负责停车费的收取

车位:负责提供停车的场所

那么一次完整的流程就是按照某个顺序组合访问车库的不同领域,而每个领域之间是完全独立的

闸机就完全不需要考虑停车费有没有优惠,因为他只负责进入和离开

这样引入新的业务,仅仅是考虑对已有领域的升级,或者添加一个全新的领域; 对于其他的领域不会带来任何影响

