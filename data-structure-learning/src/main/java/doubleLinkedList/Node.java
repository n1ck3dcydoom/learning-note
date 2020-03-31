package doubleLinkedList;

/**
 * Created by n!Ck
 * Date: 2019-01-18
 * Time: 16:11
 * Description:
 */
public class Node<T> {
    /**
     * 指向下一个节点的指针
     */
    private Node<T> next;

    /**
     * 指向上一个节点的指针
     */
    private Node<T> pre;

    /**
     * 数据域
     */
    private T t;

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPre() {
        return pre;
    }

    public void setPre(Node<T> pre) {
        this.pre = pre;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
