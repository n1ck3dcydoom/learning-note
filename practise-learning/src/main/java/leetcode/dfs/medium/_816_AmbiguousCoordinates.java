package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/11/7
 * Time: 22:08
 * Description:
 */

public class _816_AmbiguousCoordinates {

    public static void main(String[] args) {
        // System.out.println(ambiguousCoordinates("(123)"));
        // System.out.println(ambiguousCoordinates("(00011)"));
        // System.out.println(ambiguousCoordinates("(0123)"));
        System.out.println(ambiguousCoordinates("(100)"));
    }

    public static List<String> ambiguousCoordinates(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        // 枚举 ',' 的位置,将数组分为两个部分后,依次枚举 '.' 的位置
        List<String> res = new ArrayList<>();
        s = sb.toString();
        int n = s.length();

        // 约定 ',' 插入的位置将原数组分为左右两个部分,划分区间为 左闭右开 [0,i) [i,n)
        // i==1 [0,1) ∪ [1,n) = [0,0] ∪ [1,n-1]
        // i==n [0,n-1) ∪ [n-1,n) = [0,n-2] ∪ [n-1,n-1]
        for (int i = 1; i <= n - 1; i++) {
            // 枚举左边
            List<String> left = traversal(s, 0, i);
            // 枚举右边
            List<String> right = traversal(s, i, n);
            // 做笛卡尔积
            for (String l : left) {
                for (String r : right) {
                    res.add("(" + l + "," + r + ")");
                }
            }
        }
        return res;
    }

    private static List<String> traversal(String s, int l, int r) {
        List<String> res = new ArrayList<>();
        // 枚举的时候判断长度,如果只有一位,只能是单个数字
        if (r - l == 1) {
            res.add(s.substring(l, r));
        } else {
            // 如果有多位,枚举小数点的位置,判断一个小数是否合法
            // 小数点左边必须有数字,如果是 0,则只能有 1 个 0
            // 小数点后面必须有数字,且结尾不得是 0
            for (int i = l + 1; i <= r; i++) {
                // 分割整数部分
                String left = s.substring(l, i);
                // 整数部分长度为 1 的话允许为 0,否则不允许出现前导 0
                if (left.length() > 1 && '0' == left.charAt(0)) {
                    break;
                }
                // 整数部分分割完后且通过前导 0 校验
                // 若没有小数部分了,直接添加整数部分后函数返回
                if (i == r) {
                    res.add(s.substring(l, i));
                    break;
                }
                String right = s.substring(i, r);
                // 小数部分的最后一位不允许为 0
                if ('0' == right.charAt(right.length() - 1)) {
                    continue;
                }
                res.add(left + "." + right);
            }
        }
        return res;
    }
}