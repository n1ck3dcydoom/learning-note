package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 13:35
 * Description:
 */

public class _8_链表中倒数K个节点 {

    public ListNode FindKthToTail(ListNode pHead, int k) {
        if (pHead == null) {
            return pHead;
        }
        // 快慢指针
        ListNode f = pHead;
        ListNode s = pHead;

        // 先让 f 走 k 步
        while (k > 0 && f != null) {
            f = f.next;
            k--;
        }
        // k 超过了链表节点个数,返回 null
        if (f == null && k > 0) {
            return null;
        }
        // 同时移动 f 和 s,当 f 到末尾是,s 就是倒数第 k 个
        while (f != null) {
            s = s.next;
            f = f.next;
        }
        return s;
    }
}
