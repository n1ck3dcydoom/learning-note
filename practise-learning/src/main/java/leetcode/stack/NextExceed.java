package leetcode.stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * @version 1.0
 * @description 给一个数组，返回一个大小相同的数组。返回的数组的第i个位置的值应当是，对于原数组中的第i个元素，至少往右走多少步，才能遇到一个比自己大的元素（如果之后没有比自己大的元素，或者已经是最后一个元素，则在返回数组的对应位置放上-1）。
 * <p>
 * 简单的例子：
 * <p>
 * input: 5,3,1,2,4
 * <p>
 * return: -1 3 1 1 -1
 * <p>
 * explaination: 对于第0个数字5，之后没有比它更大的数字，因此是-1，对于第1个数字3，需要走3步才能达到4（第一个比3大的元素），对于第2和第3个数字，都只需要走1步，就可以遇到比自己大的元素。对于最后一个数字4，因为之后没有更多的元素，所以是-1。
 * @date 2021/5/24 20:15
 **/
public class NextExceed {
    public int[] nextExceed(int[] input) {
        // 定义返回结果
        int[] res = new int[input.length];
        Arrays.fill(res, -1);

        // 定义一个单调递减栈，存放input数组的下标
        Stack<Integer> stack = new Stack<>();

        // 对于单调递减栈来说，栈内元素依次减小

        // 如果当前元素大于栈顶元素，则依次弹出栈顶元素，直到栈顶元素小于等于当前元素
        // 否则当前元素入栈（即当前元素小于或等于栈顶元素）

        for (int i = 0; i < input.length; i++) {
            while (!stack.isEmpty() && input[stack.peek()] < input[i]) {

                // 因为单调递减栈的特性，如果栈顶元素都要比当前元素还要大，可得栈内所有元素都比当前元素大
                // 所以当前元素直接压入索引到栈内

                // 每当判断到栈顶元素比当前元素小的时候，说明栈顶元素遇到了第一个比自己大的数
                // 然而这个当前元素并不知道这个比栈内前面多少个数要大
                // 所以每次栈顶元素需要出栈的时候，都需要更新自己的索引到res数组中

                // 如果一个元素从被压入栈内后，从来没有弹出过，说明后面也没有比它还要大的数

                // 更新res数组，当前元素索引减去栈顶元素索引，得到栈顶元素和当前元素相差的距离
                res[stack.peek()] = i - stack.peek();
                // 弹出栈顶元素
                stack.pop();
            }
            // 把当前元素的下标压入栈内，栈内元素递减
            stack.push(i);
        }
        return res;
    }
}