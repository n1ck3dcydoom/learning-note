package nowcode.top101;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 11:49
 * Description:
 */

public class _45_滑动窗口的最大值 {

    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        // 区间内求最大值，考虑使用单调队列维护滑动窗口
        Deque<Integer> queue = new ArrayDeque<>();
        int n = num.length;
        // 元素有 n 个，窗口大小为 size，则窗口有 n - size + 1 个
        ArrayList<Integer> res = new ArrayList<>();
        if (size == 0) {
            return res;
        }
        // 遍历原数组
        for (int i = 0; i < n; i++) {
            // 元素加入单调队列，单调递减队列，对头元素永远是区间的最大值
            while (!queue.isEmpty() && num[queue.peekLast()] < num[i]) {
                queue.pollLast();
            }
            // 新加入的元素符合队列的单调性后入队
            queue.offerLast(i);
            // 滑动窗口的区间为 [i - size + 1, i] 判断队头元素是否处于区间内
            // 如果队头元素已经不在滑动窗口内了，则弹出队头元素
            if (i - size + 1 > queue.peekFirst()) {
                queue.pollFirst();
            }
            // 判断窗口是否形成，对于前 size - 1 个元素还未构成一个完整的滑动窗口
            // 当 i 从 size - 1
            if (i >= size - 1) {
                res.add(num[queue.peekFirst()]);
            }
        }
        return res;
    }
}
