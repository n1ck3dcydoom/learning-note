package leetcode.stack.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by n!Ck
 * Date: 2022/9/9
 * Time: 12:39
 * Description:
 */

public class _1598_CrawlerLogFolder {

    public static void main(String[] args) {
        System.out.println(minOperations(new String[]{"d1/", "d2/", "../", "d21/", "./"}));
        System.out.println(minOperations(new String[]{"d1/", "d2/", "./", "d3/", "../", "d31/"}));
        System.out.println(minOperations(new String[]{"d1/", "../", "../", "../"}));
    }

    public static int minOperations(String[] logs) {
        Deque<String> stack = new ArrayDeque<>();
        stack.addLast("/");
        for (String log : logs) {
            // ../ 返回操作则弹出栈顶元素
            if ("../".equals(log)) {
                if (!stack.peekLast().equals("/")) {
                    stack.pollLast();
                }
            }
            // ./ 不做任何事
            else if ("./".equals(log)) {

            }
            // 其他指令压入栈
            else {
                stack.offerLast(log);
            }
        }
        // 去掉最开始入栈的根目录
        return stack.size() - 1;
    }

    public static int minOperations1(String[] logs) {
        int res = 0;
        for (String log : logs) {
            if ("../".equals(log)) {
                if (res != 0) {
                    res--;
                }
            } else if ("./".equals(log)) {

            } else {
                res++;
            }
        }
        return res;
    }
}
