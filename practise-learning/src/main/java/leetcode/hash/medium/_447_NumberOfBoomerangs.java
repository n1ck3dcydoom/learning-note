package leetcode.hash.medium;

import java.util.HashMap;

public class _447_NumberOfBoomerangs {

    public static void main(String[] args) {
        int[][] points1 = new int[][] { { 0, 0 }, { 1, 0 }, { 2, 0 } };
        int[][] points2 = new int[][] { { 1, 1 }, { 2, 2 }, { 3, 3 } };
        int[][] points3 = new int[][] { { 1, 1 } };

        System.out.println(numberOfBoomerangs(points1));
        System.out.println(numberOfBoomerangs(points2));
        System.out.println(numberOfBoomerangs(points3));

    }

    public static int numberOfBoomerangs(int[][] points) {
        // 三元组(i,j,k) 其中 (i,j)和(i,k)的距离相等
        int res = 0;
        // 外循环遍历所有点，假设都是i点
        for (int i = 0; i < points.length; i++) {
            // 使用hash保存每个距离出现的次数
            HashMap<Integer, Integer> hash = new HashMap<>();
            // 内循环遍历所有点，计算(i,j)，然后从hash中找有没有其他点距离也等于(i,j)的k点
            for (int j = 0; j < points.length; j++) {
                // 计算(i,j)的距离，平面两点间距离公式需要开方，为了保持精度，全部存储平方
                int[] pi = points[i];
                int[] pj = points[j];
                int dis = (pi[0] - pj[0]) * (pi[0] - pj[0]) + (pi[1] - pj[1]) * (pi[1] - pj[1]);
                // 保存当前点的距离次数
                hash.put(dis, hash.getOrDefault(dis, 0) + 1);
            }
            // 假设points中有n个点到points[i]的距离相等，从中按照排列顺序选出两个点即可构成一个不同的回旋镖
            // 从n中选两个构成排列，排列公式An2 = n*(n-1)
            for (int count : hash.values()) {
                res += count * (count - 1);
            }
        }
        return res;
    }
}
