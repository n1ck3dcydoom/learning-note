package leetcode.pointoffer;

import leetcode.data.DuplexingListNode;

public class PointOffer_62_Josephus {

    public static void main(String[] args) {
        // System.out.println(josephus(5, 3));
        System.out.println(josephus2(11, 3));
    }

    private static int josephus(int n, int m) {
        // 使用双链表模拟约瑟夫环

        // 构造双链表环
        DuplexingListNode head = new DuplexingListNode(0);
        DuplexingListNode p = head;
        DuplexingListNode q = null;
        for (int i = 1; i < n; i++) {
            q = new DuplexingListNode(i);
            p.next = q;
            q.pre = p;

            p = q;
            q = null;
        }
        p.next = head;
        head.pre = p;

        p = head;
        int count = 0;
        while (p.next != p) {
            count++;
            // 删除报m的节点
            if (count == m) {
                // 待删除结点的前驱节点
                q = p.pre;
                // 更新指针关系
                q.next = p.next;
                p.next.pre = q;
                // 删除p
                p = null;
                // 移动p到下一个节点
                p = q.next;
                // 重置counter
                count = 0;
                continue;
            }
            p = p.next;
        }
        return p.val;
    }

    private static int josephus2(int n, int m) {
        // 公式推导
        // 令F(N,M)表示N个人参加游戏，报到M的人退出，最终的胜利者下标
        // 有F(N,M)=(F(N-1,M)+M)%n
        // 其中n=N-1

        // f(N,M)=(F(N-1,M)+M)%n，其中n=N-1
        int p = 0; // p=f(N,M)
        int q = 0; // q=f(N-1,M)
        // 若N==1，那么获胜者就是这个人
        // f(1,M)=0
        if (n == 1) {
            return 0;
        }
        // 初始状态，若N==2，M是偶数就第一个人出局，M是奇数就第二个人出局
        // f(2,M) == M % 2 == 0 ? 0 : 1;
        q = m % 2 == 0 ? 0 : 1;
        if (n == 2) {
            return q;
        }
        for (int i = 3; i <= n; i++) {
            // 根据q计算p
            p = (q + m) % i;
            // 更新q
            q = p;
        }
        return p;
    }
}
