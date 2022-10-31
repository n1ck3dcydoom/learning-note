package leetcode.array.easy;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/10/31
 * Time: 09:42
 * Description:
 */

public class _1694_ReformatPhoneNumber {

    public static void main(String[] args) {
        System.out.println(reformatNumber("1-23-45 6"));
        System.out.println(reformatNumber("123 4-567"));
        System.out.println(reformatNumber("123 4-5678"));
    }

    public static String reformatNumber(String number) {
        // do not use String.replaceAll() method, jus double pointer traverse
        char[] cs = number.toCharArray();
        int n = cs.length;
        char[] res = new char[2 * n];
        int k = 0, j = 0;
        for (char c : cs) {
            if (c >= '0' && c <= '9') {
                res[j++] = c;
                if (++k == 3) {
                    k = 0;
                    res[j++] = '-';
                }
            }
        }
        // remove last '-'
        if (res[j - 1] == '-') {
            res[--j] = '\0';
        }
        // last array of number must be -xxx-x or -xxx-xx or -xxx
        // -xxx do nothing
        // -xxx-x => -xx-xx
        // -xxx-xx => do nothing
        // remember j always point []res next char
        if (res[j - 2] == '-') {
            // -xx swap('x','-') x
            char tmp = res[j - 2];
            res[j - 2] = res[j - 3];
            res[j - 3] = tmp;
        }
        res = Arrays.copyOfRange(res, 0, j);
        return String.valueOf(res);
    }
}
