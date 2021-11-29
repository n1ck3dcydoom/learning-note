package leetcode.search.hard;

import java.util.*;

/**
 * Created by n1ck3dcydoom
 * Date: 2021/11/29
 * Time: 22:23
 * Description: TODO
 */
public class _786_K_thSmallestPrimeFraction {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 5};
        //        int[] res = kthSmallestPrimeFraction(arr, 3);
        int[] res = solution1(arr, 3);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
        // 暴力打表
        List<int[]> list = new ArrayList<>();
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                list.add(new int[]{arr[i], arr[j]});
            }
        }
        // 排序
        list.sort((a1, a2) -> {
            double d1 = (double) a1[0] / a1[1];
            double d2 = (double) a2[0] / a2[1];
            return Double.compare(d1, d2);
        });
        return list.get(k - 1);
    }

    public static int[] solution1(int[] arr, int k) {
        // 对于特定的arr[i]
        // 根据严格递增的条件以及i<j，有如下表达式成立
        // arr[0]/arr[i] < arr[1]/arr[i] < arr[2]/arr[j] < ... < arr[i-1]/arr[i];
        // 其中0<=i<n
        // 可以得到n-1个类似结果
        // arr[0]/arr[1]
        // arr[0]/arr[2] < arr[1]/arr[2]
        // arr[0]/arr[3] < arr[1]/arr[3] < arr[2]/arr[3]
        // ...
        // arr[0]/arr[n-1] < arr[1]/arr[n-1] < ... < arr[n-2]/arr[n-1]
        // 最终目的就是求上面n-1个列表中的第k小的数
        // 使用优先队列
        // 每次将这n-1个列表最前面的元素入队，然后弹出最小的元素
        // 下一次将弹出元素所在列表的后面一个元素入队，然后继续弹出最小的元素
        // 重复上述操作k次后得到第k小的元素

        int n = arr.length;
        // 类型是int[]，初始化最小堆需要指定比较方法
        // a1/b1 < a2/b2 -> a1*b2 < a2*b1
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a1, a2) -> arr[a1[0]] * arr[a2[1]] - arr[a2[0]] * arr[a1[1]]);
        // n-1个串的第一个元素入队
        for (int i = 1; i < n; i++) {
            minHeap.offer(new int[]{0, i});
        }
        int[] poll = null;
        while (k > 0) {
            // 最小堆堆顶元素出队
            poll = minHeap.poll();
            k--;
            // 出队后的元素所在列表的下一个元素入堆
            int a = poll[0];
            int b = poll[1];
            // 判断当前列表后面还有没有元素，如果已经为空了，则继续从堆里面弹出最小元素
            if (a + 1 < b) {
                minHeap.offer(new int[]{a + 1, b});
            }
        }
        return new int[]{arr[poll[0]], arr[poll[1]]};
    }
}
