package leetcode.array.easy;

import java.util.Arrays;

public class _66_PlusOne {

    public static void main(String[] args) {
        int[] digits = new int[]{1,2,3,4,9,9};
        int[] res = plusOne(digits);
        for (int i = 0; i < res.length; i++) {
            System.out.print(res[i] + " ");
        }
    }

    public static int[] plusOne(int[] digits) {
        int n = digits.length;
        int[] res = new int[n + 1];
        int carry = 1;

        for (int i = n - 1; i >= 0; i--) {
            int t = digits[i] + carry;
            if (t > 9) {
                carry = 1;
                res[i + 1] = 0;
            } else {
                carry = 0;
                res[i + 1] = t;
            }
        }
        res[0] = carry;
        return res[0] == 0 ? Arrays.copyOfRange(res, 1, n + 1) : res;
    }
}