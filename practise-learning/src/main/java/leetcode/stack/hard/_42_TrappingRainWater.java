package leetcode.stack.hard;

import java.util.ArrayDeque;
import java.util.Deque;

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

    public static int trap(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        // 使用单调栈解决问题

        // 考虑第i列，如果小于栈顶元素，说明会形成低洼，有积水，把第i列入栈

        // 考虑第i列，如果大于栈顶元素，栈里面保存的低洼到次结束，计算里面所有低洼的水的体积
        // 然后把当前第i列压入栈

        // 如果第i列大于栈顶元素，则栈顶元素出栈，计算栈顶元素和当前元素之间的水体积
        // 重复判断新的栈顶元素和当前第i列的大小，直到新的栈顶元素大于等于第i列，或者栈为空
        // 然后把当前第i列压入栈，继续遍历

        // 定义单调递增栈
        Deque<Integer> stack = new ArrayDeque<>();

        int n = heights.length;
        int res = 0;
        // 遍历所有列
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peekLast()] < heights[i]) {
                // 栈顶元素出栈，下标操作
                int topIndex = stack.pollLast();
                // 计算当前元素i到新栈顶元素之间的水的体积，需要借助新栈顶元素来计算
                if (!stack.isEmpty()) {
                    // 此处新栈顶元素和当前元素i可以看作是弹出去的旧栈顶元素的左右两端的最大值
                    int tempMin = Math.min(heights[stack.peekLast()], heights[i]);
                    // 这里弹出来的栈顶元素，看作遍历列时的第i列，此时新栈顶元素和当前第i列元素构成了一个区间，而非是遍历列是的某一列
                    // 所以计算水的体积是，需要乘以新栈顶元素和当前第i列元素的区间长度
                    int w = i - stack.peekLast() - 1;
                    res += w * (tempMin - heights[topIndex]);
                }
            }
            stack.addLast(i);
        }

        return res;
    }
}