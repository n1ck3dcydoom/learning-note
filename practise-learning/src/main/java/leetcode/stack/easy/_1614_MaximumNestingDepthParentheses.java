package leetcode.stack.easy;

public class _1614_MaximumNestingDepthParentheses {

    public static void main(String[] args) {
        // 3
        String s1 = "(1+(2*3)+((8)/4))+1";
        System.out.println(maxDepth(s1));

        // 3
        String s2 = "(1)+((2))+(((3)))";
        System.out.println(maxDepth(s2));

        // 1
        String s3 = "1+(2*3)/(2-1)";
        System.out.println(maxDepth(s3));

        // 0
        String s4 = "1";
        System.out.println(maxDepth(s4));
    }

    public static int maxDepth(String s) {
        // 使用栈统计左括号的最大深度
        int depth = 0;
        int res = 0;
        for (char c : s.toCharArray()) {
            if ('(' == c) {
                res = Math.max(res, ++depth);
            } else if (')' == c) {
                --depth;
            }
        }
        return res;
    }
}
