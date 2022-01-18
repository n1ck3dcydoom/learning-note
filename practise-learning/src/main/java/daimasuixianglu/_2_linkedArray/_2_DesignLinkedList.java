package daimasuixianglu._2_linkedArray;

public class _2_DesignLinkedList {

    private int length;
    private Node head;

    public _2_DesignLinkedList() {

    }

    public int get(int index) {
        if (index >= length) {
            return -1;
        }
        Node p = head;
        int counter = 0;
        while (counter++ < index) {
            p = p.next;
        }
        return p.val;
    }

    public void addAtHead(int val) {
        // 头插
        Node dummyHead = new Node(-1);
        dummyHead.next = head;
        // 插入节点
        Node e = new Node(val);
        e.next = head;
        dummyHead.next = e;
        head = e;
        // 更新长度
        length++;
    }

    public void addAtTail(int val) {
        // 尾插
        if (head == null){
            head = new Node(val);
            length++;
            return;
        }
        // 找到最后一个节点
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        // 插入节点
        Node e = new Node(val);
        p.next = e;
        // 更新长度
        length++;
    }

    public void addAtIndex(int index, int val) {
        if (index > length) {
            return;
        }
        Node dummyHead = new Node(-1);
        dummyHead.next = head;
        Node prev = dummyHead;
        int counter = 0;
        while (counter++ < index) {
            head = head.next;
            prev = prev.next;
        }
        Node e = new Node(val);
        e.next = head;
        prev.next = e;
        head = dummyHead.next;
        length++;
    }

    public void deleteAtIndex(int index) {
        if (index >= length) {
            return;
        }
        int counter = 0;
        Node dummyHead = new Node(-1);
        dummyHead.next = head;
        Node prev = dummyHead;
        while (counter++ < index) {
            head = head.next;
            prev = prev.next;
        }
        prev.next = head.next;
        head = dummyHead.next;
        length--;
    }
}

class Node {
    public int val;
    public Node next;

    public Node(int val) {
        this.val = val;
        this.next = null;
    }
}
