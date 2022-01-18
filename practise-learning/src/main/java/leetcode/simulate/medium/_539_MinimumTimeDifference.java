package leetcode.simulate.medium;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _539_MinimumTimeDifference {

    public static void main(String[] args) {
        // List<String> t1 = Arrays.asList("00:00", "23:59", "00:00");
        // List<String> t2 = Arrays.asList("23:59", "00:00");
        List<String> t3 = Arrays.asList("05:31", "22:08", "00:35");
        System.out.println(findMinDifference(t3));
        // System.out.println(findMinDifference(t1));
        // System.out.println(findMinDifference(t2));
    }

    public static int findMinDifference(List<String> timePoints) {
        // 排序
        List<Integer> mins = timePoints.stream().map(s -> getMin(s)).sorted((m1, m2) -> m1 - m2)
                .collect(Collectors.toList());
        int res = Integer.MAX_VALUE;
        // 两两对比
        for (int i = 0; i < mins.size() - 1; i++) {
            res = Math.min(res, mins.get(i + 1) - mins.get(i));
        }
        // 首尾对比
        res = Math.min(res, 24 * 60 - mins.get(mins.size() - 1) + mins.get(0));
        return res;
    }

    private static int getMin(String time) {
        String[] splits = time.split(":");
        int h = Integer.parseInt(splits[0]);
        int m = Integer.parseInt(splits[1]);
        int min = h * 60 + m;
        return min == 0 ? 24 * 60 : min;
    }
}
