package daimasuixianglu._8_greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class _14_MergeIntervals {

    public static void main(String[] args) {
        int[][] ints = new int[][]{{1, 4}, {0, 4}};
        int[][] res = merge(ints);
        int a = 1;
    }

    public static int[][] merge(int[][] intervals) {
        // 区间调度问题，贪心策略
        // 按照end排序
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[0]));
        List<int[]> res = new ArrayList<>();
        int n = intervals.length;
        int preStart = intervals[0][0];
        int preEnd = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            // 判断区间start是否在上一个重叠区间的end内
            // <=，[1,4][4,5]也需要合并
            if (start <= preEnd) {
                // 判断当前重叠区间是否需要扩大
                preEnd = Math.max(preEnd, end);
            } else {
                // 如果当前区间不在上一个重叠区间内
                // 上一个重叠区间加入结果集，并更新start和end
                res.add(new int[]{preStart, preEnd});
                preStart = start;
                preEnd = end;
            }
        }
        // 最后一个区间加入结果集
        res.add(new int[]{preStart, preEnd});
        int[][] resArray = new int[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            resArray[i] = res.get(i);
        }
        return resArray;
    }
}
