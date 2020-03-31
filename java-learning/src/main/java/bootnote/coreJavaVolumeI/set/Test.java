package bootnote.coreJavaVolumeI.set;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> intList = new LinkedList<>();
        intList.add(1);
        intList.add(2);

        Iterator<Integer> i1 = intList.iterator();
        Iterator<Integer> i2 = intList.iterator();

        i1.next();
        i1.remove();
        i2.next(); // 两个迭代器同时操作一个集合，后一个迭代器会抛出异常

        // 对于链表而言做了细小优化的get方法
        intList.get(2);
    }
}