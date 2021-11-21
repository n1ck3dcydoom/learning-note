package leetcode.dp.medium;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * 给你一个整数数组nums。你需要选择 恰好一个下标（下标从 0开始）并删除对应的元素。请注意剩下元素的下标可能会因为删除操作而发生改变。
 * <p>
 * 比方说，如果nums = [6,1,7,4,1]，那么：
 * <p>
 * 选择删除下标 1 ，剩下的数组为nums = [6,7,4,1]。
 * 选择删除下标2，剩下的数组为nums = [6,1,4,1]。
 * 选择删除下标4，剩下的数组为nums = [6,1,7,4]。
 * 如果一个数组满足奇数下标元素的和与偶数下标元素的和相等，该数组就是一个 平衡数组 。
 * <p>
 * 请你返回删除操作后，剩下的数组nums是平衡数组 的方案数。
 * <p>
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,1,6,4]
 * 输出：1
 * 解释：
 * 删除下标 0 ：[1,6,4] -> 偶数元素下标为：1 + 4 = 5 。奇数元素下标为：6 。不平衡。
 * 删除下标 1 ：[2,6,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：6 。平衡。
 * 删除下标 2 ：[2,1,4] -> 偶数元素下标为：2 + 4 = 6 。奇数元素下标为：1 。不平衡。
 * 删除下标 3 ：[2,1,6] -> 偶数元素下标为：2 + 6 = 8 。奇数元素下标为：1 。不平衡。
 * 只有一种让剩余数组成为平衡数组的方案。
 * 示例 2：
 * <p>
 * 输入：nums = [1,1,1]
 * 输出：3
 * 解释：你可以删除任意元素，剩余数组都是平衡数组。
 * 示例 3：
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：0
 * 解释：不管删除哪个元素，剩下数组都不是平衡数组。
 *
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ways-to-make-a-fair-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/2/22 22:31
 **/
public class _1664_WaysToMakeFairArray {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 1, 6, 4};
        System.out.println(waysToMakeFair(nums));
    }

    public static int waysToMakeFair(int[] nums) {
        // 数组奇数下标和、数组偶数下标和，联想到普通的前缀和(下标连续的前缀和)
        // 这里替换为i前面的奇数下标前缀和，偶数下标前缀和
        // 可以很容易得到从下标0到n的所有上述前缀和

        // 分析题目，求出删掉i之后，剩下的元素的奇偶前缀和是否相等
        // i之前的奇偶前缀和肯定是不变的，i之后的奇偶前缀和由于所有元素往前移动了一个位置，奇偶性发生了翻转
        // 如果i之前的奇数前缀和 加上 i之后的奇数前缀和 == 两部分偶数前缀和，则这是满足题意的一个下标i

        // 所以需要定义两个前缀和数组，分别保存奇数和偶数的前缀和

        // 数组长度
        int n = nums.length;
        // 奇数前缀和
        int[] oddSum = new int[n];
        oddSum[0] = 0;
        // 偶数前缀和
        int[] evenSum = new int[n];
        evenSum[0] = nums[0];
        // 遍历数组得到上述前缀和
        for (int i = 1; i < n; i++) {
            if (i % 2 == 0) {
                oddSum[i] = oddSum[i - 1];
                evenSum[i] = evenSum[i - 1] + nums[i];
            } else {
                oddSum[i] = oddSum[i - 1] + nums[i];
                evenSum[i] = evenSum[i - 1];
            }
        }

        // 遍历原数组，尝试删掉每一个下标的元素，检查删掉后，前后两部分的对应奇偶前缀和是否满足题意
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 删掉下标i后，前部分的前缀和
            // 需要单独考虑删除元素0的情况
            // 如果删除第0个元素，左边的前缀和都是0，否则是减去i后的前缀和即[i-1]
            int preOddSum = i >= 1 ? oddSum[i - 1] : 0;
            int preEvenSum = i >= 1 ? evenSum[i - 1] : 0;

            // 删除下标i后，后部分的前缀和，奇偶性发生翻转
            // 即，后部分的奇数前缀和 等于 删除前的偶数前缀和
            //            偶数前缀和 等于 删除前的奇数前缀和
            int backOddSum = evenSum[n - 1] - evenSum[i];
            int backEvenSum = oddSum[n - 1] - oddSum[i];

            // 判断两部分对应的前缀和是否相等
            if (preEvenSum + backEvenSum == preOddSum + backOddSum) {
                res++;
            }
        }
        return res;
    }

    private static int waysToMakeFair1(int[] nums) {

        int n = nums.length;
        // 定义dp[i]表示从0到i的偶元素和 - 奇元素和的差值
        int[] dp = new int[n];

        // 初始值(偶元素和 - 奇元素和的差值)
        dp[0] = -nums[0];

        for (int i = 1; i < n; i++) {
            // 如果i是偶数，那么dp[i] = dp[i-1] + nums[i]
            // 如果i是奇数，那么dp[i] = dp[i-1] - nums[i]
            dp[i] = dp[i - 1] + ((i % 2 == 0) ? nums[i] : -nums[i]);
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (i >= 1 && i < n - 1) {
                if (dp[i - 1] == dp[n - 1] - dp[i]) {
                    res++;
                }
            } else if (i == 0) {
                if (dp[n - 1] - dp[i] == 0) {
                    res++;
                }
            } else if (i == n - 1) {
                if (dp[n - 2] == 0) {
                    res++;
                }
            }
        }
        return res;
    }
}