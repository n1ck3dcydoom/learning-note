package leetcode.array.medium;

/**
 * Created by n!Ck
 * Date: 2022/11/2
 * Time: 00:25
 * Description:
 */

public class _1620_CoordinateWithMaximumNetworkQuality {

    public static void main(String[] args) {
        System.out.println(bestCoordinate(new int[][]{{42, 0, 0}}, 7));
    }

    public static int[] bestCoordinate(int[][] towers, int radius) {
        // 直接枚举每个点的信号强度
        // 首先要确定坐标的搜索范围,由于信号存在辐射,需要找到最左下角的塔和最右上角的塔
        // 得到 [xmin, ymin] 和 [xmax, ymax]
        // x 轴的搜索范围 = [xmin-r, xmax+r]
        // y 轴的搜索范围 = [ymin-r, ymax+y]
        int xmin = Integer.MAX_VALUE, ymin = Integer.MAX_VALUE;
        int xmax = Integer.MIN_VALUE, ymax = Integer.MIN_VALUE;
        int n = towers.length;
        for (int[] t : towers) {
            xmin = Math.min(xmin, t[0]);
            xmax = Math.max(xmax, t[0]);
            ymin = Math.min(ymin, t[1]);
            ymax = Math.max(ymax, t[1]);
        }
        // 为了保证结果符合字典序最小,从左往右,从下往上搜索
        // 题目规定了坐标非负要从 [0,0] 开始搜索
        // 反正都不能超过 50,即使塔能够辐射到 50 开外的坐标,也可以跳过
        int max = Integer.MIN_VALUE;
        int[] res = new int[2];
        for (int i = 0; i <= 50; i++) {
            for (int j = 0; j <= 50; j++) {
                // 遍历所有塔,计算当前坐标的信号强度
                int tmp = 0;
                for (int[] t : towers) {
                    // 判断当前塔是否能够覆盖到当前坐标
                    int d2 = (t[0] - i) * (t[0] - i) + (t[1] - j) * (t[1] - j);
                    if (d2 <= radius * radius) {
                        tmp += Math.floor(t[2] / (1 + Math.sqrt((float) d2)));
                    }
                }
                // 大于才更新点坐标
                // 等于的话后面的坐标字典序是大于前面坐标的
                if (tmp > max) {
                    max = tmp;
                    res[0] = i;
                    res[1] = j;
                }
            }
        }
        return res;
    }
}
