package nowcode.top101;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 14:51
 * Description:
 */

public class _13_判断一个链表是否是回文结构 {

    public static boolean isPail(ListNode head) {
        // 使用栈保存记录
        Deque<Integer> stack = new ArrayDeque<>();
        ListNode p = head;
        while (p != null) {
            stack.offerLast(p.val);
            p = p.next;
        }
        p = head;
        while (p != null) {
            int top = stack.pollLast();
            if (p.val != top) {
                return false;
            }
            p = p.next;
        }
        return true;
    }
}
