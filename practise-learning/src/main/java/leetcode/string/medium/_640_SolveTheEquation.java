package leetcode.string.medium;

/**
 * Created by n!Ck
 * Date: 2022/8/10
 * Time: 09:12
 * Description:
 */

public class _640_SolveTheEquation {

    public static void main(String[] args) {
        System.out.println(solveEquation("-x=-1"));
    }

    public static String solveEquation(String equation) {
        String[] split = equation.split("=");
        // 预处理所有-号,变为+-
        // 提取所有变量 x 和常亮
        String[] left = split[0].replace("-", "+-").split("\\+");
        String[] right = split[1].replace("-", "+-").split("\\+");

        int leftVar = 0;
        int rightConst = 0;

        // 合并同类项
        for (String s : left) {
            // +x,变量保留在左边
            if (s.equals("x")) {
                leftVar++;
            }
            // -x,变量保留在左边
            else if (s.equals("-x")) {
                leftVar--;
            }
            // 带常量的 x,例如 3x 或者-x,变量保留在左边
            else if (s.contains("x")) {
                // 截取前面的常量部分,x 一定在末尾,substring 把前面的常量部分抓出来
                leftVar += Integer.parseInt(s.substring(0, s.length() - 1));
            }
            // 常量放到右边去,注意处理仅有一项的表达式,即 "-+x".split("+") 后有一个空串"",注意符号处理
            else if (!s.equals("")) {
                rightConst -= Integer.parseInt(s);
            }
        }
        // 合并同类项
        for (String s : right) {
            // +x,变量放到左边去,注意符号处理
            if (s.equals("x")) {
                leftVar--;
            }
            // -x,变量放到左边去,注意符号处理
            else if (s.equals("-x")) {
                leftVar++;
            }
            // 带常量的 x,例如 3x 或者-x,变量放到左边去,注意符号处理
            else if (s.contains("x")) {
                // 截取前面的常量部分,x 一定在末尾,substring 把前面的常量部分抓出来
                leftVar -= Integer.parseInt(s.substring(0, s.length() - 1));
            }
            // 常量,保留在右边,注意处理仅有一项的表达式,即 "-+x".split("+") 后有一个空串"",注意符号处理
            else if (!s.equals("")) {
                rightConst += Integer.parseInt(s);
            }
        }

        // 如果左边没有 x,即左边为 0,判断右边是否也为 0
        if (leftVar == 0) {
            return rightConst == 0 ? "Infinite solutions" : "No solution";
        } else {
            return "x=" + rightConst / leftVar;
        }
    }
}
