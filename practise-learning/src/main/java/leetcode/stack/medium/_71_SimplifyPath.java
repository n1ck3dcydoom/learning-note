package leetcode.stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class _71_SimplifyPath {

    public static void main(String[] args) {
        System.out.println(simplifyPath("/home/"));
        System.out.println(simplifyPath("/../"));
        System.out.println(simplifyPath("/home///test/"));
        System.out.println(simplifyPath("/a/./b/../../c/"));
    }

    public static String simplifyPath(String path) {
        // 用栈处理
        // 遍历处理过后的路径
        // 遇到/压栈
        // 遇到单个.表示当前路径，则什么都不做
        // 遇到两个..表示上层目录，弹出栈顶的目录和分隔符/
        // 遇到目录(若干个连续的字符组成)压栈
        String[] pathSplit = path.split("/");
        Deque<String> stack = new ArrayDeque<>();
        // 根目录入栈
        stack.addLast("/");
        for (String str : pathSplit) {

            if ("".equals(str)) {
                continue;
            }
            // 单个.什么都不做
            else if (".".equals(str)) {
                // // 把上一个分隔符/弹出
                // // 如果栈空，则需要补充根目录/
                // stack.pollLast();
                // if (stack.isEmpty()) {
                //     stack.addLast("/");
                // }
                continue;
            }
            // 两个点，栈顶如果是某个目录，则出栈
            else if ("..".equals(str)) {
                // 对于非根目录的情况，例如/abc/test/...
                // 此时需要弹出最后一个/和上一个目录test

                // 对于根目录的情况，例如/..
                // 此时弹出最后一个/之后，栈空，所以不能再弹出上一个目录

                // 弹出最后一个分隔符/
                stack.pollLast();
                // 判断栈是否为空
                if (stack.isEmpty()) {
                    // 如果栈空，表明对根目录做..操作，已经不存在上一个目录了
                    // 此时就不再继续弹栈，而且把已经弹出的/再压进去，保证栈不为空
                    stack.addLast("/");
                } else {
                    // 如果栈不为空，则把上一个目录继续弹出去
                    stack.pollLast();
                }
            }
            // 文件目录入栈
            else {
                stack.addLast(str);
                stack.addLast("/");
            }
        }
        StringBuilder resBuilder = new StringBuilder();
        if ("/".equals(stack.peek()) && stack.size() > 1) {
            stack.pollLast();
        }
        while (!stack.isEmpty()) {
            resBuilder.insert(0, stack.pollLast());
        }
        return resBuilder.toString();
    }
}
