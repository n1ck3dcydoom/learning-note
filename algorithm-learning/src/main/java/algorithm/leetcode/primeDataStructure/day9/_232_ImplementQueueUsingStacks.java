package algorithm.leetcode.primeDataStructure.day9;

import java.util.Stack;

public class _232_ImplementQueueUsingStacks {

    public static void main(String[] args) {
        _232_ImplementQueueUsingStacks queue = new _232_ImplementQueueUsingStacks();
        queue.push1(1);
        System.out.println("null");
        queue.push1(2);
        System.out.println("null");
        System.out.println(queue.peek1());
        System.out.println(queue.pop1());
        System.out.println(queue.empty1());
    }

    private static Stack<Integer> s1 = new Stack<>();
    private static Stack<Integer> s2 = new Stack<>();

    private static int count = 0;

    /** Push element x to the back of queue. */
    public static void push(int x) {
        s1.push(x);
        count++;
    }

    /** Removes the element from in front of queue and returns that element. */
    public static int pop() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int pop = s2.pop();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        count--;
        return pop;
    }

    /** Get the front element. */
    public static int peek() {
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        int pop = s2.peek();
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        return pop;
    }

    /** Returns whether the queue is empty. */
    public static boolean empty() {
        return count == 0;
    }

    /**
     * 优化版，去掉count变量 s1作为入队栈 s2作为出队栈 每次push操作时，直接压入s1 每次pop或peek操作时 先检查队列是否为空
     * !s1.isEmpty() && !s2.isEmpty( 检查s2是否为空
     * 如果s2为空，把s1全部压入s2中，然后返回s2.pop()或者s2.peek() 如果s2不为空，则直接返回s2.pop()或者s2.peek()
     * 
     * @return
     */
    /** Push element x to the back of queue. */
    public static void push1(int x) {
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public static int pop1() {
        if (empty1()) {
            return -1;
        }
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.pop();
        }
        return s2.pop();
    }

    /** Get the front element. */
    public static int peek1() {
        if (empty1()) {
            return -1;
        }
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
            return s2.peek();
        }
        return s2.peek();
    }

    /** Returns whether the queue is empty. */
    public static boolean empty1() {
        return s1.isEmpty() && s2.isEmpty();
    }
}
