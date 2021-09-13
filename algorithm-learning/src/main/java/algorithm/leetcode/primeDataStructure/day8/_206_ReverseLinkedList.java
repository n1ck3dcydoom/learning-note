package algorithm.leetcode.primeDataStructure.day8;

import algorithm.structure.ListNode;

public class _206_ReverseLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode res = reverseList1(head);
        while (res != null) {
            System.out.print(res.val + " ");
            res = res.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        // 双指针
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            // 保存后驱节点
            ListNode temp = cur.next;
            // 反转
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public static ListNode reverseList1(ListNode head) {
        // 递归
        // 第一步，递归函数的作用
        // reverseList(ListNode head) 返回一个ListNode指向head反转后的新头节点
        if (head == null || head.next == null) {
            return head;
        }
        // 反转头节点后面的所有节点，然后后置更新头节点的指向关系
        ListNode res = reverseList1(head.next);
        // 第二步，函数的参数，哪些是变量，哪些是常量
        // 第三步，拿到递归函数的返回值后，还需要做哪些后置操作
        // n1 -> n2 -> n3 -> n4 -> ... -> nk-1 -> nk -> nk+1 -> ... -> nn
        // 考虑中间过foo(nk)调用后的结果
        // n1 -> n2 -> n3 -> n4 -> ... -> nk-1 -> nk -> nk+1 <- ... <- nn
        // 其中nk-1到nn已经被反转
        // 此时得到递归函数foo(nk)的返回值nn，需要把nk的下一个节点指向nk
        // nk.next.next = nk
        // 还需要把nk的下一个节点指向null，这样最后一个n1的下一个节点也会指向null
        // nk.next = null
        head.next.next = head;
        head.next = null;
        return res;
    }
}
