package leetcode.greedy.medium;

import java.util.Arrays;

public class _475_Heaters {

    public static void main(String[] args) {
        int[] ho1 = new int[]{1, 2, 3};
        int[] he1 = new int[]{2};

        // int[] ho2 = new int[]{1, 2, 3, 4};
        // int[] he2 = new int[]{1, 4};
        //
        // int[] ho3 = new int[]{1, 5};
        // int[] he3 = new int[]{2};

        System.out.println(findRadius(ho1, he1));
        // System.out.println(findRadius(ho2, he2));
        // System.out.println(findRadius(ho3, he3));
    }

    public static int findRadius(int[] houses, int[] heaters) {
        // 从房子i开始往两侧寻找最近的加热器，记录距离di
        // 遍历每个距离di求最大值(要求加热器能够覆盖到所有房子)

        // 排序
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int n1 = houses.length;
        int n2 = heaters.length;

        int res = 0;
        for (int t : houses) {
            int tr = 0;
            // 二分查找t的左右两个加速器
            // 左侧的加热器
            int l = 0;
            // 右侧的加热器
            int r = n2 - 1;
            // 查找区间[l,r]，结束时l=r+1，查找区间[r+1,r]为空
            while (l <= r) {
                int m = l + (r - l) / 2;
                if (heaters[m] == t) {
                    l = m;
                    break;
                } else if (heaters[m] < t) {
                    l = m + 1;
                } else if (heaters[m] > t) {
                    r = m - 1;
                }
            }

        }
        return res;
    }
}
