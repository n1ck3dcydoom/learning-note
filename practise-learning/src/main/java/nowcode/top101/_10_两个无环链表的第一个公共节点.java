package nowcode.top101;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 13:47
 * Description:
 */

public class _10_两个无环链表的第一个公共节点 {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        // 快慢指针
        ListNode f = pHead1;
        ListNode s = pHead2;
        // 找到两个链表的长度差
        int i = 0;
        while (f != null) {
            i++;
            f = f.next;
        }
        int j = 0;
        while (s != null) {
            j++;
            s = s.next;
        }
        // 差值
        int k = Math.abs(i - j);
        f = pHead1;
        s = pHead2;
        if (i > j) {
            while (k > 0) {
                f = f.next;
            }
        } else {
            while (k > 0) {
                s = s.next;
            }
        }
        while (f != s) {
            f = f.next;
            s = s.next;
        }
        return f;
    }
}
