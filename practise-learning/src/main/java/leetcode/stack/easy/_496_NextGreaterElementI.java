package leetcode.stack.easy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class _496_NextGreaterElementI {

    public static void main(String[] args) {
        int[] n1 = new int[]{4, 1, 2};
        int[] n2 = new int[]{1, 3, 4, 2};
        int[] res = nextGreaterElement2(n1, n2);
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;
        int[] res = new int[m];
        // 暴力搜索
        // 遍历nums1
        for (int i = 0; i < m; i++) {
            int num = nums1[i];
            // 在n2中先查找到num的位置pos
            int pos = 0;
            while (pos < n && nums2[pos] != num) {
                pos++;
            }
            // 从pos后面找第一个比nums更大的数
            while (pos < n && nums2[pos] <= num) {
                pos++;
            }
            // 如果找不到pos==n，设置res对应的位置为-1
            // 找到了则设置为nums2[pos]
            if (pos == n) {
                res[i] = -1;
            } else {
                res[i] = nums2[pos];
            }
        }
        return res;
    }

    public static int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        // 预处理nums1
        // 使用hash表存放nums1的每个元素
        // 遍历nums2的时候只用处理nums1出现的元素
        int m = nums1.length;
        int n = nums2.length;
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < m; i++) {
            hash.put(nums1[i], i);
        }

        // 遍历nums2
        int[] res = new int[m];
        for (int i = 0; i < n; i++) {
            if (hash.containsKey(nums2[i])) {
                // 在nums1中的元素才往右边寻找下一个更大的数
                int pos = i + 1;
                while (pos < n && nums2[pos] <= nums2[i]) {
                    pos++;
                }
                if (pos == n) {
                    res[hash.get(nums2[i])] = -1;
                } else {
                    res[hash.get(nums2[i])] = nums2[pos];
                }
            }
        }
        return res;
    }

    public static int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        // 形如下一个更大的、下一个更小的类似的字眼，可以使用单调栈解决
        // 单调栈就是元素出栈的顺序如果是单调的，那么这个栈就是个单调栈
        // 出栈顺序是单调增，那么栈就是个单调递增栈
        // 同理有单调递减栈

        int m = nums1.length;
        int n = nums2.length;
        // 预处理nums1
        HashMap<Integer, Integer> hash = new HashMap<>();
        for (int i = 0; i < m; i++) {
            hash.put(nums1[i], i);
        }
        // 构造一个单调递增栈
        Deque<Integer> upStack = new ArrayDeque<>();
        int[] res = new int[m];
        // 遍历nums2
        // 栈是倒着出栈的，所以遍历nums2的顺序是倒序的，这样出栈顺序就是顺序的
        for (int i = n - 1; i >= 0; i--) {
            // 栈顶元素如果比当前元素更小或者等于，则弹出
            // 这样出栈的顺序是单调递增的，即小的更先出去
            // 1,3,4,2
            while (!upStack.isEmpty() && nums2[upStack.peekLast()] <= nums2[i]) {
                upStack.pollLast();
            }
            // 弹栈操作结束后，栈顶元素就是严格大于当前元素的下一个更大元素
            if (hash.containsKey(nums2[i])) {
                // 栈顶没有元素，则说明没有元素大于当前元素
                if (upStack.isEmpty()) {
                    res[hash.get(nums2[i])] = -1;
                } else {
                    // 否则栈顶元素就是当前元素的下一个更大的数
                    res[hash.get(nums2[i])] = nums2[upStack.peekLast()];
                }
            }
            // 当前元素入栈(实际上压入栈的是数组的索引)
            upStack.addLast(i);
        }

        return res;
    }
}