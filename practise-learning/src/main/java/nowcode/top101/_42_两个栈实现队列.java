package nowcode.top101;

import java.util.Stack;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 11:12
 * Description:
 */

public class _42_两个栈实现队列 {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        // 入队的时候直接压入 s1
        stack1.add(node);
    }

    public int pop() {
        // 出队的时候，s2 不为空则直接栈顶元素出队
        if (!stack2.isEmpty()) {
            int pop = stack2.pop();
            return pop;
        }
        // 如果 s2 为空，则把 s1 全部压入 s2
        while (!stack1.isEmpty()) {
            stack2.add(stack1.pop());
        }
        return stack2.pop();
    }
}
