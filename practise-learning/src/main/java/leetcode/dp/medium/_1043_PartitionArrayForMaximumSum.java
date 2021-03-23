package leetcode.dp.medium;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个整数数组 arr，请你将该数组分隔为长度最多为 k 的一些（连续）子数组。分隔完成后，每个子数组的中的所有值都会变为该子数组中的最大值。
 * <p>
 * 返回将数组分隔变换后能够得到的元素最大和。
 * <p>
 *
 * <p>
 * 注意，原数组和分隔后的数组对应顺序应当一致，也就是说，你只能选择分隔数组的位置而不能调整数组中的顺序。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [1,15,7,9,2,5,10], k = 3
 * 输出：84
 * 解释：
 * 因为 k=3 可以分隔成 [1,15,7] [9] [2,5,10]，结果为 [15,15,15,9,10,10,10]，和为 84，是该数组所有分隔变换后元素总和最大的。
 * 若是分隔成 [1] [15,7,9] [2,5,10]，结果就是 [1, 15, 15, 15, 10, 10, 10] 但这种分隔方式的元素总和（76）小于上一种。
 * 示例 2：
 * <p>
 * 输入：arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
 * 输出：83
 * 示例 3：
 * <p>
 * 输入：arr = [1], k = 1
 * 输出：1
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 500
 * 0 <= arr[i] <= 109
 * 1 <= k <= arr.length
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/partition-array-for-maximum-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/22 23:16
 **/
public class _1043_PartitionArrayForMaximumSum {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 15, 7, 9, 2, 5, 10};
        System.out.println(maxSumAfterPartitioning1(arr, 3));
    }

    public static int maxSumAfterPartitioning(int[] arr, int k) {

        // 定义dp[i]表示数组arr前i个元素能够在满足最多分割k个连续元素的情况下，所得到的最大和

        // 初始状态很好确定，dp[0]有且只有一种分割方法
        int n = arr.length;

        int[] dp = new int[n];
        dp[0] = arr[0];

        int res = 0;
        res = dp[0];

        // 随便从k等于3开始分析问题
        // dp[0] = arr[0]
        // dp[1] 的分割方法，只有两种
        // dp[1] = dp[0] + arr[1]                     // 从i开始往前分割1个元素，且前i-1的dp[i-1]状态已知
        // dp[1] = max(arr[0], arr[1]) * 2            // 由于i-2已经超过了数组的下边范围，所以相当于只能分割1个数组

        // dp[2] = dp[1] + arr[2]                     // 从i开始往前分割1个元素，且前i-1的dp[i-1]状态已知
        // dp[2] = dp[0] + max(arr[1], arr[2]) * 2    // 从i开始往前分割2个元素，且前i-2的dp[i-2]状态已知
        // dp[2] = max(arr[0],arr[1],arr[2]) * 3      // 由于i-3已经超过了数组的下边范围，所以相当于只能分割1个数组

        // 一般情况下k，由于是从i往前分割k个元素，所以再用一个变量j从i-k+1遍历到i (表示从i开始最多往前分割k个元素)
        // dp[i] = dp[i-1] + arr[i]
        // dp[i] = dp[i-2] + max(arr[i],arr[i-1]) * 2
        //                  ...
        // dp[i] = dp[i-k] + max(arr[i],arr[i-1],...arr[i-k+1]) * k

        for (int i = 1; i < n; i++) {
            // dp[0]状态已知，从1开始，直到等于k

            // dp[i] = dp[i-1] + arr[i]
            // dp[i] = dp[i-2] + max(arr[i],arr[i-1]) * 2
            //                  ...
            // dp[i] = dp[i-k] + max(arr[i],arr[i-1],...arr[i-k+1]) * k
            // 观察递推表达式，后面的i-1、i-2、...、i-k可以替换为i-j
            // 在计算dp的时候，发现是从i-k依次往后遍历到i-1的

            // 考虑如果k已经大于i了，那么还没有遍历到k次(假设在j的时候越界了)，在前面就会数组越界，所以自然而然就出现了分情况讨论
            // 如果j>i，那么，那么相当于只能分割成一个数组，直接求最大值max(arr[0],arr[1],...,arr[i]) * i
            // 如果j<=i   则使用上面的地推表达式求解

            // 为什么要在j>i的时候判断只能分割成一个数组，j=i的时候，这里表示刚好把arr的所有元素组成一个数组
            // 此时需要计算 dp[0] + max(arr[i],arr[i-1],...arr[i-k+1]) * k
            // 这里也是递推表达式的最后一项

            for (int j = 1; j <= k; j++) {
                // 当前遍历的中间最大值
                int tempMax = 0;
                // 当i需要分割出个数为k，且k>i的段落时，直接计算arr所有元素的最大值，乘以arr的长度
                if (i - j < 0) {
                    dp[i] = getSubArrayMax(arr, 0, n - 1) * n;
                }
                // 当i满足分割出个数为k，且前面还剩有其他元素时，使用递推表达式计算dp[i]
                else {
                    dp[i] = dp[i - j] + getSubArrayMax(arr, i - j + 1, i) * j;
                }
            }
        }

        return 0;
    }

    /**
     * 求数组区间内的最大值
     */
    private static int getSubArrayMax(int[] arr, int start, int end) {
        int max = 0;
        for (int i = start; i <= end; i++) {
            max = Math.max(arr[i], max);
        }
        return max;
    }

    public static int maxSumAfterPartitioning1(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n];

        // 初始值
        dp[0] = arr[0];

        // 遍历i
        for (int i = 1; i < n; i++) {
            // 用当前i的元素作为分段数组的起始最大值，后面倒序遍历j的时候会不断跟新这个最大值和对应的dp[i]
            int tempMax = arr[i];
            // 从i开始往前遍历k个元素，用j表示，直到j=i-k ，需要处理j==0的边界条件
            for (int j = i; j > i - k; j--) {
                // 更新中间最大值
                tempMax = Math.max(tempMax, arr[j]);
                // 更新中间dp[i]
                // 如果j==0了，表示整个数组都作为k的分段子数组，直接跳出j的遍历
                if (j == 0) {
                    // 使用整个数组的最大值
                    // 当前下标是i，数组的长度就是i+1
                    dp[i] = Math.max(dp[i], tempMax * (i + 1));
                    break;
                } else {
                    // 通过递推表达式计算dp[i]的结果
                    dp[i] = Math.max(dp[i], dp[j - 1] + tempMax * (i - j + 1));
                }
            }
        }
        return dp[n - 1];
    }

}