package nowcode.top101;

import java.util.Stack;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 11:21
 * Description:
 */

public class _43_包含MIN函数的栈 {

    public static void main(String[] args) {
        MyStack s = new MyStack();
        s.push(-1);
        s.push(2);
        System.out.println(s.min());
        System.out.println(s.top());
        s.pop();
        s.push(1);
        System.out.println(s.top());
        System.out.println(s.min());
    }

    static class MyStack {
        Stack<Integer> s = new Stack<>();
        // 最小栈保存最小值
        Stack<Integer> mins = new Stack<>();

        public void push(int node) {
            s.add(node);
            if (mins.isEmpty()) {
                mins.add(node);
            } else {
                if (node >= mins.peek()) {
                    mins.add(mins.peek());
                } else {
                    mins.add(node);
                }
            }
        }

        public void pop() {
            s.pop();
            mins.pop();
        }

        public int top() {
            return s.peek();
        }

        public int min() {
            return mins.peek();
        }
    }
}
