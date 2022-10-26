package leetcode.presum.hard;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/10/26
 * Time: 00:14
 * Description:
 */

public class _862_ShortestSubarrayWithSumAtLeastK {

    public static void main(String[] args) {
        System.out.println(shortestSubarray(new int[]{1}, 1));
        System.out.println(shortestSubarray(new int[]{1, 2}, 4));
        System.out.println(shortestSubarray(new int[]{2, -1, 2}, 3));
        System.out.println(shortestSubarray(new int[]{84, -37, 32, 40, 95}, 167));
    }

    public static int shortestSubarray(int[] nums, int k) {
        // 预处理数组得到前缀和
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pre[i + 1] = pre[i] + nums[i];
        }
        // 搜索每个子数组
        // 暴力搜索果然超时
        // int res = Integer.MAX_VALUE;
        // for (int i = 0; i < n; i++) {
        //     for (int j = 0; j <= i; j++) {
        //         // 计算当前子数组的和
        //         if (pre[i + 1] - pre[j + 1 - 1] >= k) {
        //             res = Math.min(res, i - j + 1);
        //         }
        //     }
        // }
        // return res == Integer.MAX_VALUE ? -1 : res;

        // 滑动窗口搜索
        // 因为窗口内元素不满足单调性(存在负数),所以窗口内可能存在更优解,滑动窗口失效
        // int res = Integer.MAX_VALUE;
        // int l = 0, r = 0;
        // while (r < n) {
        //     int sum = pre[r + 1] - pre[l + 1 - 1];
        //     if (sum >= k) {
        //         res = Math.min(res, r - l + 1);
        //         // 尝试缩短左端点
        //         l++;
        //     } else {
        //         // 尝试扩大右端点
        //         r++;
        //     }
        // }
        // return res == Integer.MAX_VALUE ? -1 : res;

        // 考虑使用某个集合保存前缀和的下标
        // 考虑集合尾部的元素是 s[j] 时,若存在 s[i] <= s[j], 且 i > j
        // 若将来的元素 s[n] 满足 s[n] - s[j] >= k,那么 s[n] - s[i] 也一定 >= k
        // 因为 j 的前缀和较大,i 的前缀和较小,如果 n 与 j 的前缀和之差 >= k,那么 n 与前缀和更小的 i 的差也一定 >= k
        // 此时集合保存 s[j] 将没有意义,把 s[j] 从集合尾部移除

        // 考虑集合头部的元素是 s[j] 时,若 s[i] - s[j] >= k,且 i > j,则说明结合头部元素 s[j] 找到了符合题意的子数组尾端点 s[i]
        // 那么将来的元素 s[n] , n > i,若满足 s[n] - s[j] >= k,其子数组长度也一定大于 s[i] - s[j] 的长度
        // 所以一旦头部元素 s[j] 找到了满足条件的 s[i] ,就可以把头部 s[j] 移除掉,更新最小子数组的长度

        // 考虑到在集合头部和尾部移除元素,符合双端队列的性质

        int res = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<>();
        // 遍历所有前缀和元素,按照条件依次进入双端队列
        for (int i = 0; i <= n; i++) {
            while (!deque.isEmpty() && pre[deque.peekLast()] >= pre[i]) {
                deque.pollLast();
            }
            while (!deque.isEmpty() && pre[i] - pre[deque.peekFirst()] >= k) {
                res = Math.min(res, i - deque.peekFirst());
                deque.pollFirst();
            }
            deque.offerLast(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
