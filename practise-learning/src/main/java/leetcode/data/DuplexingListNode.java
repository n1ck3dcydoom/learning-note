package leetcode.data;

public class DuplexingListNode {

    public DuplexingListNode next;
    public DuplexingListNode pre;
    public int val;

    public DuplexingListNode() {
    }

    public DuplexingListNode(int val) {
        this.next = null;
        this.pre = null;
        this.val = val;
    }
}
