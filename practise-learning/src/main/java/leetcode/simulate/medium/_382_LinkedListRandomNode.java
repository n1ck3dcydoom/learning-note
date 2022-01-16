package leetcode.simulate.medium;

import java.util.Random;

import leetcode.data.ListNode;

/**
 * Created by n!Ck
 * Date: 2022/1/16
 * Time: 23:24
 * Description:
 */

public class _382_LinkedListRandomNode {

    private ListNode head;
    private Random random;

    public _382_LinkedListRandomNode(ListNode head) {
        this.head = head;
        this.random = new Random();
    }

    public int getRandom1() {
        // 遍历链表长度
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }
        // 随机访问
        Random random = new Random();
        int pos = random.nextInt(n);
        // 访问随机位置上的节点
        p = head;
        while (pos > 0) {
            p = p.next;
            pos--;
        }
        return p.val;
    }

    public int getRandom2() {
        // 水塘抽样
        int i = 1;
        int select = -1;
        ListNode p = head;
        while (p != null) {
            if (this.random.nextInt(i) == 0) {
                select = p.val;
            }
            i++;
            p = p.next;
        }
        return select;
    }
}
