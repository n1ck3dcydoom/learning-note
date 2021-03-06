###1 Echo服务器
多个客户端同时连接到一台服务器，在建立连接之后，客户端会向echo服务器发送一个或多个消息，同时echo服务器也会将每个收到的消息返回给客户端

###2 基本组件
每个Netty服务器 **至少** 需要以下两个部分
1. 至少一个 `ChannelHandler` 这个组件实现了服务器对客户端接受的数据的处理，即 **业务逻辑**
2. 引导————这是配置服务器的启动代码。这里实现最简单的功能：它会将服务器绑定到所需要监听请求连接的端口上

###3 入站事件响应方法
由于服务器需要相应传入的消息，需要实现 `ChannelInboundHandler` 接口，由于功能简单，直接继承其默认的实现类 `ChannelInboundHandlerAdapter` 即可

* `channelRead()` — 对于每个传入的消息都要调用
* `channelReadComplete()` — 通知 `ChannelInboundHandler` 最后一次对 `channelRead()` 的调用是当前批量读取中的最后一条消息
* `exceptionCaught()` — 在读取操作期间，有异常抛出时会调用

###4 其他ChannelHandler的实现类和子类
需要注意以下关键点
* 针对不同类型的事件来调用 `ChannelHandler`
* 应用程序通过实现或者扩展 `ChannelHandler` 来挂钩到事件的生命周期，并且提供自定义的应用程序逻辑(自己的业务逻辑)
* 在架构上，`ChannelHandler` 有助于保持业务逻辑与网络处理代码的分离。这简化了开发过程，因为代码必须不断地演化以响应不断变化的需求

###5 引导服务器
上述4点简述了一个Echo服务器所应该实现的核心业务逻辑，接下来需要实现第二点————引导:

* 绑定到服务器将在其上监听并接受传入连接请求的端口；
* 配置 `Channel` ，以将有关的入站消息通知给 `EchoServerHandler` 实例。

**传输：Java NIO传输大多时候就是指的TCP传输**