package leetcode.array.easy;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/9/14
 * Time: 14:46
 * Description:
 */

public class _1619_MeanOfArrayAfterRemovingSomeElements {

    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int sum = 0;
        for (int i = n / 20; i < (n * 19) / 20; i++) {
            sum += arr[i];
        }
        return sum / (n * 0.9);
    }
}
