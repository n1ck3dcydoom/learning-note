package leetcode.simulate.medium;

public class _390_EliminationGame {

    public static void main(String[] args) {
        System.out.println(lastRemaining(24));
    }

    public static int lastRemaining(int n) {
        if (n == 1) {
            return 1;
        }
        // 数组头部
        int head = 1;
        // 1 2 3 4 5 6 7 8      -> 2 4 6 8    删掉4个数剩下8/2=4个数，head变为2
        // 2 4 6 8 -> 2 6  删掉2个数剩下4/2=2个数，head还是22
        // 1 2 3 4 5 6 7 8 9 10 -> 2 4 6 8 10 删掉5个数剩下10/2=4个数，head变为2
        // 2 4 6 8 10 -> 4 8 删掉3个数剩下5/2=2个数，head变为4
        // 每次删除将会剩下n/2个数

        // head什么时候会变
        // 从左边开始删除的时候，无论当前是奇数个还是偶数个数，head=head+1
        // 从右边开始删除的时候，且当前个数是奇数，head=head+1
        // 从右边开始删除的时候，当前是偶数个数，head不变

        // 从左从右
        boolean left = true;

        // 删除的步长
        int step = 1;
        // 如何理解step每次更新为step*2，可以通过下面的例子发现，其实每次都是一个等差数列
        // 每次删除过后的新等差数列的公差，变为上一次等差数列公差的2倍
        // ↓
        // 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20   head=1 step=1 left=true
        //   ↓
        //   2   4   6   8   10    12    14    16    18    20   head=1+1=2 step=1*2=2 left=right
        //   ↓
        //   2       6       10          14          18         head=不变2 step=2*2=4 left=true
        //           ↓
        //           6                   14                     head=2+4=6 step=4*2=8 left=right
        //           ↓
        //           6                                          head不变6 step=8*2=16 left=true  break

        while (n > 1) {
            // 从左删
            if (left) {
                // 等差数列头部元素一定后移
                head = head + step;
            }
            // 从右删
            else {
                // 奇数个数头部后移
                head += n % 2 == 0 ? 0 : step;
            }
            // 每次删除后剩下元素个数为n/2
            // n /= 2;
            n = n >> 1;
            // 每次删除后公差变为原来的2倍
            // step *= 2;
            step = step << 1;
            // 方向改变
            left = !left;
        }
        return head;
    }
}
