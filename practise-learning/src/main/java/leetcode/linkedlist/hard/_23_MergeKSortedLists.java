package leetcode.linkedlist.hard;

import leetcode.data.ListNode;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * 
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 *
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 *
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：lists = [[]]
 * 输出：[]
 * 
 *
 * 提示：
 *
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-k-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 
 * @date 2021/4/25 22:46
 **/
public class _23_MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode res = null;

        if (lists == null || lists.length == 0) {
            return res;
        }

        // 参考合并两个链表的方式，每次合并两个链表
        for (ListNode list : lists) {
            res = mergeTwoLinkedList(res, list);
        }
        return res;
    }

    private ListNode mergeTwoLinkedList(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        ListNode head = new ListNode();
        // 使用res保存假头
        ListNode res = head;

        // p和q分别指向a和b，每次放入较小的值到res中，然后指针后移
        // 直到某个指针指向null，直接放入另外一个链表到res后面

        ListNode p = a;
        ListNode q = b;
        while (p != null && q != null) {
            // 尾插到res后面
            if (p.val <= q.val) {
                head.next = p;
                p = p.next;
            } else {
                head.next = q;
                q = q.next;
            }
            // 移动head指针
            head = head.next;
        }
        // 判断哪个先到末尾
        if (p == null) {
            head.next = q;
        }
        if (q == null) {
            head.next = p;
        }
        return res.next;
    }

    /**
     * 归并合并
     *
     */
    /**
     * 归并合并，每次合并两个链表，第一趟之后还剩k/2个
     * 第二趟之后还剩k/4个。。。以此类推
     */
    private ListNode merge(ListNode[] lists, int left, int right) {
        // 如果当前两趟合并的索引已经相当，直接返回
        if (right == left) {
            return lists[left];
        }
        // 寻找下一次递归的中间位置
        int mid = left + (right - left) / 2;
        // 递归找到最后一个l1和l2
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        // 开始归并合并
       return mergeTwoLinkedList(l1, l2);
    }
}
