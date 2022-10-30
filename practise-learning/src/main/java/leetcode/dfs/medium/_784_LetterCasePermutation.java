package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/10/30
 * Time: 11:10
 * Description:
 */

public class _784_LetterCasePermutation {

    public static void main(String[] args) {
        System.out.println(letterCasePermutation("a1b2"));
    }

    public static List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList<>();
        // 直接 dfs 搜索
        dfs(s.toCharArray(), new StringBuilder(), 0, res);
        return res;
    }

    /**
     * @param cs    待搜索的字符串数组
     * @param sb    当前搜索路径
     * @param index 当前搜索位置
     * @param res   结果集
     */
    private static void dfs(char[] cs, StringBuilder sb, int index, List<String> res) {
        // 结束搜索的条件是到达末尾
        if (index == cs.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = index; i < cs.length; i++) {
            if (cs[i] < 'A') {
                sb.append(cs[i]);
                if (sb.length() == cs.length) {
                    res.add(sb.toString());
                }
                continue;
            }
            sb.append(cs[i]);
            dfs(cs, sb, i + 1, res);
            while (sb.charAt(sb.length() - 1) < 'A') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.deleteCharAt(sb.length() - 1);

            sb.append(cs[i] >= 'a' ? (char) (cs[i] - 32) : (char) (cs[i] + 32));
            dfs(cs, sb, i + 1, res);
            while (sb.charAt(sb.length() - 1) < 'A') {
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.deleteCharAt(sb.length() - 1);
            return;
        }
    }
}
