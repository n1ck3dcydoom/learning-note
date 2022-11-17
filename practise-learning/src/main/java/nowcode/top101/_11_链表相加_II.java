package nowcode.top101;

import java.util.ArrayDeque;
import java.util.Deque;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/11/17
 * Time: 13:53
 * Description:
 */

public class _11_链表相加_II {

    public static void main(String[] args) {
        ListNode h1 = new ListNode(9);
        h1.next = new ListNode(3);
        h1.next.next = new ListNode(7);

        ListNode h2 = new ListNode(6);
        h2.next = new ListNode(3);

        ListNode h3 = addInList(h1, h2);
        while (h3 != null) {
            System.out.println(h3.val);
            h3 = h3.next;
        }
    }

    public static ListNode addInList(ListNode head1, ListNode head2) {
        // 使用两个栈保存每位数
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();

        ListNode p = head1;
        ListNode q = head2;
        while (p != null) {
            s1.offerLast(p.val);
            p = p.next;
        }
        while (q != null) {
            s2.offerLast(q.val);
            q = q.next;
        }

        int carry = 0;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!s1.isEmpty() || !s2.isEmpty()) {
            int sum = 0;
            if (!s1.isEmpty()) {
                sum += s1.pollLast();
            }
            if (!s2.isEmpty()) {
                sum += s2.pollLast();
            }
            sum += carry;
            carry = sum / 10;
            // 只取 sum 的末尾
            // 头插法
            ListNode e = new ListNode(sum % 10);
            cur = dummy.next;
            dummy.next = e;
            e.next = cur;
            cur = e;
        }
        // 不要忘了最后的进位
        if (carry == 1) {
            // 头插法
            ListNode e = new ListNode(1);
            cur = dummy.next;
            dummy.next = e;
            e.next = cur;
            cur = e;
        }
        return dummy.next;
    }
}
