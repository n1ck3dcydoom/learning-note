package daimasuixianglu._5_stackAndQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _7_TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = new int[]{4, 1, -1, 2, -1, 2, 3};
        int[] res = topKFrequent(nums, 2);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // hash+优先队列

        // hash统计词频
        Map<Integer, Integer> hash = new HashMap<>();
        for (int num : nums) {
            hash.put(num, hash.getOrDefault(num, 0) + 1);
        }
        // 全部加入优先队列(大根堆)
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((i1, i2) -> i2[1] - i1[1]);
        for (Map.Entry<Integer, Integer> entry : hash.entrySet()) {
            int[] node = new int[2];
            node[0] = entry.getKey();
            node[1] = entry.getValue();
            maxHeap.offer(node);
        }
        // 弹出k个堆顶元素
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll()[0];
        }
        return res;
    }
}
