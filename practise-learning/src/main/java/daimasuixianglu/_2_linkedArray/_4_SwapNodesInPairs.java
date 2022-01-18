package daimasuixianglu._2_linkedArray;

import leetcode.data.ListNode;

public class _4_SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode prev = dummyHead;
        // 每次交换prev.next和prev.next.next
        // 如果prev.next或者prev.next.next为null，则表示没有更多节点需要交换
        while (prev.next != null && prev.next.next != null) {
            // 保存当前节点的交换节点
            ListNode n1 = prev.next;
            ListNode n2 = prev.next.next;
            // 交换前
            // prev -> n1 -> n2
            // 交换后
            // prev -> n2 -> n1
            // 更新prev=n1，继续交换prev后面的两个节点prev.next和prev.next.next

            // prev -> n2
            prev.next = n2;
            // n1 -> n3(n2.next)
            n1.next = n2.next;
            // n2 -> n1
            n2.next = n1;
            // prev = n1
            prev = n1;
        }
        return dummyHead.next;
    }
}
