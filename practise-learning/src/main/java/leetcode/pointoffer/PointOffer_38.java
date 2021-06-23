package leetcode.pointoffer;

import java.util.HashSet;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetocde
 * <p>
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 * <p>
 *  
 * <p>
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 * <p>
 *  
 * <p>
 * 示例:
 * <p>
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *  
 * <p>
 * 限制：
 * <p>
 * 1 <= s 的长度 <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/6/22 11:19
 **/
public class PointOffer_38 {

    public static void main(String[] args) {
        String s = "aac";
        for (String str : permutation(s)) {
            System.out.print(str + " ");
        }
    }

    public static String[] permutation(String s) {
        HashSet<String> res = new HashSet<>();
        boolean[] visited = new boolean[s.length()];
        dfs(res, visited, s, "");
        String[] resArray = new String[res.size()];
        return res.toArray(resArray);
    }

    /**
     * 使用visited[]数组标记哪些字符已经被访问过了
     *
     * @param res  结果集res
     * @param s    可供选择 字符串s
     * @param path 前路径path
     */
    private static void dfs(HashSet<String> res, boolean[] visited, String s, String path) {
        // 如果当前路径长度等于了输入串的长度，表示找到了一个满足题意的路径
        if (path.length() == s.length()) {
            // 当前路径加入结果集，返回上一次dfs
            // 使用hashset去重
            res.add(path.toString());
            return;
        }

        // 遍历所有还没被访问过的字符
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                // 做出选择
                visited[i] = true;
                // 进行递归
                dfs(res, visited, s, path + s.charAt(i));
                // 撤销选择
                visited[i] = false;
            }
        }
    }
}