package leetcode.array.medium;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/9/8
 * Time: 16:49
 * Description:
 */

public class _667_BeautifulArrangementII {

    public static void main(String[] args) {
        // System.out.println(Arrays.toString(constructArray(3, 1)));
        // System.out.println(Arrays.toString(constructArray(3, 2)));
        System.out.println(Arrays.toString(constructArray(10, 3)));
    }

    public static int[] constructArray(int n, int k) {
        // n=10
        // 若 k=1
        // [1,2],3,4,5,6,7,8,9,10  此时差值列表[1]
        // 若 k=2
        // [1,3,2],4,5,6,7,8,9,10  此时差值列表[2,1]
        // 若 k=3
        // [1,4,2,3],5,6,7,8,9,10  此时差值列表[3,2,1]
        // 若 k=4
        // [1,5,2,4,3],6,7,8,9,10  此时差值列表[4,3,2,1]
        // 若 k=5
        // [1,6,2,5,3,4],7,8,9,10  此时差值列表[5,4,3,2,1]
        // 一般的,若有 n 和 k
        // 在[0,k]的范围内,偶数填充[1,2,3,4...],奇数填充[k+1,k,k-1,k-2...],剩下的[k+1,n]范围内顺序填充[k+2,k+3...n]

        int[] res = new int[n];

        int i = 1;
        int index = 0;
        int tempk = k + 1;
        int nextk = k + 2;
        while (index <= k) {
            res[index] = index++ % 2 == 0 ? i++ : tempk--;
        }
        while (index < n) {
            res[index++] = nextk++;
        }
        return res;
    }
}
