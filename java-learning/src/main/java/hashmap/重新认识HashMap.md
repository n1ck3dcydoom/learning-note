几个 `HashMap` 的关键字段

```
    int initialCapacity; //  默认值1<<4  aka 16
    int modCount;
    int size;
    float loadFactory; // 默认值0.75
```

默认的无参构造函数、带有指定大小的构造函数、带有指定负载因子的构造函数，均是初始化这几个关键字段。

