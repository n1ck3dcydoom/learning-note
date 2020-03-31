package linkedListArray;

/**
 * Created by n!Ck
 * Date: 2019-01-13
 * Time: 16:44
 * Description:
 */

/**
 * 带有头结点的链表
 *
 * @param <T>
 */
public class Node<T> {
    /**
     * 指针域
     */
    private Node<T> next;

    /**
     * 数据域，对于头结点来说其数据域存储的是链表的节点个数（不包含头结点）
     * 也就是说如果一个链表只含有一个头结点，那么这个链表应该就是空链表
     */
    private T t;

    /**
     * 头结点数据域为链表结点个数
     */
    private int count;

    public Node() {
    }

    public Node(T t) {
        this.next = null;
        this.t = t;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
