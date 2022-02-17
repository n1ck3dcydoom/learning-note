package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _6_PalindromePartitioning {

    private static List<List<String>> res = new ArrayList<>();
    private static List<String> path = new ArrayList<>();

    public static void main(String[] args) {
        partition("aab");
        int a = 1;
    }

    public static List<List<String>> partition(String s) {
        dfs(path, s, 0);
        return res;
    }

    private static void dfs(List<String> path, String s, int index) {
        // 当前遍历的起始位置已经超过了s串的长度
        if (index == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 遍历切割子串
        for (int i = index; i < s.length(); i++) {
            String sub = s.substring(index, i + 1);
            if (check(sub)) {
                path.add(sub);
                dfs(path, s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean check(String s) {
        int n = s.length();
        for (int i = 0; i < (int) Math.ceil(n / (double) 2); i++) {
            if (s.charAt(i) != s.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
