package nowcode.top101;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 14:15
 * Description:
 */

public class _46_最小K个数 {

    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        // topK 问题,考虑使用优先队列
        // 最小 k 个数,使用小顶堆保存结果,弹出对顶 k 个元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int i : input) {
            minHeap.offer(i);
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (k-- > 0) {
            res.add(minHeap.poll());
        }
        return res;
    }
}
