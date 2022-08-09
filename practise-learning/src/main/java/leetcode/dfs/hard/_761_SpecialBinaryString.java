package leetcode.dfs.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/8/9
 * Time: 09:14
 * Description:
 */

public class _761_SpecialBinaryString {

    public static void main(String[] args) {
        String s = "11011000";
        System.out.println(makeLargestSpecial(s));
    }

    public static String makeLargestSpecial(String s) {
        // 将 1 表示左括号(, 2 表示右括号)
        // 满足特殊字符串的条件:
        // 1 和 0 必须相等,即左括号个数必须等于右括号个数
        // 其前缀的 1 的个数必须大于等于 0 的个数

        // 对于一个特殊序列来说,由于前缀 1 的个数必须大于等于 0,所以必定以 1 开头,以 0 结尾
        // 排除首位和末位,从中间位置开始遍历查找所有的特殊序列
        // 对找到的所有特殊序列做降序排序后依次添加到末位,构成字典序最大的特殊序列

        if (s == null || s.length() < 2) {
            return s;
        }

        return dfs(s, 0, s.length() - 1);
    }

    private static String dfs(String s, int left, int right) {
        // 递归查找
        if (left >= right) {
            return "";
        }
        // 保存所有有效括号对
        List<String> list = new ArrayList<>();
        // 保存左括号的个数
        int count = 0;
        // 连续子串的起点
        int pre = left;
        // 从给定的递归起点和终点范围内查找
        for (int i = left; i <= right; i++) {
            if (s.charAt(i) == '1') {
                count++;
            } else {
                count--;
                // 如果 count==0 ,表示找到一个完整的有效括号对
                if (count == 0) {
                    // 继续递归查找当前有效括号对里面的子串
                    String tmp = dfs(s, pre + 1, i - 1);
                    // 为其添加首位的 1 和末位的 0
                    list.add("1" + tmp + "0");
                    // 更新下一个连续括号对的查找起始区间
                    pre = i + 1;
                }
            }
        }
        // 对所有子串降序排序
        list.sort((a, b) -> b.compareTo(a));
        return String.join("", list);
    }
}
