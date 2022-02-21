package daimasuixianglu._8_greedy;

import java.util.Arrays;
import java.util.Comparator;

public class _12_NonOverlappingIntervals {

    public int eraseOverlapIntervals(int[][] intervals) {
        // 区间调度问题，贪心策略，找到所有重叠区间的个数
        // 按照区间end排序
        Arrays.sort(intervals, Comparator.comparingInt(i -> i[1]));

        // 至少有一个区间不重叠
        int res = 1;
        // 第一个区间的结束记作重叠区间的end
        int end = intervals[0][1];
        for (int[] interval : intervals) {
            int start = interval[0];
            // 如果当前区间的start大于或者等于end
            // 则说明当前区间和上一个重叠区间不重叠
            // 注意是>=，[1,2][2,3]看作是两个互不重叠的区间
            if (start >= end) {
                res++;
                // 更新重叠区间的end
                end = interval[1];
            }
        }
        // 找到最大不重叠区间的个数之后，需要删掉的重叠区间个数=n-res
        return intervals.length - res;
    }
}
