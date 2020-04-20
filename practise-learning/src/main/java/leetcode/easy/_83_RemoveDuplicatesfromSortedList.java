package leetcode.easy;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1->1->2
 * 输出: 1->2
 * <p>
 * 示例 2:
 * <p>
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/4/18 下午11:27
 **/
public class _83_RemoveDuplicatesfromSortedList {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(3);

        ListNode p = deleteDuplicates(head);
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
    }

    /**
     * 暴力遍历 ?
     *
     * @param head 头结点
     * @return 删除重复节点之后的头结点
     */
    private static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        // 保存头部节点
        ListNode p = head;
        int temp;
        while (p.next != null) {
            ListNode q = p.next;
            temp = p.val;
            // 如果下一个节点的val等于当前节点vale
            if (q.val == temp) {
                // 删除下一个节点
                p.next = q.next;
                q = null;
            } else {
                p = q;
            }
        }
        return head;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}