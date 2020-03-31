package doubleLinkedList;

/**
 * Created by n!Ck
 * Date: 2019-01-18
 * Time: 16:14
 * Description:
 */
public class DoubleLinkedList<T> {

    /**
     * 双向链表的头结点
     */
    private Node<T> head;

    /**
     * 双向链表很多操作和单项链表相同，只是提供了反向遍历链表的功能，但是需要修改两个指针域
     * <p>
     * 这里主要实现双向链表的插入和删除操作
     */

    /**
     * 双向链表插入结点
     * <p>
     * 1. 先找到待插入节点位置的前驱结点p
     * 2. 主要顺序是先操作待插入结点s，完成s的前驱和后继指针域的赋值
     * 3. 再操作p -> next结点
     *
     * @param s 待插入结点s
     * @return T
     */
    private T insert(int pos, Node<T> s) {
        // 先找到待插入位置pos的前驱结点p
        Node<T> p = new Node<>();

        // 1. 把p赋值给s的前驱
        s.setPre(p);
        // 2. 把p -> next赋值给s的后继
        s.setNext(p.getNext());
        // 3. 把s赋值给p -> next -> pre（即s的后继）
        p.getNext().setPre(s);
        // 4. 把s赋值给p -> next
        p.setNext(s);

        return s.getT();
    }

    /**
     * 双向链表删除结点
     */

    private T delete(int pos) {
        // 先找到待删除位置pos的结点p
        //（注意：这里不用找p的前驱结点，所以在寻找前驱结点方法返回后需要把next赋值给p
        Node<T> p = new Node<>();

        // 1. 把 p -> next 赋值给 p -> pre 的后继
        p.getPre().setNext(p.getNext());
        // 2. 把 p -> pre 赋值给 p -> next 的前驱
        p.getNext().setPre(p.getPre());

        return p.getT();
    }
}
