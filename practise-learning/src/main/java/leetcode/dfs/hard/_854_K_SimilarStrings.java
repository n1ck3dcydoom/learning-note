package leetcode.dfs.hard;

/**
 * Created by n!Ck
 * Date: 2022/9/21
 * Time: 22:34
 * Description:
 */

public class _854_K_SimilarStrings {

    private static int res = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // System.out.println(kSimilarity("abc", "bca"));
        // System.out.println(kSimilarity("ab", "ba"));
        System.out.println(kSimilarity("bccaba", "abacbc"));
    }

    public static int kSimilarity(String s1, String s2) {

        dfs(s1.toCharArray(), s2.toCharArray(), 0, 0);
        return res;
    }

    private static void dfs(char[] s1, char[] s2, int index, int step) {
        // 如果当前的搜索次数已经超过了之前的最大搜索次数，则说明当前的路径不是最优解，无需继续搜索下去
        if (step >= res) {
            return;
        }
        // 如果已经查找到末尾，则说明查找结束，更新最小交换次数
        if (index == s1.length) {
            res = Math.min(res, step);
            return;
        }
        // 如果index位置已经相等，则直接从下个位置开始搜索，由于没有交换所以step不变
        if (s1[index] == s2[index]) {
            dfs(s1, s2, index + 1, step);
            return;
        }
        // 开始从index位置往后查找和s2[index]相等的s1[i]
        // 找第一个不相同的位置
        // s1=baaca
        // s2=aacba
        // index=0，时，s1[0]!=s2[0]，此时应当在s1里面找到s1[i]==s2[0]，即i=1,
        // 但是s1[1]==s2[1]这是已经匹配上的字符，为了保证最小交换次数，已经匹配上的不再交换了
        // 所以继续往后面找s1[i]==s2[0]，且s1[i]!=s2[i]的，即i=2
        // 不同的位置，从index开始往后查找s1[i]==s2[index]的i值，但是s1[i]!=s2[index]（即i的位置也已经是相同的了，需要跳过）
        for (int i = index + 1; i < s1.length; i++) {
            // 找到与s2[index]相同的s1[i]
            if (s1[i] == s2[index]) {
                // 交换s1[j]和s1[i]
                swap(s1, index, i);
                // 继续往下搜索
                dfs(s1, s2, index + 1, step + 1);
                // 回溯交换的位置
                swap(s1, index, i);
            }
        }
    }

    private static void swap(char[] s, int x, int y) {
        char t = s[x];
        s[x] = s[y];
        s[y] = t;
    }
}
