package nowcode.top101;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 14:06
 * Description:
 */

public class _58_字符串的排列 {

    public static void main(String[] args) {
        System.out.println(Permutation("aa"));
    }

    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();

        char[] cs = str.toCharArray();
        Arrays.sort(cs);
        dfs(cs, new boolean[cs.length], new StringBuilder(), res);
        return res;
    }

    private static void dfs(char[] cs, boolean[] visited, StringBuilder sb, ArrayList<String> res) {
        if (sb.length() == cs.length) {
            String r = sb.toString();
            res.add(r);
        }
        for (int i = 0; i < cs.length; i++) {
            // 剪枝,跳过已经访问过的字符
            if (visited[i]) {
                continue;
            }
            // 剪枝,跳过重复字符
            // 这里 visited[i - 1] 和 !visited[i - 1] 都可以
            // 对于第一层已经搜索完的 i-1,其 visited[i - 1] == false,下一个相同元素在第一层搜索时前一个相同元素是没有访问过的
            if (i > 0 && cs[i] == cs[i - 1] && !visited[i - 1]) {
                continue;
            }
            // 当前字符加入选择
            sb.append(cs[i]);
            // 标记当前字符已经访问过
            visited[i] = true;
            // 递归搜索其他所有字符
            dfs(cs, visited, sb, res);
            // 撤销选择
            visited[i] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
