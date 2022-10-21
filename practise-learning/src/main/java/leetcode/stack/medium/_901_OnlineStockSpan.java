package leetcode.stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/10/21
 * Time: 09:37
 * Description:
 */

public class _901_OnlineStockSpan {

    /**
     * 单调栈中保存二维记录,分别是第 i 天的下标索引和之前小于等于第 i 天价格的天数
     */
    private Deque<int[]> stack;

    public _901_OnlineStockSpan() {
        this.stack = new ArrayDeque<>();
    }

    public int next(int price) {
        // 新元素入栈时,判断当前栈顶元素是否小于等于入栈元素
        // 如果 stack.peek() <= price 则弹出栈顶元素,直到出现 stack.peek() > price
        // 将所有弹出的栈顶元素所记录的天数累加,然后再加上入栈元素本身的一天,即为入栈元素前面小于等于其价格的天数之和
        // price[1] = sum(stack.pop(1]...) + 1

        int[] element = new int[2];
        element[0] = price;
        element[1] = 1;
        // 当前栈顶元素小于等于入栈元素,依次弹出栈顶元素
        while (!stack.isEmpty() && stack.peekLast()[0] <= price) {
            int[] top = stack.pollLast();
            element[1] += top[1];
        }
        // 入栈元素找到新位置后入栈
        stack.offerLast(element);
        return element[1];
    }
}
