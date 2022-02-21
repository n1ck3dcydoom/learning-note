package daimasuixianglu._8_greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

public class _11_MinimumNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {
        // [[-2147483646,-2147483645],[2147483646,2147483647]]
        int[][] ps = new int[][] { { -2147483646, -2147483645 }, { 2147483646, 2147483647 } };
        int a = findMinArrowShots(ps);
        System.out.println(a);
    }

    public static int findMinArrowShots(int[][] points) {
        // 区间调度问题，寻找重叠区间的个数

        // 把所有区间[s,e]按照e从小到大排序
        Arrays.sort(points, Comparator.comparingInt(p -> p[1]));
        // 1、以第1个区间的结束点当作end
        // 2、寻找后面每个区间是否和end相交，如果相交则移除
        // 3、遇到不相交的区间后，重复1~2，直到所有区间都被访问
        int res = 1;
        // 第一个区间的结尾当作重叠区间的end
        int end = points[0][1];
        // 遍历所有区间
        for (int[] p : points) {
            int start = p[0];
            // 如果当前区间的起点start已经大于上一个区间的end了
            // 则说明遇到下一个非重叠区间
            // 否则说明当前区间和上一个区间仍然重叠
            if (start > end) {
                res++;
                // 重置重叠区间的end
                end = p[1];
            }
        }
        return res;
    }
}
