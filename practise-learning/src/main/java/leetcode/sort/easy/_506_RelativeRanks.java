package leetcode.sort.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _506_RelativeRanks {

    public static void main(String[] args) {
        int[] s1 = new int[]{5, 4, 3, 2, 1};
        int[] s2 = new int[]{10, 3, 8, 9, 4};

        String[] r1 = findRelativeRanks(s1);
        for (String str : r1) {
            System.out.print(str + " ");
        }
        System.out.println("\n");

        String[] r2 = findRelativeRanks(s2);
        for (String str : r2) {
            System.out.print(str + " ");
        }
        System.out.println("\n");
    }

    public static String[] findRelativeRanks(int[] score) {
        // hash记录得分和人的映射关系
        Map<Integer, Integer> hash = new HashMap<>(score.length);
        for (int i = 0; i < score.length; i++) {
            hash.put(score[i], i);
        }
        // 大根堆排序
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i : score) {
            maxHeap.offer(i);
        }
        String[] res = new String[score.length];
        for (int i = 1; i <= score.length; i++) {
            int index = hash.get(maxHeap.poll());
            if (i == 1) {
                res[index] = "Gold Medal";
            } else if (i == 2) {
                res[index] = "Silver Medal";
            } else if (i == 3) {
                res[index] = "Bronze Medal";
            } else {
                res[index] = String.valueOf(i);
            }
        }
        return res;
    }
}
