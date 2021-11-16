package leetcode.array.hard;

import java.util.HashSet;

/**
 * Created by n!Ck
 * Date: 2021/11/16
 * Time: 22:09
 * Description:
 */
public class _391_PerfectRectangle {

    public static void main(String[] args) {
        //        int[][] rec1 = new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {3, 2, 4, 4}, {1, 3, 2, 4}, {2, 3, 3, 4}};
        //        System.out.println(isRectangleCover(rec1));
        //        int[][] rec2 = new int[][]{{1, 1, 2, 3}, {1, 3, 2, 4}, {3, 1, 4, 2}, {3, 2, 4, 4}};
        //        System.out.println(isRectangleCover(rec2));
        //        int[][] rec3 = new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {3, 2, 4, 4}};
        //        System.out.println(isRectangleCover(rec3));
        //        int[][] rec4 = new int[][]{{1, 1, 3, 3}, {3, 1, 4, 2}, {1, 3, 2, 4}, {2, 2, 4, 4}};
        //        System.out.println(isRectangleCover(rec4));
        // TODO badcase
        int[][] rec5 = new int[][]{{0, 0, 1, 1}, {0, 0, 2, 1}, {1, 0, 2, 1}, {0, 2, 2, 3}};
        System.out.println(isRectangleCover(rec5));
    }

    public static boolean isRectangleCover(int[][] rectangles) {
        // 大矩形的四个端点各出现一次，小矩形的所有非大矩形端点的其他端点出现两次
        // 所有小矩形面积之和等于大矩形

        // 记录大矩形的四个端点
        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        int top = Integer.MIN_VALUE;
        int bottom = Integer.MAX_VALUE;

        // 记录小矩形的所有端点
        HashSet<String> set = new HashSet<>();
        // 记录小矩形的面积之和
        int areaSum = 0;

        // 遍历所有小矩形
        for (int[] rec : rectangles) {
            // 更新大矩形的四个端点
            // 左端更左
            left = Math.min(rec[0], left);
            // 下端更下
            bottom = Math.min(rec[1], bottom);
            // 右端更右
            right = Math.max(rec[2], right);
            // 上端更上
            top = Math.max(rec[3], top);

            // 计算小矩形面积之和
            areaSum += (rec[2] - rec[0]) * (rec[3] - rec[1]);

            // 记录小矩形的四个端点
            // 左下端点
            String leftBottom = rec[0] + "-" + rec[1];
            // 右下端点
            String rightBottom = rec[2] + "-" + rec[1];
            // 左上端点
            String leftTop = rec[0] + "-" + rec[3];
            // 右上端点
            String rightTop = rec[2] + "-" + rec[3];
            // 内部端点集合存在就移除，
            if (set.contains(leftBottom)) {
                set.remove(leftBottom);
            } else {
                set.add(leftBottom);
            }
            if (set.contains(rightBottom)) {
                set.remove(rightBottom);
            } else {
                set.add(rightBottom);
            }
            if (set.contains(leftTop)) {
                set.remove(leftTop);
            } else {
                set.add(leftTop);
            }
            if (set.contains(rightTop)) {
                set.remove(rightTop);
            } else {
                set.add(rightTop);
            }
        }
        // 大矩形面积
        int area = (right - left) * (top - bottom);
        // 如果是完美矩形，最后内部端点集合只有大矩形的四个端点；且所有小矩形面积之和等于大矩形面积
        String lb = left + "-" + bottom;
        // 右下端点
        String rb = right + "-" + bottom;
        // 左上端点
        String lt = left + "-" + top;
        // 右上端点
        String rt = right + "-" + top;
        if (set.size() == 4
                && set.contains(lb) && set.contains(rb) && set.contains(lt) && set.contains(rt)
                && areaSum == area) {
            return true;
        }
        return false;
    }
}