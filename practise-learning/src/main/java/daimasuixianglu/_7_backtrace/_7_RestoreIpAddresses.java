package daimasuixianglu._7_backtrace;

import java.util.ArrayList;
import java.util.List;

public class _7_RestoreIpAddresses {

    private static List<String> res = new ArrayList<>();
    private static List<String> path = new ArrayList<>();

    public static void main(String[] args) {
        restoreIpAddresses("101023");
        int a = 1;
    }

    public static List<String> restoreIpAddresses(String s) {
        dfs(s, 0, 0);
        return res;
    }


    /**
     * 不变量是什么？是区间的左闭右开原则
     */
    private static void dfs(String s, int index, int count) {
        // 剪枝
        if (count == 4 && index < s.length()) {
            return;
        }
        if (index == s.length() && count < 4) {
            return;
        }
        // 如果索引下标等于s长度，且ip段落有4个，则结束遍历
        if (index == s.length() && count == 4) {
            String ip = String.join(".", path.toArray(new String[0]));
            res.add(ip);
            return;
        }
        // 尝试切割合法的ip段
        for (int i = index; i < s.length(); i++) {
            // 超过3位ip段直接返回
            String sub = s.substring(index, i + 1);
            if (sub.length() > 3) {
                return;
            }
            // 非法前导0直接返回
            if (sub.startsWith("0") && sub.length() > 1) {
                return;
            }
            int seg = Integer.parseInt(sub);
            // 判断是否合法
            if (0 <= seg && seg < 256) {
                path.add(sub);
                dfs(s, i + 1, count + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
