package leetcode.dfs.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _140_WordBreakII {

    public static void main(String[] args) {
        List<String> dic = new ArrayList<>(Arrays.asList("cat", "cats", "and", "sand", "dog"));
        List<String> res = wordBreak("catsanddog", dic);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }

    public static List<String> wordBreak(String s, List<String> wordDict) {
        // dfs搜索每个可能
        List<String> res = new ArrayList<>();
        dfs(res, new ArrayList<>(), s, 0, wordDict);
        return res;
    }

    private static void dfs(List<String> res, List<String> path, String s, int lastPos, List<String> dic) {
        // 上一个切割点已经到达字符串末尾
        if (lastPos == s.length()) {
            res.add(String.join(" ", path));
            return;
        }
        // 从上一个切割点开始往后面搜索，直到找到一个合法的单词
        for (int i = lastPos + 1; i <= s.length(); i++) {
            String sub = s.substring(lastPos, i);
            if (dic.contains(sub)) {
                // 加入选择
                path.add(sub);
                // 继续dfs搜索
                dfs(res, path, s, i, dic);
                // 撤销选择
                path.remove(path.size() - 1);
            }
        }
        // 如果从lastPost往后所有字符都无法构成一个合法单词，直接return
    }
}
