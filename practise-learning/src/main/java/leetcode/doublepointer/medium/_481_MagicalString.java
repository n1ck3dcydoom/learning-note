package leetcode.doublepointer.medium;

/**
 * Created by n!Ck
 * Date: 2022/10/31
 * Time: 00:27
 * Description:
 */

public class _481_MagicalString {

    public static void main(String[] args) {
        System.out.println(magicalString(4));
    }

    public static int magicalString(int n) {
        // 从 s 前面已知的部分,一直往后扩展,直到 n=1^5
        // 令指针 i 指向 s 串,指针 j 指向 "新串", 即使新串和 s 串相等
        // 由于 i 每次指向 2 后,新串都要新增两个相同数组
        // 所以在构造的过程当中,可以保证 i,j 满足关系 i <= j 一定成立
        //  s = 1 2  2  1 1 2  1 2  2  1 2  2  1 1 2  1 1 2  2  1
        // s0 = 1 22 11 2 1 22 1 22 11 2 11 22 1 2 11 2 1 22 11 2
        // 初始状态如果为 1,无法继续构造
        // 初始状态如果为 12
        // i=1,j=1
        // i=2,j=22
        // i=2,j=11
        // i=1,j=2
        // i=1,j=1
        // 可以一直构造下去,其中 j 每次交替出现 1 和 2
        if (n <= 3) {
            return 1;
        }
        char[] cs = new char[2 * n + 1];
        // cs 下表从 1 开始计算
        // i 遍历 s 串,j 构造新串,从 12 开始构造
        // i 指向 s 串需要构造的位置,j 指向新串最后一个数字的下一个空白位置
        int i = 2, j = 2;
        cs[1] = '1';
        cs[2] = '2';

        int res = 1;
        while (i <= n) {
            // j 指向的新串 1 和 2 是交替出现的
            char last = cs[j - 1];
            char cur = last == '1' ? '2' : '1';
            if (cs[i] == '2') {
                cs[j++] = cur;
                cs[j++] = cur;
            } else {
                cs[j++] = cur;
            }
            if (cs[i] == '1') {
                res++;
            }
            i++;
        }
        return res;
    }
}
