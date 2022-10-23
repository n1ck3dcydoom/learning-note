package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/10/23
 * Time: 21:14
 * Description:
 */

public class _1768_MergeStringsAlternately {

    public static void main(String[] args) {
        System.out.println(mergeAlternately("ab", "pqrs"));
    }

    public static String mergeAlternately(String word1, String word2) {
        StringBuilder sb = new StringBuilder();
        int m = word1.length();
        int n = word2.length();
        int i = 0;
        while (i < m && i < n) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(i));
            i++;
        }
        if (i == m) {
            sb.append(word2.substring(i));
        } else if (i == n) {
            sb.append(word1.substring(i));
        }
        return sb.toString();
    }
}
