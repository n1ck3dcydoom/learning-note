package leetcode.dp;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description zl
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/1/5 19:55
 **/
public class _198_HouseRobber {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 1};
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 第一步、定义dp数组，找出递推关系式
        // 定义dp[i]表示偷前i个房间所能获取的最大金额
        int n = nums.length;
        int[] dp = new int[n + 1];

        // 第二步、初始值
        // (没有房子的情况已经在最上面nums.length==0考虑过) dp[0]=0
        dp[0] = 0;
        // 如果只有一间房子，则肯定偷，即dp[1]=nums[1]
        if (n == 1) {
            return nums[0];
        }
        dp[1] = nums[0];
        // 如果有两间房子，则偷金额大的那个房子，即dp[2]=max(nums[1],nums[2])
        dp[2] = Math.max(nums[0], nums[1]);
        // 如有三间及三间以上的房子，则满足dp[i]的递推公式

        // 第三步、做选择，即状态转移方程
        // 两种情况：1、偷第i间房  2、不偷第i间房
        // 1、若偷第i间房，则第i-1间房肯定不能偷，则最大金额等于偷前i-2间房+第i间房的金额，即dp[i] = dp[i-2] + nums[i]
        // 2、若不偷第i间房，则第i-1间房肯定要偷，则最大金额等于偷前i-1间房的金额，即dp[i] = dp[i-1]
        // 综上所述: dp[i] = max(dp[i-2] + nums[i], dp[i-1])
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        }
        return dp[n];
    }
}