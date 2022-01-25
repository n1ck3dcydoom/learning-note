package daimasuixianglu._4_StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class _1_ImplementQueueUsingStacks {

    // 输入栈
    private Deque<Integer> s1;
    // 输出栈
    private Deque<Integer> s2;

    public _1_ImplementQueueUsingStacks() {
        this.s1 = new ArrayDeque<>();
        this.s2 = new ArrayDeque<>();
    }

    public void push(int x) {
        // 压入输入栈
        s1.addLast(x);
    }

    public int pop() {
        // 当前输出栈不为空，弹出输出栈栈顶元素
        if (!s2.isEmpty()) {
            return s2.pollLast();
        }
        // 当前输出栈为空，输入栈全部压入输出栈
        while (!s1.isEmpty()) {
            s2.addLast(s1.pollLast());
        }
        // 不允许对空队列做pop操作
        if (s2.isEmpty()) {
            return -1;
        }
        return s2.pollLast();
    }

    public int peek() {
        // 调用pop拿到队头元素，然后再放回去
        int peek = this.pop();
        this.s2.addLast(peek);
        return peek;
    }

    public boolean empty() {
        return this.s1.isEmpty() && this.s2.isEmpty();
    }
}
