package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 有 n 个气球，编号为0 到 n - 1，每个气球上都标有一个数字，这些数字存在数组 nums 中。
 * <p>
 * 现在要求你戳破所有的气球。戳破第 i 个气球，你可以获得 nums[i - 1] * nums[i] * nums[i + 1] 枚硬币。 这里的 i - 1 和 i + 1 代表和 i 相邻的两个气球的序号。如果 i - 1或 i + 1 超出了数组的边界，那么就当它是一个数字为 1 的气球。
 * <p>
 * 求所能获得硬币的最大数量。
 * <p>
 * 示例 1：
 * 输入：nums = [3,1,5,8]
 * 输出：167
 * 解释：
 * nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
 * coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
 * 示例 2：
 * <p>
 * 输入：nums = [1,5]
 * 输出：10
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 500
 * 0 <= nums[i] <= 100
 * 通过次数39,586提交次数5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/3/2 21:21
 **/
public class _312_BurstBalloons {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5};
        System.out.println(maxCoins(nums));
    }

    public static int maxCoins(int[] nums) {
        // 对于某个区间i，j
        // 戳破其中第k个气球，可以得到的金币是nums[k-1] * nums[k] * nums[k+1]
        // 如果k-1或者k+1超过了边界，即k是区间的左端点，或者k是区间的右端点，那么特殊处理为金币等于1

        // 在不考虑边界的情况下，对于 “开”区间 i,j，此时从i < k < j选出一个k一定不为边界情况
        // 假设dp[i][j]表示从nums中的 “开”区间 i,j 中戳破所有气球能获得的最大金币数
        // 假设最后一个戳破的气球是k个气球被戳破，那么k可以把区间(i,j)分为(i,k)(k,j)
        // 由于是最后一个被戳破的气球k，那么k两端就只剩i和j了，所以他相邻的两个气球就是i和j
        // 此时能够获得的最大金币dp[i][j] = dp[i][k] + nums[i] * nums[k] * nums[j] + dp[k][j]
        // 如果能够遍历i到j，计算出每个不同位置的k，那么求出最大值，就是区间(i,j)的最优解

        // dp问题如何定义递推表达式，比较好的分析方式就是自底向上
        // 自底向上：对于区间(i,j)，思考 “最后” 一个被戳爆的气球，这里的 “最后” 一个，其实就是 “底”

        // 这里需要处理的就是k的边界问题，如果戳爆的气球处于给定数组nums的两端，那么他左右相邻越界的部分必须要当作金币1来处理
        // 可以在dp数组中，先预留出这两个边界
        // 即  3,1,5,8 ->  1,3,1,5,8,1
        // 两头多出来的1，就是边界情况下的气球，但是我们又不能把他们戳爆
        // 所以dp数组需要多2个额外空间存储边界情况
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        // 对于给定的气球数组nums，也需要多开辟2个额外空间存储不存在的边界气球
        int[] newNums = new int[n + 2];
        newNums[0] = 1;
        newNums[n + 1] = 1;
        // 新气球数组两端的金币只有1
        for (int i = 1; i < n + 1; i++) {
            newNums[i] = nums[i - 1];
        }

        // 递归求解dp
        return getMaxCoins(newNums, 0, n + 1, dp);

    }

    /**
     * 如果用递归的话？会不会好想一点
     * https://leetcode-cn.com/problems/burst-balloons/solution/java-dong-tai-gui-hua-xiao-bai-si-kao-guo-cheng-fe/
     *
     * @param nums  添加两端后的新金币数组
     * @param start 当前需要递归的起点
     * @param end   当前需要递归的终点
     * @param dp    每次递归需要把dp数组带入
     * @return 当前start到end的最大金币
     */
    private static int getMaxCoins(int[] nums, int start, int end, int[][] dp) {

        // 第二步、思考结束递归的条件
        // 什么情况下将没有气球能够被戳破了，当start = end - 1的时候，表示start和end之间再也没有气球了
        // 这样自然就没有k值了，所以这个时候需要结束递归
        // 既然没有k气球能够被戳破，自然也没有金币可以拿到，所以直接返回0
        if (start == end - 1) {
            return 0;
        }

        // 第一步、先思考递归的步骤
        // 对于start和end，需要遍历中间每个k所分成的两个区间，求得k两端区间的最大硬币数后，加上戳破k的硬币数
        // 对于每一个k值，求所有k值的最大值，即是dp[start][end]的最终结果
        // 所以需要一个循环，从start开始，到end结束
        // 需要一个变量保存每次遍历k得到的结果，计算最大值
        int max = 0;

        // nums:       3 1 5 8
        // newNums:  1 3 1 5 8 1
        // newIndex: 0 1 2 3 4 5

        // oldLength = n = 4
        // newLength = n + 2 = 6
        // 第一次递归的start，自然是newNums的第一个，即下标0
        // 第一次递归的end，自然是newNums的最后一个，即下标n+2-1= n+1
        // 由于下标0和下标n+1的两个气球不能戳破（金币1），所以实际k值需要从start+1开始遍历，直到n+1
        for (int k = start + 1; k < end; k++) {
            // 比较每次遍历k得到的结果，保存最大值
            // 这里的nums实际上是拓展后的newNums，所以下标不用额外处理
            // nums[k] * nums[start] * nums[end]表示戳破最后一个气球k所能得到的金币数量
            max = Math.max(max,
                    nums[k] * nums[start] * nums[end] + getMaxCoins(nums, start, k, dp) + getMaxCoins(nums, k, end,
                            dp));
            // getMaxCoins(nums, start, k, dp) 表示求k左边的气球全部被戳爆后能得到的最大金币
            // 由于递归遍历中k从start+1一直遍历到end-1为止，所以不用传入end = k-1，因为递归会从k=end - 1结束，即k永远不可能等于k-1了 (这样会导致k-1这个气球没有戳爆，但是k-1是一个合法的气球)
            // getMaxCoins(nums, k, end, dp) 表示求k右边的气球全部被戳爆后能得到的最大金币
            // 不用传入start = k + 1，因为递归会从start+1开始，即从k+2开始(这样会导致k+1这个气球没有被戳爆，但是k+1是一个合法的气球)
        }
        // 递归遍历完当前start到end的区间中所有合法气球被戳爆后，记录下dp[start][end]的值
        dp[start][end] = max;
        // 返回当前递归的结果
        return max;
    }

    private static int getMaxCoins1(int[] nums, int start, int end, int[][] dp) {

        if (start == end - 1) {
            return 0;
        }
        // 直接递归会超时，考虑结束递归的条件

        // 由于每次递归遍历左右区间时，当从start1到end1遍历到很小的区间start0到end0的时候
        // 这里的start0到end0很有可能被之前更大的区间start2到end1遍历的时候计算过了
        // 所以这里发现dp[start0][end0]的值已经不等于0的时候，可以提前结束递归
        if (dp[start][end] != 0) {
            return dp[start][end];
        }
        int max = 0;
        for (int k = start + 1; k < end; k++) {
            max = Math.max(max,
                    nums[k] * nums[start] * nums[end] + getMaxCoins1(nums, start, k, dp) + getMaxCoins1(nums, k, end,
                            dp));
        }
        dp[start][end] = max;
        return max;
    }

}