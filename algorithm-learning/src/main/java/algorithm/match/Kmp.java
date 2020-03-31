package algorithm.match;

/**
 * Created by n!Ck
 * Date: 2018/12/26
 * Time: 4:37 PM
 * Description:
 */
public class Kmp {
    public static void main(String[] args) {
        String searchStr = "BBC ABCDAB ABCDABCDABDE";
        String patternStr = "ABCDABD";
        System.out.println(KMP(searchStr, patternStr));
    }

    private static int KMP(String searchStr, String patternStr) {
        char[] searchCharArray = searchStr.toCharArray();
        char[] patternCharArray = patternStr.toCharArray();

        // 主串中指针位置
        int i = 0;
        // 模式串中指针位置
        int j = 0;
        // next数组
        int[] next = getNext(patternStr);

        while (i < searchCharArray.length && j < patternCharArray.length) {
            // 当j = -1时，需要移动i，如果主串和模式串对应位置相等，则分别移动两个指针
            if (j == -1 || searchCharArray[i] == patternCharArray[j]) {
                i++;
                j++;
            }
            // 如果模式串位置j失配，则按照next数组移动至模式串的位置k
            else {
                j = next[j];
            }
        }

        if (j == patternCharArray.length) {
            return i - j;
        } else {
            return -1;
        }
    }

    private static int[] getNext(String patternStr) {
        char[] charArray = patternStr.toCharArray();
        int[] next = new int[charArray.length];

        // 初始条件
        int j = 0;
        int k = -1;
        next[0] = -1;

        while (j < charArray.length - 1) {
            if (k == -1 || charArray[j] == charArray[k]) {
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }

    /**
     * KMP求next复习
     */
    private static int[] getNext2(String patternStr) {
        char[] charArray = patternStr.toCharArray();
        int[] next = new int[charArray.length];

        // 初始条件
        int j = 1; //对比上面的算法
        int k = 0; //对比上面的算法
        next[0] = 0; //对比上面的算法

        while (j < charArray.length) { //对比上面的算法
            if (k == 0 || charArray[j] == charArray[k]) {
                // k == 0 表示当前k+1位置的前字符串的最长重复子串为0，所以当前k+1位置的next值为0 + 1
                next[++j] = ++k;
            } else {
                k = next[k];
            }
        }
        return next;
    }
}
