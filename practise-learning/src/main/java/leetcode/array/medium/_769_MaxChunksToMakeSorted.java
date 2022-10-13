package leetcode.array.medium;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/10/13
 * Time: 09:30
 * Description:
 */

public class _769_MaxChunksToMakeSorted {

    public static void main(String[] args) {
        // System.out.println(maxChunksToSorted(new int[]{1, 0, 2, 3, 4}));
        System.out.println(maxChunksToSorted(new int[]{4, 3, 1, 2, 0}));
    }

    public static int maxChunksToSorted(int[] arr) {
        // 为了能够切分区间,区间的最大值和其数组索引应当相等,才能符合生序排列
        int res = 0;

        for (int i = 0; i < arr.length; ) {
            for (int j = i + 1; j <= arr.length; j++) {
                int[] sub = Arrays.copyOfRange(arr, i, j);
                // 对子区间排序后,检查其最大值和最大值索引是否相等
                Arrays.sort(sub);
                int max = sub[sub.length - 1];
                if (max == j - 1) {
                    res++;
                    i = j;
                    break;
                }
            }
        }
        return res;
    }
}
