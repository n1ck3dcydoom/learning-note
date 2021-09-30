package leetcode.array.medium;

public class _223_RectangleArea {

    public static void main(String[] args) {
        System.out.println(computeArea(-2,
                -2,
                2,
                2,
                -3,
                -3,
                3,
                -1));
    }


    public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {

        // 两个矩形面积减去重叠部分面积

        // 矩形a面积
        int a = (ax2 - ax1) * (ay2 - ay1);
        // 矩形b面积
        int b = (bx2 - bx1) * (by2 - by1);
        // 重叠部分面积
        int c = 0;
        // 重叠部分矩形四条边的关系由a，b坐标确定
        // 左边界，由ab左边界的较大值确定
        int cLeft = Math.max(ax1, bx1);
        // 右边界，由ab右边界的较小值确定
        int cRight = Math.min(ax2, bx2);
        // 上边界，由ab上边界较小值确定
        int cTop = Math.min(ay2, by2);
        // 下边界，由ab下边界较大值确定
        int cButton = Math.max(ay1, by1);
        // 最后判断一下重叠部分左边界必须比右边界更小，下边界必须比上边界更小
        if (cLeft >= cRight || cButton >= cTop) {
            return a + b;
        }
        return a + b - ((cRight - cLeft) * (cTop - cButton));
    }
}