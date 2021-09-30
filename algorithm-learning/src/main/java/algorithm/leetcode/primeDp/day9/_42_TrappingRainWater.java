package algorithm.leetcode.primeDp.day9;

public class _42_TrappingRainWater {

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap(nums));
    }

    public static int trap(int[] height) {
        // 按列求解每列上面能够接到的雨水
        int res = 0;
        int n = height.length;

//        // 第一列单独不可能接到水，直接从第二列开始求解
//        for (int i = 1; i < n; i++) {
//            // 记录左边的最高列和右边的最高列
//            // 分为三种情况
//            // 1、左右最高列的较小值>当前列
//            // 能够接到的雨水=较小值高度-当前列高度
//            // 2、较小值=当前列
//            // 3、较小值<当前列
//            // 23情况都接不到雨水
//            int maxL = 0, maxR = 0;
//            // 往左寻找最高列，不包含当前列i
//            for (int j = i - 1; j >= 0; j--) {
//                maxL = Math.max(maxL, height[j]);
//            }
//            // 往右寻找最高列，不包含当前列i
//            for (int j = i + 1; j < n; j++) {
//                maxR = Math.max(maxR, height[j]);
//            }
//            // 判断左右最高列的较小值和当前列的关系
//            if (Math.min(maxL, maxR) > height[i]) {
//                res += Math.min(maxL, maxR) - height[i];
//            }
//        }

        // 每次都需要重复计算i列左右两侧的最高列的值
        // 提前计算好每列左右两边的最高列
        // dp[i][0]表示第i列左边最高列的值
        // dp[i][1]表示第i列右边最高列的值
        int[][] dp = new int[n][2];

        // 初始值
        // 对于第一列左边的最高列就是他自己
        dp[0][0] = height[0];
        // 对于最后一列右边的做高列就是他自己
        dp[n - 1][1] = height[n - 1];

        // 状态转移
        // 对于左边最高列，从第一列开始正向遍历
        // dp[i][0]=max(dp[i-1][0], heigh[i])
        // 对于右边最高列，从最后一列开始往前倒序遍历
        // dp[i][1]=max(dp[i+1][1], height[i])

        // 求左边最高列
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], height[i]);
        }
        // 求右边最高列
        for (int i = n - 2; i >= 0; i--) {
            dp[i][1] = Math.max(dp[i + 1][1], height[i]);
        }
        // 求每列能接多少水，第一列和最后一列无法单独接水
        for (int i = 1; i < n - 1; i++) {
            if (Math.min(dp[i][0], dp[i][1]) > height[i]) {
                res += Math.min(dp[i][0], dp[i][1]) - height[i];
            }
        }
        return res;
    }
}