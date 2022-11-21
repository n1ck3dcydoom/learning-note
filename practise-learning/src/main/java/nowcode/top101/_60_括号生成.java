package nowcode.top101;

import java.util.ArrayList;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 14:40
 * Description:
 */

public class _60_括号生成 {

    public static void main(String[] args) {
        System.out.println(generateParenthesis(2));
    }

    public static ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> res = new ArrayList<>();
        dfs(n, n, "", res);
        return res;
    }

    private static void dfs(int m, int n, String path, ArrayList<String> res) {
        // 如果左括号有右括号都用完了
        if (m == 0 && n == 0) {
            res.add(path);
        }
        // 用一个左括号
        if (m > 0) {
            // 递归搜索剩下的组合
            // 选择作为参数传入，方法返回后自动撤销选择
            dfs(m - 1, n, path + '(', res);
        }
        // 用一个右括号，由于必须保证左右括号成对出现，所以右括号的次数必须小于左括号的次数
        if (n > 0 && n > m) {
            dfs(m, n - 1, path + ')', res);
        }
    }
}
