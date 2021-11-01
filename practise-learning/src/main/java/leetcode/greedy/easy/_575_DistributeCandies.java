package leetcode.greedy.easy;

import java.util.HashSet;

public class _575_DistributeCandies {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 1, 2, 2, 3, 3};
        System.out.println(distributeCandies(nums1));
        int[] nums2 = new int[]{1, 1, 2, 3};
        System.out.println(distributeCandies(nums2));
        int[] nums3 = new int[]{6, 6, 6, 6};
        System.out.println(distributeCandies(nums3));
    }

    public static int distributeCandies(int[] candyType) {
        int n = candyType.length;
        // 吃掉的个数
        int k = n / 2;
        // 统计糖果种类
        HashSet<Integer> set = new HashSet<>();
        for (int i : candyType) {
            set.add(i);
        }
        // 糖果种数m大于等于k，也只能吃到k种糖
        // 糖果种数小于k，最多也只能吃到m种糖
        // 有点贪心的味道
        return Math.min(k, set.size());
    }
}