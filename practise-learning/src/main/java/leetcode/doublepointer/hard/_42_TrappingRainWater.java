package leetcode.doublepointer.hard;

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
        // 双指针
        // 对于每个列，依次往左右两端遍历，找到左边最高的列和右边最高的列
        // 为什么左右两边最高的列，因为根据短板效应，能装多少水取决于最短的板块
        // 既然求解的是当前列能装的水，那么当前列就应该作为最短的板块，这样左右两边更高的板块围起来才能装下水

        int n = height.length;
        int res = 0;

        // 遍历每一列，求每一列上能装的水
        for (int i = 0; i < n; i++) {
            // 往左寻找最高的列
            int p = i - 1;
            int leftMax = height[i];
            while (p >= 0) {
                leftMax = Math.max(leftMax, height[p--]);
            }
            // 往右寻找最高的列
            int q = i + 1;
            int rightMax = height[i];
            while (q < n) {
                rightMax = Math.max(rightMax, height[q++]);
            }

            // 求左右两个最高列的较小列
            int tempMin = Math.min(leftMax, rightMax);
            // 如果较小列都第i列更小，根据短板效应，第i列不是最短板，所以第i列上无法装水
            // 同理，较小列等于第i列，也无法装水
            // 如果较小列大于第i列，那么第i列能装的水体积就等于较小列减去第i列的高度
            if (tempMin > height[i]) {
                res += tempMin - height[i];
            }
        }
        return res;
    }
}