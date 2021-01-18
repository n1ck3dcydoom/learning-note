package leetcode.dp.medium;

/**
 * @author zhanglei
 * @version 1.0
 * @description leetcode
 * 最初在一个记事本上只有一个字符 'A'。你每次可以对这个记事本进行两种操作：
 * <p>
 * Copy All (复制全部) : 你可以复制这个记事本中的所有字符(部分的复制是不允许的)。
 * Paste (粘贴) : 你可以粘贴你上一次复制的字符。
 * 给定一个数字 n 。你需要使用最少的操作次数，在记事本中打印出恰好 n 个 'A'。输出能够打印出 n 个 'A' 的最少操作次数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 3
 * 输出: 3
 * 解释:
 * 最初, 我们只有一个字符 'A'。
 * 第 1 步, 我们使用 Copy All 操作。
 * 第 2 步, 我们使用 Paste 操作来获得 'AA'。
 * 第 3 步, 我们使用 Paste 操作来获得 'AAA'。
 * 说明:
 * <p>
 * n 的取值范围是 [1, 1000] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/2-keys-keyboard
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/18 22:46
 **/
public class _650_2KeysKeyboard {

    public static void main(String[] args) {
        System.out.println(minSteps(9));
    }

    public static int minSteps(int n) {
        // 第一步、定义dp数组
        // 定义dp[i]表示得到i个A的最少步数
        int[] dp = new int[n + 1];
        // 第二步、初始值
        dp[0] = 0;
        dp[1] = 0;
        // 第三步、选择，即递推表达式
        // 如果当前已经有j个A了，之后每次复制得到j个A
        // 那么从j到i的步数就是dp[j]+ i/j
        // 例如 i=12，j=2，此时需要复制一遍，粘贴五遍，即6次操作  i/j = 12/2 = 6
        // 例如 i=12，j=3，此时需要复制一遍，粘贴三遍，即4次操作  i/j = 12/3 = 4
        // 例如 i=12，j=4，此时需要复制一遍，粘贴两遍，即3次操作  i/j = 12/4 = 3
        // 例如 i=12，j=6，此时需要复制一遍，粘贴一遍，即2次操作  i/j = 12/6 = 2
        // dp[i] = min(dp[j] + i/j) 此时要求i%j == 0，即j是i的因数
        // 为什么只能在j是i的因数的情况下做出选择
        // 想想如果当前已经有9个A了，怎样才能变成12个A
        // 直接复制粘贴肯定不行，得到9之前肯定不是2、4、6、8复制过来的
        // 只能从3粘贴2次得到9，相当于求得是9的因数列表
        // 同理12也只能求12的因数列表
        for (int i = 2; i <= n; i++) {
            // 每个数i的最多步数也就是复制一个A，每次粘贴一个A，也就是复制1次，粘贴i-1次，等于i次
            int min = i;
            // 找i的因数
            for (int j = 2; j <= i / 2; j++) {
                // dp[i] = min(dp[j] + i/j) 此时要求i%j == 0，即j是i的因数
                if (i % j == 0) {
                    min = Math.min(min, dp[j] + i / j);
                }
            }
            dp[i] = min;
        }
        return dp[n];
    }

    public static int minSteps1(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            // 每个数i的最多步数也就是复制一个A，每次粘贴一个A，也就是复制1次，粘贴i-1次，等于i次
            dp[i] = i;
            // 找i的最大因数，可以粘贴最少次数得到j
            for (int j = i / 2; j >= Math.sqrt(i); j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + i / j;
                    // 由于从i/2开始找最大因数，找到第一个就可以直接break了
                    break;
                }
            }
        }
        return dp[n];
    }
}