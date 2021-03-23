package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定一个非负整数数组 nums 和一个整数m ，你需要将这个数组分成m个非空的连续子数组。
 * <p>
 * 设计一个算法使得这m个子数组各自和的最大值最小。
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [7,2,5,10,8], m = 2
 * 输出：18
 * 解释：
 * 一共有四种方法将 nums 分割为 2 个子数组。 其中最好的方式是将其分为 [7,2,5] 和 [10,8] 。
 * 因为此时这两个子数组各自的和的最大值为18，在所有情况中最小。
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4,5], m = 2
 * 输出：9
 * 示例 3：
 * <p>
 * 输入：nums = [1,4,4], m = 3
 * 输出：4
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 1000
 * 0 <= nums[i] <= 106
 * 1 <= m <= min(50, nums.length)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-largest-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/23 22:51
 **/
public class _410_SplitArrayLargestSum {

    public static void main(String[] args) {
        int[] nums = new int[]{7, 2, 5, 10, 8};
        System.out.println(splitArray(nums, 2));
    }

    public static int splitArray(int[] nums, int m) {
        // 将数组分割为m段，容易联想到定义dp[i][j]表示，把数组前i个元素分割为j段后的连续子数组和的最小值
        int n = nums.length;
        // 求的答案就是dp[n][m]，即把i个元素分割为m段之后，所有连续子数组和的最小值
        int[][] dp = new int[n + 1][m + 1];


        // 初始值dp[0][0]，表示0个元素，分割为0段，其和自然就是0
        // 由于nums数组最少都有一个元素，所以反推回来dp数组需要n+1，m+1，多出来的一个位置保存[0][0]的情况
        // 考虑范围，i [0,n]   j [0,m]

        // 根据下面的推算，这里需要全部初始化为最大值
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        dp[0][0] = 0;

        // 求nums的前缀和数组
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // 递推表达式，也是一个填表的过程
        // 考虑dp[i][j]，它的上一个状态，总共i个元素，前面k个数被分割为j-1段，后面从k+1开始到i，总共i-k个数组成第j段
        // 前面的状态是已知的即dp[k][j-1], 而后面的结果等于arr[k+1] + arr[k+2] + ... + arr[i]
        // 所以dp[i][j] = min(max(dp[k][j-1], sum(k+1,i)))

        // 遍历i
        for (int i = 1; i <= n; i++) {
            // 遍历j，j能够取到0吗？如果j==0，表示一段也不分割，题干给定了m至少等于1，所以j从1开始遍历到m
            // 考虑到如果需要分割的段数超过了数组的长度，即就算一个元素构成一个子数组都凑不够m个子数组，此时最多能分割出i个子数组来
            // 即j=i
            for (int j = 1; j <= m && j <= i; j++) {
                // 由于前面i个元素在第k的位置被分割为j-1段了，剩下k+1到i的位置组成第j段
                // 不知道k的具体值，还需要k从0开始遍历到i-1的位置，因为至少要留出1个元素，即nums[i]作为最后一段的唯一一个元素
                // 如何确定k的起始位置
                for (int k = 0; k <= i - 1; k++) {
                    // dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], getSum(nums, k, i - 1)));
                    // 这里调试的时候发现dp[1][1]的初始值是0，后面max计算的到的值是7，取最小值过后等于0了
                    // 这里的dp[i][j]的默认值0影响到了后面的计算
                    // 由于我们最终求的是dp[i][j]是最小值，如果全部初始化为MAX最大值，这样求min后就会保留我们的计算结果

                    // 这里计算k到i的和超时了，使用前缀和数组简化求和的计算过程
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], preSum[i] - preSum[k]));
                }
            }

        }
        return dp[n][m];
    }

    private static int getSum(int[] nums, int start, int end) {
        int sum = 0;
        for (int i = start; i <= end; i++) {
            sum += nums[i];
        }
        return sum;
    }
}
