package leetcode.array.easy;

public class _495_TeemoAttacking {

    public static void main(String[] args) {
        int[] t1 = new int[]{1, 4};
        System.out.println(findPoisonedDuration(t1, 2));
        int[] t2 = new int[]{1, 2};
        System.out.println(findPoisonedDuration(t2, 2));
        int[] t3 = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(findPoisonedDuration(t3, 1));
    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        // 遍历数组攻击时刻t
        // 如果当前时刻t处于上一个持续区间内，则更新新的区间
        // 如果不在上一个区间内，则更新持续时间d
        int res = 0;
        int section = -1;
        for (int t : timeSeries) {
            // 不在上一个区间内
            if (t > section) {
                // 立即增加持续时间
                res += duration;
                // 更新区间
                section = t + duration - 1;
            }
            // 处于上一个区间内
            else {
                // 新的区间和上一个区间重叠的部分不能重复计算
                res += (t + duration - section - 1);
                // 更新区间
                section = t + duration - 1;
            }
        }
        return res;
    }
}
