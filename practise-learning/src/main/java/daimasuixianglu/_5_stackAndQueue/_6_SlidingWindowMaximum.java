package daimasuixianglu._5_stackAndQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class _6_SlidingWindowMaximum {

    public static void main(String[] args) {
        int[] nums = new int[]{-7, -8, 7, 5, 7, 1, 6, 0};
        int[] res = maxSlidingWindow1(nums, 4);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        // 暴力模拟
        int n = nums.length;
        int[] res = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            int tmax = nums[i];
            for (int j = i + 1; j < i + k; j++) {
                tmax = Math.max(tmax, nums[j]);
            }
            res[i] = tmax;
        }
        return res;
    }

    public static int[] maxSlidingWindow1(int[] nums, int k) {
        // 单调队列
        // 单调递减队列，队列内部元素满足单调递减顺序，这样出队的时候就是最大值
        int n = nums.length;
        int[] res = new int[n - k + 1];

        MonotoneQueue monotoneQueue = new MonotoneQueue();
        // 先放入前k个元素
        for (int i = 0; i < k; i++) {
            monotoneQueue.push(nums[i]);
        }
        res[0] = monotoneQueue.head();
        // 便利剩下的n-k个元素
        for (int i = 1; i <= n - k; i++) {
            // 移除i-1
            monotoneQueue.poll(nums[i - 1]);
            // 新增i+k
            monotoneQueue.push(nums[i + k - 1]);
            // 优先队列头部为当前窗口的最大值
            res[i] = monotoneQueue.head();
        }

        return res;
    }
}

class MonotoneQueue {
    Deque<Integer> queue = new ArrayDeque<>();

    public MonotoneQueue() {

    }

    public void push(int x) {
        // 进入单调递减队列时，如果入队元素比队尾元素更大
        // 则弹出队尾元素，直到入队元素小于或者等于队尾元素
        // 保证队列里面的元素单调递减
        while (!this.queue.isEmpty() && this.queue.peekLast() < x) {
            this.queue.pollLast();
        }
        this.queue.addLast(x);
    }

    public void poll(int x) {
        if (!this.queue.isEmpty() && this.queue.peekFirst() == x) {
            this.queue.pollFirst();
        }
    }

    public int head() {
        return this.queue.peekFirst();
    }
}
