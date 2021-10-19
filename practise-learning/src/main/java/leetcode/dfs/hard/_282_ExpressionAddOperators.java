package leetcode.dfs.hard;

import java.util.ArrayList;
import java.util.List;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2021/10/18 14:08
 **/
public class _282_ExpressionAddOperators {

    public static void main(String[] args) {
        System.out.println(addOperators("105", 5));
    }

    public static List<String> addOperators(String num, int target) {
        // 前面i-1个已经处理好的数的结果记作pre
        // 在i-1和i之间可以放入的操作+ - *
        // +和-都很简单，即pre+i或者pre-i
        // *乘法改变了运算符顺序，1+2*3*4，在遍历到3和4之间的*符号时
        // 前面的结果pre=1+2*3=6，需要减去前面的连乘结果2*3，然后加上新的连乘结果2*3*4
        // 即pre-2*3+2*3*4 = 6-6+24 = 24


        List<String> res = new ArrayList<>();
        // dfs需要传递的参数，结果集res，当前路径path，前面的结果pre，前面的连乘段mul，当前遍历的位置，num，结束的条件

        return null;
    }

    private static void dfs(List<String> res, String path, int pre, int mul, int index, String num, int target) {
        // 如果遍历完成，检查当前路径是否满足条件
        if (index == num.length()) {
            if (pre == target) {
                res.add(path);
            }
            return;
        }
        // 继续遍历路径
        for (int i = index; i < num.length(); i++) {
            // 表达式开头不能添加符号
            if (i == 0) {
//                dfs(res, path + num.charAt(i),);
            }

        }
    }

}