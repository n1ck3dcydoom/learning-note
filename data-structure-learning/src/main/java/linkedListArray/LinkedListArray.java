package linkedListArray;

import exception.ArrayIndexException;

/**
 * Created by n!Ck
 * Date: 2019-01-13
 * Time: 16:49
 * <p>
 * Description:
 * jdk1.8
 * IDEA 2018.3.2
 * MacOSX 10.13.6
 * <p>
 * init方法头插法创建链表存在内存溢出问题，暂未找到解决办法
 */

@Deprecated
public class LinkedListArray<T> {

    private static final String INDEXOUTOFARRAY = "位置i下标越界";

    /**
     * 头结点
     */
    private Node<T> head;

    /**
     * 无参构造函数，使得链表的头结点指针域为null，数据域为0
     */
    public LinkedListArray() {
        this.head = new Node<>();
        this.head.setCount(0);
    }

    /**
     * 使用数组初始化一个单链表
     *
     * @param ts     初始化数组
     * @param length 数组长度=结点个数
     */
    public void init(T[] ts, int length) throws ArrayIndexException {
        // 创建头结点
        this.head = new Node<>();
        this.head.setCount(length);
        this.head.setNext(null);

        // 使用头插法创建单链表，初始化数组倒序遍历
        for (int i = length - 1; i >= 0; i--) {
            Node<T> node = new Node<>(ts[i]);
            node.setNext(head.getNext());
            head.setNext(node);
        }
    }

    /**
     * 获取链表第i个元素，由于链表不支持随机访问，必须从头结点开始遍历i-1次
     *
     * @param i 位置i
     * @return T
     */
    public T getElem(int i) throws ArrayIndexException {
        if (i < 0 || i > head.getCount()) {
            throw new ArrayIndexException(INDEXOUTOFARRAY);
        }
        Node<T> p = head.getNext();
        while (i > 0) {
            p = p.getNext();
            i--;
        }
        return p.getT();
    }

    /**
     * 找到节点i的前驱节点
     *
     * @param i 位置i
     * @return Node<T>
     */
    private Node<T> getPreNode(int i) throws ArrayIndexException {
        if (i < 0 || i > head.getCount()) {
            throw new ArrayIndexException(INDEXOUTOFARRAY);
        }
        if (i == 0) {
            return head;
        }
        Node<T> p = head;
        while (i > 0) {
            p = p.getNext();
            i--;
        }
        return p;
    }

    /**
     * 在位置i插入结点
     *
     * @param pos 位置pos
     * @param s   待插入结点s
     * @return T
     * @throws ArrayIndexException 下标越界异常
     */
    public T insert(int pos, Node<T> s) throws ArrayIndexException {
        if (pos < 0 || pos > head.getCount()) {
            throw new ArrayIndexException(INDEXOUTOFARRAY);
        }
        // 获取pos的前驱结点q
        Node<T> q = getPreNode(pos);
        // 插入结点
        s.setNext(q.getNext());
        q.setNext(s);

        return s.getT();
    }

    /**
     * 删除位置i的结点
     *
     * @param pos 位置pos
     * @return T
     * @throws ArrayIndexException 下标越界异常
     */
    public T delete(int pos) throws ArrayIndexException {
        if (pos < 0 || pos > head.getCount()) {
            throw new ArrayIndexException(INDEXOUTOFARRAY);
        }
        // 获取pos的前驱结点q
        Node<T> q = getPreNode(pos);
        // 待删除的结点
        Node<T> p = q.getNext();
        // 获得待删除结点
        q.setNext(p.getNext());
        // 保存p的数据域
        T t = p.getT();
        // 删除结点
        p = null;
        return t;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[ linkedListArray : ");
        Node<T> temp = head;
        // 遍历单链表
        while (temp.getNext() != null) {
            stringBuilder.append(temp.getNext().getT()).append(" ");
        }
        return stringBuilder.toString();
    }
}
