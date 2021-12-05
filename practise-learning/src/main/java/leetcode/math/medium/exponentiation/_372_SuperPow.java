package leetcode.math.medium.exponentiation;

public class _372_SuperPow {

    public static void main(String[] args) {
        // System.out.println(superPow(2, new int[]{3}));
        // System.out.println(superPow(2, new int[]{1, 0}));
        // System.out.println(superPow(1, new int[]{4, 3, 3, 8, 5, 2}));
        System.out.println(superPow(2147483647, new int[]{2, 0, 0}));
    }

    private final static int MOD = 1337;

    public static int superPow(int a, int[] b) {
        // 普通快速幂
        // 倒序遍历
        int res = 1;
        a %= MOD;
        for (int i = b.length - 1; i >= 0; i--) {
            // 先计算末尾的数
            // a^[x,y,z] = a^z * a^[x,y]^10
            res = (res * qpow(a, b[i])) % MOD;
            // 普通快速幂计算将指数转化为二进制
            // 这里计算的是a^[x,y]^10的结果
            a = qpow(a, 10);
        }
        return res;
    }

    private static int qpow(int x, int n) {
        int res = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                res = (res * x) % MOD;
            }
            x = (x * x) % MOD;
            n >>= 1;
        }
        return res;
    }
}
