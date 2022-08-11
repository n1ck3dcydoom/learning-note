package leetcode.string.easy;

/**
 * Created by n!Ck
 * Date: 2022/8/11
 * Time: 09:43
 * Description:
 */

public class _1374_GenerateAStringWithCharactersThatHaveOddCounts {

    public static void main(String[] args) {
        System.out.println(generateTheString(4));
    }

    public static String generateTheString(int n) {
        // 直接贪心,从 a-z 开始遍历,每次填充最大奇数个字符个数
        int[] arr = new int[26];
        int i = 0;
        while (n > 0) {
            if (n % 2 == 0) {
                arr[i++] = n - 1;
                n -= n - 1;
            } else {
                arr[i++] = n;
                n -= n;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 26; j++) {
            if (arr[j] == 0) {
                return sb.toString();
            } else {
                while (arr[j]-- > 0) {
                    sb.append((char) (j + 97));
                }
            }
        }
        return sb.toString();
    }
}
