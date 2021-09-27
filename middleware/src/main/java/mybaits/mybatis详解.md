### 基本架构图
![](./pic/structure/20210328141914678.png)

1、`mybatis` 所有的 `slq` 执行，都是基于 `Statement` 的

### 划分核心源码

`mybatis` 的核心源码，基本上可以划分为 **解析配置信息** 和 **执行SQL** 两大类

### 解析配置信息

```java
// 将XML配置文件构建为Configuration配置类
reader = Resources.getResourceAsReader(resource);
// 通过加载配置文件流构建一个SqlSessionFactory  DefaultSqlSessionFactory
SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);
```

其中 `getResourceAsReader` 主要获得配置文件的文件流，解析操作还是放在下面的 `build()` 方法中完成的。

跟入 `build()` 方法，继续跟入其包装方法，到最后发现本质上是创建了一个 `DefaultReflectorFactory` 对象，并且将从配置文件中获取的所有内容全部注册到这个 `Factory` 对象当中
![](./pic/config/Snipaste_2021-09-24_00-20-11.png)
![](./pic/config/Snipaste_2021-09-24_00-22-44.png)
![](./pic/config/Snipaste_2021-09-24_00-23-22.png)

其注入的配置类 `Configuration` 的具体内容，几乎包含了 `mybatis-config.xml` 配置文件中的所有配置项
![](./pic/config/Snipaste_2021-09-24_00-26-26.png)

回到上面 `build()` 方法的包装方法当中，这个配置文件的内容全部是由上面的 `XMLConfigBuilder.parser()` 方法得到

其中把从 `classpath` 加载到的配置文件转化成 `XPathParser` 对象，然后调用 `parseConfiguration()` 方法，挨个解析 `mybatis-config.xml` 配置文件里面的各个标签内容
![](./pic/config/Snipaste_2021-09-24_00-30-37.png)

看一个常用的 `properties` 标签如何解析的
![](./pic/config/Snipaste_2021-09-24_00-39-24.png)
![](./pic/config/Snipaste_2021-09-24_00-41-40.png)

关于事务工厂和数据源对象的创建
![](./pic/config/Snipaste_2021-09-24_00-49-10.png)

所有mapper的初始化解析和注册 (即我们代码里面的mapper类、或者mapper.xml)
![](./pic/config/Snipaste_2021-09-24_17-07-10.png)

跟进 `mapperParser.parse()` 方法，看看具体是怎么绑定 `mapper` 和生成对应的 `sql` 操作包装对象的
![](./pic/config/Snipaste_2021-09-24_17-39-16.png)

跟进 `configurationElement()` 方法
![](./pic/config/Snipaste_2021-09-24_17-41-11.png)

跟进 `buildStatmentFromContext()` 方法里面去，一路顺藤摸瓜，看到下面这个方法，看看具体的 `MapperStatement` 对象是怎么被绑定到 `sql` 语句上面
![](./pic/config/Snipaste_2021-09-24_18-04-46.png)
![](./pic/config/Snipaste_2021-09-27_22-04-30.png)

### 获取SqlSession
跟进 `SqlSessionFactory.openSession()` 方法
![](./pic/sqlsession/Snipaste_2021-09-27_22-41-12.png)

事务的隔离级别配置
![](./pic/sqlsession/Snipaste_2021-09-27_22-44-44.png)

可以看到 `mybaits` 是实现了两种不同的事务管理机制
![](./pic/sqlsession/Snipaste_2021-09-27_22-42-35.png)

* 使用 `JDBC Connection对象` 管理事务，里面包含完整的使用 `connection` 对象进行事务的提交、回滚和关闭
* 使用 `ManagedTransaction` 管理事务，里面的提交和回滚不会做任何事，而是交给运行容器来管理事务的具体执行

根据全局配置对象，和之前创建的事务对象，和执行器类型，初始化具体的 `SQL` 执行器，这里使用了 **装饰器设计模式**
![](./pic/sqlsession/Snipaste_2021-09-27_23-18-09.png)

最后将使用每一个拦截器 `interceptor` 来重新包装执行器，并返回最终的结果
```java
return (Executor) interceptorChain.pluginAll(executor)
```

### 获取mapper