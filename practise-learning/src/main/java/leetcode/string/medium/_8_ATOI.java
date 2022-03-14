package leetcode.string.medium;

/**
 * Created by n!Ck
 * Date: 2022/3/9
 * Time: 19:59
 * Description:
 */

public class _8_ATOI {

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("4193 new test"));
        System.out.println(myAtoi("new tes 981"));
        System.out.println(myAtoi("-91283472332"));
        System.out.println(myAtoi("92147483648"));
        System.out.println(myAtoi("+2"));
        System.out.println(myAtoi("-+12"));
        System.out.println(myAtoi(" "));
    }

    public static int myAtoi(String s) {
        int num = s.length();
        if (num == 0) {
            return 0;
        }
        int index = 0;
        char[] cs = s.toCharArray();
        // 处理前导空格
        while (index < num && cs[index] == ' ') {
            index++;
        }
        // 如果全是空格
        if (index == num) {
            return 0;
        }
        // 找到第一个非空格字符，判断是否是符号位
        int sign = 1;
        if (cs[index] == '-') {
            sign = -1;
            index++;
        } else if (cs[index] == '+') {
            index++;
        }
        // 开始处理数字
        long res = 0L;
        while (index < num) {
            if ('0' <= cs[index] && cs[index] <= '9') {
                res = res * 10 + cs[index++] - '0';
                // 判断是否溢出
                if (sign * res >= Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (sign * res < Integer.MIN_VALUE) {
                    return Integer.MIN_VALUE;
                }
            } else {
                break;
            }
        }
        return sign * (int) res;
    }
}
