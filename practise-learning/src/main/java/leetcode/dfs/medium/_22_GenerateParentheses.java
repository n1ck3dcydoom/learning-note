package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 1.0
 * @description leetcode
 * <p>
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 8
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/4/1 23:39
 **/
public class _22_GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, "", n, n);
        return res;
    }

    /**
     * 回溯要么用全局变量保存每次符合条件的结果，要么每次递归的时候带上保存的集合
     * <p>
     * str用于保存中间结果
     * left表示“剩余”左括号的个数
     * right表示“剩余”右括号的个数
     * <p>
     * 剩余左括号个数总是小于等于剩余右括号个数
     */
    private void dfs(List<String> res, String str, int left, int right) {
        if (left == 0 && right == 0) {
            res.add(str);
            return;
        }

        // 如果剩余左右括号相等，下一个一定只能是左括号
        if (left == right) {
            dfs(res, str + "(", left - 1, right);
        }
        // 如果剩余左括号小于剩余右括号
        // 那么下一个可以继续用左括号，也可以为前面补充若干个缺少的右括号
        if (left < right) {
            if (left > 0) {
                dfs(res, str + "(", left - 1, right);
            }
            if (right > 0) {
                dfs(res, str + ")", left, right - 1);
            }
        }
    }
}