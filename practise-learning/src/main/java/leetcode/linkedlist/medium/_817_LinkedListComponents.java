package leetcode.linkedlist.medium;

import java.util.HashSet;
import java.util.Set;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/10/12
 * Time: 09:46
 * Description:
 */

public class _817_LinkedListComponents {

    public static void main(String[] args) {
        ListNode h1 = new ListNode(0);
        h1.next = new ListNode(1);
        h1.next.next = new ListNode(2);
        h1.next.next.next = new ListNode(3);
        System.out.println(numComponents(h1, new int[]{0, 1, 3}));

        ListNode h2 = new ListNode(0);
        h2.next = new ListNode(1);
        h2.next.next = new ListNode(2);
        h2.next.next.next = new ListNode(3);
        h2.next.next.next.next = new ListNode(4);
        System.out.println(numComponents(h2, new int[]{0, 3, 1, 4}));
    }

    public static int numComponents(ListNode head, int[] nums) {
        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            set.add(n);
        }

        int k = 0;
        ListNode p = head;
        while (p != null) {
            if (!set.contains(p.val)) {
                if (k != 0) {
                    res++;
                    k = 0;
                }
            } else {
                k++;
            }
            p = p.next;
        }
        return k == 0 ? res : res + 1;
    }
}
