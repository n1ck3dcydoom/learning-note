package leetcode.stack.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * <p>
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * <p>
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为 [2,1,5,6,2,3]。
 * <p>
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为 10 个单位。
 * <p>
 * 示例:
 * <p>
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/largest-rectangle-in-histogram
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/31 23:02
 **/
public class _84_LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int n = heights.length;
        int res = 0;

        // 先放入两个哨兵
        int[] h = new int[n + 2];
        System.arraycopy(heights, 0, h, 1, n);
        // 更新长度
        n += 2;

        // 使用单调栈存放数组下标
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(0);

        // i从新数组的第2个元素开始遍历到倒数第2个元素，即除去两边的哨兵不在访问到
        for (int i = 1; i < n; i++) {
            // 由于数组高度一定大于0的，所以左侧的哨兵永远不会出栈，不用再判断栈是否为空
            // 往右边寻找第一个比栈顶元素严格小的数
            while (h[i] < h[stack.peekLast()]) {
                // 弹出当前栈顶元素，计算此时的矩形面积
                // 高
                int ch = h[stack.pollLast()];
                // 宽，不包含栈顶左边的和当前元素的两根柱子
                int w = i - stack.peekLast() - 1;

                res = Math.max(res, ch * w);
            }
            // 如果当前元素大于，压入栈中
            stack.addLast(i);
        }
        return res;
    }
}