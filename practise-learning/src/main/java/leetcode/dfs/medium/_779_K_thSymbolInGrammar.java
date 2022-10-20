package leetcode.dfs.medium;

/**
 * Created by n!Ck
 * Date: 2022/10/20
 * Time: 09:48
 * Description:
 */

public class _779_K_thSymbolInGrammar {

    public static void main(String[] args) {
        System.out.println(kthGrammar(3, 3));
    }

    public static int kthGrammar(int n, int k) {
        // n=1 0
        // n=2 01
        // n=3 0110
        // n=4 01101001
        // 观察发现第 n 层的前半部分和 n-1 层相同,后半部分与 n-1 层相反
        // 从第 n 层第 k 个数往前递归求解

        // 第一层为 0
        if (n == 1) {
            return 0;
        }
        // 可以看作是满二叉树,其每一层的节点个数为 2^(n-1)
        if (k <= (Math.pow(2, n - 1) / 2)) {
            // 若第 k 个数在第 n 层的前半部分,则第 k 个数与 n-1 层第 k 个数相同
            return kthGrammar(n - 1, k);
        } else {
            // 若第 k 个数在第 n 层的后半部分,则第 k 个数余 n-1 层第 k 个数相反
            // 每次往上一层递归时,节点个数会减少一半,为了和下一层的处于后半段的第 k 个数索引保持一致
            // 递归时需要将后半段的 k 值映射到前半段,即减去前半段的个数 k = k-(2^{n-1}/2) = k-(2^{n-2})
            // 异或运算取反
            return kthGrammar(n - 1, k - (int) Math.pow(2, n - 2)) ^ 1;
        }
    }
}
