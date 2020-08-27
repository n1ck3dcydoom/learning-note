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
        System.out.println(trap(nums));
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
     * 韦恩图
     * @param height
     * @return
     */
    public static int trap0(int[] height){

    }
}