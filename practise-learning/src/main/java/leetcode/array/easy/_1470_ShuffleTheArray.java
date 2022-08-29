package leetcode.array.easy;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/8/29
 * Time: 09:04
 * Description:
 */

public class _1470_ShuffleTheArray {

    public static void main(String[] args) {
        int[] shuffle = shuffle(new int[]{2, 5, 1, 3, 4, 7}, 3);
        System.out.println(Arrays.toString(shuffle));
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] res = new int[2 * n];
        for (int i = 0, j = 0; i < n; i++) {
            res[j++] = nums[i];
            res[j++] = nums[i + n];
        }
        return res;
    }
}
