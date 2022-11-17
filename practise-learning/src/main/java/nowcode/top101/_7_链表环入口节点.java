package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 13:21
 * Description:
 */

public class _7_链表环入口节点 {

    public ListNode EntryNodeOfLoop(ListNode pHead) {
        // 快慢指针
        ListNode f = pHead;
        ListNode s = pHead;
        while (f != null && f.next != null) {
            f = f.next.next;
            s = s.next;
            if (f == s) {
                // head 到入口距离为 X,入口到第一次相遇的位置距离为 Y,环剩下的距离为 Z
                // 有 s = X + Y; l = X + 2Y + Z; l = 2s
                // 带入后有 X = Z
                // 即当 f 和 s 相遇后,f 回到头节点,第二次相遇即为环入口节点
                f = pHead;
                while (f != s) {
                    f = f.next;
                    s = s.next;
                }
                return s;
            }
        }
        return null;
    }
}
