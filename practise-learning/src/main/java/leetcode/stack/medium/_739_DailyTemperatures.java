package leetcode.stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用0 来代替。
 * <p>
 * 例如，给定一个列表temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是[1, 1, 4, 2, 1, 1, 0, 0]。
 * <p>
 * 提示：气温 列表长度的范围是[1, 30000]。每个气温的值的均为华氏度，都是在[30, 100]范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/daily-temperatures
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/5/31 23:56
 **/
public class _739_DailyTemperatures {

    public static void main(String[] args) {
        int[] t = new int[]{73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println(dailyTemperatures(t));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[]{};
        }
        int n = temperatures.length;
        int[] res = new int[n];

        // 题目翻译过来就是，从数组后面找到第一个比当前元素更大的数
        // “在一维数组中找第一个满足某种条件的数”的场景就是典型的单调栈应用场景。

        // 巩固一下单调栈
        // 因为是找后面更大的元素，所以遇到后面更小的元素，需要入栈
        // 按照出栈顺序来说，即构造一个单调递增栈

        // 避免直接使用Stack数据结构，练习下Deque双向队列
        Deque<Integer> stack = new ArrayDeque<>();

        // 扫描数组
        for (int i = 0; i < n; i++) {
            // 遇到当前元素严格大于栈顶元素时，需要依次出栈，直到栈顶元素大于当前元素
            while (!stack.isEmpty() && temperatures[stack.peekLast()] < temperatures[i]) {
                // 把当前元素的下标位置，写入对应栈顶元素的下标当中
                int currentIndex = stack.pollLast();
                // 求栈顶元素距离当前元素的距离
                res[currentIndex] = i - currentIndex;
            }
            // 压入栈中
            stack.addLast(i);
        }
        return res;
    }
}