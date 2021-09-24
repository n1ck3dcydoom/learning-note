package leetcode.linkedlist.medium;

public class _430_FlattenMultilevelDoublyLinkedList {

    public static void main(String[] args) {
        Node h1 = new Node(1);
        Node h2 = new Node(2);
        h1.next = h2;
        h2.prev = h1;
        Node h3 = new Node(3);
        h2.next = h3;
        h3.prev = h2;
        Node h4 = new Node(4);
        h3.next = h4;
        h4.prev = h3;
        Node h5 = new Node(5);
        h4.next = h5;
        h5.prev = h4;
        Node h6 = new Node(6);
        h5.next = h6;
        h6.prev = h5;

        Node h7 = new Node(7);
        Node h8 = new Node(8);
        h7.next = h8;
        h8.prev = h7;
        Node h9 = new Node(9);
        h8.next = h9;
        h9.prev = h8;
        Node h10 = new Node(10);
        h9.next = h10;
        h10.prev = h9;

        h3.child = h7;

        Node h11 = new Node(11);
        Node h12 = new Node(12);
        h11.next = h12;
        h12.prev = h11;

        h8.child = h11;

        Node head = flatten(h1);
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("\n");

        Node t1 = new Node(1);
        Node t2 = new Node(2);
        Node t3 = new Node(3);
        t1.child = t2;
        t2.child = t3;
        Node tt1 = flatten(t1);
        while (tt1 != null) {
            System.out.print(tt1.val + " ");
            tt1 = tt1.next;
        }
        System.out.println("\n");
    }

    public static Node flatten(Node head) {
        // 明确函数的作用
        // flatten(Node head)
        // 接收一个Node head节点，返回一个将head所有子结构全部扁平化后的新头节点Node res

        // 如果head存在如下
        // head(x).child != null
        // 那么函数flatten(head(x).child)将返回一个把head(x).child扁平化后的新头节点Node childRes

        // 有点递归的样子

        if (head == null) {
            return head;
        }
        // 保存头节点
        Node res = head;
        // 遍历整条链表
        while (head != null) {
            // 如果当前节点没有子结点，则直接后移head
            if (head.child == null) {
                head = head.next;
            } else {
                // 否则递归拿到当前节点子结点扁平化后返回的新链表头child
                Node child = flatten(head.child);
                // 保存head的下一个节点
                Node next = head.next;
                // 建立当前head和其子节点扁平化后返回的新链表child的关系
                head.next = child;
                child.prev = head;
                // 断开当前head的子结点关系
                head.child = null;
                // 找到子结点的最后一个节点
                while (child.next != null) {
                    child = child.next;
                }
                // 把子结点的最后一个节点指向之前保存的head的下一个节点
                child.next = next;
                // 如果head不是最后一个节点，next!=null，需要把child的最后一个节点指向head的下一个节点
                if (next != null) {
                    next.prev = child;
                }
                // 移动head到head的下一个节点
                head = next;
            }
        }
        return res;
    }
}

class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {
    }

    public Node(int val) {
        this.val = val;
        this.prev = null;
        this.next = null;
        this.child = null;
    }
}