package leetcode.dp.hard;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 * 示例 2：
 * <p>
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *  
 * <p>
 * 提示：
 * <p>
 * n == height.length
 * 0 <= n <= 3 * 104
 * 0 <= height[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/1 19:33
 **/
public class _42_TrappingRainWater {

    public static void main(String[] args) {
        int[] h = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(h));
    }

    public static int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int res = 0;

        // 参考双指针的解法，在遍历i的时候，每次都要重复往两边寻找两端的最大值
        // 可以在每次遍历的时候，通过两个数组分别保存以i开始左边的最大值，右边的最大值

        // 定义dp数组
        int[] leftDp = new int[n];
        int[] rightDp = new int[n];

        // 初始值
        // 显然，当i等于0的时候，左边的最大值就是h[0]
        // 当i等于n-1的时候，右边的最大值就是h[n-1]
        leftDp[0] = height[0];
        rightDp[n - 1] = height[n - 1];

        // 递推表达式
        // 对于leftDp来说 每次遍历到i的时候，已知左边i-1的最大值为leftDp[i-1]
        // 只需要判断h[i]和leftDp[i-1]谁更大，然后更新leftDp[i]即可，即leftDp[i] = Math.max(leftDp[i-1], h[i])
        // 对于rightDp来说 需要从n-1开始逆向遍历到0，每次遍历到i的时候，已知右边i+1的最大值为rightDp[i+1]
        // 只需要判断h[i]和rightDp[i+1]谁更大，然后更新rightDp[i]即可，即rightDp[i] = Math.max(rightDp[i+1], h[i])

        // 求解leftDp，从左往右扫描
        for (int i = 1; i < n; i++) {
            leftDp[i] = Math.max(leftDp[i - 1], height[i]);
        }
        // 求解rightDp，从右往左扫描
        for (int i = n - 2; i >= 0; i--) {
            rightDp[i] = Math.max(rightDp[i + 1], height[i]);
        }

        // 遍历i，依次计算第i列上能装的水
        for (int i = 0; i < n; i++) {
            // 左右两端的较小值
            int tempMin = Math.min(leftDp[i], rightDp[i]);
            // 根据短板效应求第i列能装的水
            if (tempMin > height[i]) {
                res += tempMin - height[i];
            }
        }

        return res;
    }
}