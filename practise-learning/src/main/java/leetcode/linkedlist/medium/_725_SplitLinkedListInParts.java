package leetcode.linkedlist.medium;

import leetcode.data.ListNode;

public class _725_SplitLinkedListInParts {

    public static void main(String[] args) {
        int[] nums = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        ListNode head = new ListNode(0);
        ListNode preHead = head;
        ListNode p = head.next;
        for (int i : nums) {
            p = new ListNode(i);
            head.next = p;
            head = p;
        }
        ListNode pre1 = new ListNode(1);
        pre1.next = new ListNode(2);
        pre1.next.next = new ListNode(3);
        ListNode[] res = splitListToParts(preHead.next, 5);
        // ListNode[] res1 = splitListToParts(pre1, 5);

        for (ListNode node : res) {
            if (node == null) {
                System.out.println("null");
            } else {
                while (node != null) {
                    System.out.print(node.val + " ");
                    node = node.next;
                }
                System.out.println("\n");
            }
        }
        System.out.println(111);
    }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        if (head == null) {
            return new ListNode[k];
        }
        // 遍历一遍链表求得长度
        int n = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            n++;
        }
        // 一共有k个桶，n个球
        ListNode[] res = new ListNode[k];

        // 分情况讨论
        // 1、若k >= n，则每个桶依次放入1个球
        if (k >= n) {
            split(res, head, n, 1, k);
            return res;
        }
        // 2、若 n < k
        // 2.1、若 n % k == 0，则每个桶放入 n/k 个球
        if (n % k == 0) {
            split(res, head, n, n / k, k);
            return res;
        } else {
            // 2.2、若 n % k != 0，则先每个桶放入 n/k 个球
            // 余 n%k 个，则前 n%k 个桶多放1个球
            int mod = n % k;
            int avg = n / k;
            // 先放前 n%k 个桶，每个桶放 n/k + 1 个球
            p = head;
            ListNode q = head;
            int count = 0;
            for (int i = 0; i < mod; i++) {
                for (int j = 1; j < avg + 1; j++) {
                    q = q.next;
                }
                ListNode temp = q.next;
                q.next = null;
                ListNode rp = p;
                res[i] = rp;
                p = temp;
                q = temp;
                count = i;
            }
            // 再放剩下的 k - n%k 个桶，每个桶 n/k 个球
            for (int i = count + 1; i < k; i++) {
                for (int j = 1; j < avg; j++) {
                    q = q.next;
                }
                ListNode temp = q.next;
                q.next = null;
                ListNode rp = p;
                res[i] = rp;
                p = temp;
                q = temp;
            }
            return res;
        }
    }

    private static void split(ListNode[] res, ListNode head, int n, int m, int k) {
        ListNode p = head;
        ListNode q = head;
        if (k > n) {
            for (int i = 0; i < n; i++) {
                for (int j = 1; j < m; j++) {
                    // 把链表切割为长度为m的子链表
                    // 找到分段的最后一个节点q
                    q = q.next;
                }
                // 保存中间信息
                ListNode temp = q.next;
                // 切割
                q.next = null;
                // 保存当前切割下来的分段
                ListNode rp = p;
                res[i] = rp;
                // p指向新分段的头部
                p = temp;
                q = temp;
            }
        } else {
            for (int i = 0; i < k; i++) {
                for (int j = 1; j < m; j++) {
                    // 把链表切割为长度为m的子链表
                    // 找到分段的最后一个节点q
                    q = q.next;
                }
                // 保存中间信息
                ListNode temp = q.next;
                // 切割
                q.next = null;
                // 保存当前切割下来的分段
                ListNode rp = p;
                res[i] = rp;
                // p指向新分段的头部
                p = temp;
                q = temp;
            }
        }
    }
}
