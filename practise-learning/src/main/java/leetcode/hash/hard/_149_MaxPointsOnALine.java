package leetcode.hash.hard;

import java.util.HashMap;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点。求最多有多少个点在同一条直线上。
 * <p>
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：3
 * <p>
 * 输入：points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * 输出：4
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= points.length <= 300
 * points[i].length == 2
 * -104 <= xi, yi <= 104
 * points 中的所有点 互不相同
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/max-points-on-a-line
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/24 11:58
 **/
public class _149_MaxPointsOnALine {

    public static void main(String[] args) {
        int[][] points = new int[][]{{1, 1}, {3, 2}, {5, 3}, {4, 1}, {2, 3}, {1, 4}};
        System.out.println(maxPoints1(points));
    }

    public static int maxPoints(int[][] points) {
        // 枚举任意两个点i和j，构成一条直线，再枚举其他所有点，检查是否落在这条直线上

        // 如果只有一个点，那么至少有一个点在直线上
        int res = 1;
        // 点数
        int n = points.length;
        // 枚举任意两个点i和j
        for (int i = 0; i < n; i++) {
            // i点 x1,y1
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                // j点 x2,y2
                int[] y = points[j];
                // 这里已经至少有两个点在一条直线上了
                int count = 2;
                for (int k = j + 1; k < n; k++) {
                    // m点 x3,y3
                    int[] m = points[k];

                    // 判断第三个点k是否在直线y=kx+b上，除了带入k点的坐标到方程上
                    // 还可以通过计算ki和kj的斜率是否相等，斜率相等则平行或者重合

                    // 除法存在精度的误差
                    //                    int ki = (m[1] - x[1]) / (m[0] - x[0]);
                    //                    int kj = (m[1] - y[1]) / (m[0] - y[0]);
                    // 根据a/b = c/d 对角相乘可得a*d = b*c
                    int ki = (m[1] - x[1]) * (m[0] - y[0]);
                    int kj = (m[1] - y[1]) * (m[0] - x[0]);
                    if (ki == kj) {
                        count++;
                    }
                }
                res = Math.max(res, count);
            }
        }
        return res;
    }

    public static int maxPoints1(int[][] points) {
        // 枚举任意两个点i和j，构成一条直线，再枚举其他所有点，检查是否落在这条直线上

        // 点数
        int n = points.length;
        if (n < 3) {
            return n;
        }
        int res = 2;
        // 使用hash表保存所有点(每两个)的斜率
        HashMap<Double, Integer> hash = new HashMap<>();
        // 枚举任意两个点i和j
        for (int i = 0; i < n; i++) {
            // i点 x1,y1
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                // j点 x2,y2
                int[] y = points[j];
                // 计算斜率
                // k=dy/dx
                double dy = y[1] - x[1];
                double dx = y[0] - x[0];

                // dx=0，垂直于x轴的直线，假定斜率为Double.MAX
                if (dx == 0) {
                    hash.put(Double.MAX_VALUE, hash.getOrDefault(Double.MAX_VALUE, 2) + 1);
                }
                double k = dy / dx;

                // TODO 处理垂直于x和y轴的情况

                // 从hash里面看当前斜率是否存在
                if (hash.containsKey(k)) {
                    int count = hash.get(k);
                    hash.put(k, count + 1);
                    res = Math.max(res, count + 1);
                } else {
                    hash.put(k, 2);
                }

            }
        }
        return res;
    }
}