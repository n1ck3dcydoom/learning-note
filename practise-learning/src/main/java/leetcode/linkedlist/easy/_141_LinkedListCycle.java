package leetcode.linkedlist.easy;

import leetcode.data.ListNode;

import java.util.HashSet;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 如果链表中存在环，则返回 true 。 否则，返回 false 。
 * <p>
 * <p>
 * 进阶：
 * 你能用 O(1)（即，常量）内存解决此问题吗？
 * <p>
 * 提示：
 * <p>
 * 链表中节点的数目范围是 [0, 10^4]
 * -10^5 <= Node.val <= 10^5
 * pos 为 -1 或者链表中的一个 有效索引 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/17 23:00
 **/
public class _141_LinkedListCycle {

    /**
     * 哈希表，遍历整个链表，每次放入hash表中，如果已经存在节点，则表示有环
     */
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            } else {
                set.add(head);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 快慢指针，两个指针同时从一个起点出发
     * 快指针每次走两格，慢指针每次走一格
     * 如果有环，那么两个指针一定会相遇
     * 如果快指针走到终点了，说明没有环
     */
    public boolean hasCycle1(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 先移动一步
        do {
            // 快指针没有到达终点，则移动两个指针
            if (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            } else {
                return false;
            }
        } while (fast != slow);
        return true;
    }
}