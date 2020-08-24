package leetcode.easy.array;

/**
 * @author zhanglei
 * @version 1.0
 * @description 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线
 *              i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它 们 与 x 轴共同构成的容器可以容纳最多的水。
 *              说明：你不能倾斜容器，且 n 的值至少为 2。 来源：力扣（LeetCode）
 *              链接：https://leetcode-cn.com/problems/container-with-most-water
 * @date 2020/8/24 23:00
 **/

public class _11_ContainerWithMostWater {
    public static void main(String[] args) {
        int[] nums = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
        System.out.println(maxArea(nums));
    }

    public static int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1;
        int max = 0;
        while (i < j) {
            // 更新最大面积
            max = Math.max(max, (j - i) * Math.min(height[i], height[j]));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return max;
    }
}
