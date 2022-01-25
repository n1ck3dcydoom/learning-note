package daimasuixianglu._4_StackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class _2_ImplementStackUsingQueues {

    // 操作队列
    private Deque<Integer> q1;
    // 备份队列
    private Deque<Integer> q2;

    public _2_ImplementStackUsingQueues() {
        this.q1 = new ArrayDeque<>();
        this.q2 = new ArrayDeque<>();
    }

    public void push(int x) {
        // 压入操作队列
        this.q1.addLast(x);
    }

    public int pop() {
        if (this.empty()) {
            return -1;
        }
        // 操作队列除了最后一个元素以外，全部压入备份队列
        while (this.q1.size() > 1) {
            this.q2.addLast(this.q1.pollFirst());
        }
        // 得到需要弹出的栈顶元素
        int pop = this.q1.pollFirst();
        // 将备份队列全部元素全部放回操作队列中
        while (!this.q2.isEmpty()) {
            this.q1.addLast(this.q2.pollFirst());
        }
        return pop;
    }

    public int top() {
        if (this.empty()) {
            return -1;
        }
        int top = this.pop();
        // 放回操作队列
        this.q1.addLast(top);
        return top;
    }

    public boolean empty() {
        return this.q1.isEmpty() && this.q2.isEmpty();
    }
}
