package leetcode.linkedlist.easy;

import leetcode.data.ListNode;

public class _237_DeleteNodeInLinkedList {

    public static void main(String[] args) {

        ListNode l1=new ListNode(1);
        l1.next=new ListNode(2);
        l1.next.next=new ListNode(3);
        l1.next.next.next=new ListNode(4);

        deleteNode(l1.next);

        while (l1!=null)
        {
            System.out.print(l1.val+" ");
            l1=l1.next;
        }
    }

    public static void deleteNode(ListNode node) {
        // 1 -> 2 -> 3 -> 4
        // 删除2的话，只需要把下一个节点3的值赋给当前节点
        // 然后删除下一个节点3即可
        // 1 -> 3 -> 3 -> 4
        // 1 -> 3 -> 4

        // 下一个节点的值赋给当前
        node.val = node.next.val;
        // 删除下一个节点
        node.next = node.next.next;
    }
}