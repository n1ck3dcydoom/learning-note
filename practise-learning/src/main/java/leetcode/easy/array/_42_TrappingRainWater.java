package leetcode.easy.array;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 示例:
 * <p>
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/trapping-rain-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2020/8/27 19:32
 **/
public class _42_TrappingRainWater {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(trap0(nums));
    }

    /**
     * 计算每一列的盛水容量 = min(左边最高,右边最高) - 当前列高度
     * 只保存上述计算结果大于0的值
     *
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int n = height.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 往左寻找最高
            int leftMax = 0;
            for (int j = i - 1; j >= 0; j--) {
                leftMax = Math.max(leftMax, height[j]);
            }
            // 往右寻找最高
            int rightMax = 0;
            for (int j = i + 1; j < n; j++) {
                rightMax = Math.max(rightMax, height[j]);
            }
            // 求当前列的水容量
            int temp = Math.min(leftMax, rightMax) - height[i];
            res += Math.max(temp, 0);
        }
        return res;
    }

    /**
     * 动态规划，使用两个中间数组额外保存左边的最高高度，右边的最高高度
     * maxLeft[i] 表示第i列左边的最高高度
     * maxRight[i] 表示第i列右边的最高高都
     * <p>
     * 如何求解maxLeft[i] ?
     * 想要求得maxLeft[i]，如果能够知道第i列左边前一个列的左边最高高度maxLeft[i-1]，和左边前一个列的高度height[i-1]
     * 那么 maxLeft[i] = max(maxLeft[i-1], height[i-1])
     * 同理 maxRight[i] = max(maxRight[i+1], height[i+1])
     */
    private static int trap0(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int n = height.length;
        // 左边的最高高度缓存数组，默认初始化为0
        int[] maxLeft = new int[n];
        // 右边的最高高都缓存数组，默认初始化为0
        int[] maxRight = new int[n];
        // 填充maxLeft，从第二个元素开始遍历，因为第一列永远不能装下水
        for (int i = 1; i < n; i++) {
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i - 1]);
        }
        // 填充maxRight，从倒数第二个元素开始遍历，因为最后一列永远不能装下水
        for (int i = n - 2; i >= 0; i--) {
            maxRight[i] = Math.max(maxRight[i + 1], height[i + 1]);
        }
        // 求解每一列能装的水，计算最终结果
        int res = 0;
        for (int i = 0; i < n; i++) {
            int temp = Math.min(maxLeft[i], maxRight[i]) - height[i];
            res += Math.max(temp, 0);
        }
        return res;
    }

    /**
     * 韦恩图
     */
    public static int trap1(int[] height) {
        return 0;
    }
}