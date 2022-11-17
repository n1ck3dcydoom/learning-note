package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 09:21
 * Description:
 */

public class _2_反转区间链表 {

    /**
     * 链表区间反转
     * 将一个节点数为 size 链表 m 位置到 n 位置之间的区间反转，要求时间复杂度 O(n)，空间复杂度 O(1)。
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // 如果 m==1,就等于反转前 n 个节点
        if (m == 1) {
            return reverseBefore(head, n);
        }
        // 如果 m!=1,把 head.next 当做下标 1,相当于反转 m-1 到 n
        head.next = reverseBetween(head.next, m - 1, n - 1);
        return head;
    }

    // 反转前第 n 个节点的后驱节点
    private ListNode back = null;

    /**
     * 反转前 n 个节点
     */
    public ListNode reverseBefore(ListNode head, int n) {
        // 找到前 n 个节点的最后一个节点,包含头节点
        if (n == 1) {
            // 找到最后一个节点时,保存这个节点的后驱节点,当反转完成后新链表的最后一个节点的 next 指向这个后驱节点
            back = head.next;
            return head;
        }
        ListNode newHead = reverseBefore(head.next, n - 1);
        head.next.next = head;
        // 这里 head.next 指向之前保存的后驱节点
        head.next = back;
        return newHead;
    }


    public static void main(String[] args) {
        ListNode h = new ListNode(1);
        h.next = new ListNode(2);
        h.next.next = new ListNode(3);
        h.next.next.next = new ListNode(4);
        h.next.next.next.next = new ListNode(5);

        ListNode nh = reverseBetween1(h, 2, 4);
        while (nh != null) {
            System.out.println(nh.val);
            nh = nh.next;
        }
    }

    /**
     * 迭代反转
     */
    public static ListNode reverseBetween1(ListNode head, int m, int n) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        // 记录 cur 的前驱节点
        ListNode pre = dummy;
        // 当前遍历的节点
        ListNode cur = head;
        // 找到节点 m
        // 此时 cur = 节点 m, pre.next = cur
        for (int i = 1; i < m; i++) {
            pre = cur;
            cur = cur.next;
        }
        // 反转 m 到 n
        for (int i = m; i < n; i++) {
            // 将 tmp = cur.next 头插法插入到 pre 后面
            ListNode tmp = cur.next;
            cur.next = tmp.next;
            tmp.next = pre.next;
            pre.next = tmp;
        }
        return dummy.next;
    }
}

