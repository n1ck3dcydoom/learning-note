package leetcode.array.easy;

public class _492_ConstructRectangle {

    public static void main(String[] args) {
        int[] r1 = constructRectangle(4);
        System.out.println(r1[0] + " " + r1[1]);
        int[] r2 = constructRectangle(37);
        System.out.println(r2[0] + " " + r2[1]);
        int[] r3 = constructRectangle(90);
        System.out.println(r3[0] + " " + r3[1]);
    }

    public static int[] constructRectangle(int area) {
        // 算术平方根是乘数最接近的结果
        int w = (int) Math.sqrt(area);
        // 从算术平方根开始往下找其他乘数，直到找到第一个为止
        while (area % w != 0) {
            w--;
        }
        return new int[]{area / w, w};
    }
}