package leetcode.math.medium;

import java.util.HashMap;

public class _166_FractionToRecurringDecimal {

    public static void main(String[] args) {
        System.out.println(fractionToDecimal1(1, 2));
        System.out.println(fractionToDecimal1(2, 1));
        System.out.println(fractionToDecimal1(2, 3));
        System.out.println(fractionToDecimal1(4, 333));
        System.out.println(fractionToDecimal1(1, 5));
        System.out.println(fractionToDecimal1(4, 3));
        System.out.println(fractionToDecimal1(1, 333));
        System.out.println(fractionToDecimal1(-1, -2147483648));
    }

    public static String fractionToDecimal(int numerator, int denominator) {
        // 题目给出了除数不可能为0，b != 0
        int a = numerator, b = denominator;
        // 模拟除法
        StringBuilder sb = new StringBuilder();
        // 先判断是否是负数
        if (a * b < 0) {
            sb.append("-");
        }
        // 先计算整数部分
        int integer = a / b;
        sb.append(integer);
        // 整数整除
        if (a % b == 0) {
            return sb.toString();
        }
        // 小数 1/2=0.5 小数整除
        // 添加小数点
        sb.append(".");
        // 使用hash表记录每个商的出现位置
        // 根据抽屉原理，一旦有一个商重复出现了，那么两个商中间的就是循环节
        // 余数要么等于0，除尽了
        // 余数不等于0，没除尽，被除数补0后继续计算
        HashMap<Integer, Integer> hash = new HashMap<>(10);
        // 模拟除法的过程就是，每次不构除的时候，在被除数后面补0继续计算
        // 余数
        int c = a % b;
        int res = 0;
        int last = 0;
        while (true) {
            // 除尽了
            if (c == 0) {
                break;
            }
            // 余数补0
            c *= 10;
            // 新的商
            res = c / b;
            // 新的余数
            c = c % b;
            // 抽屉原理，判断新的商是否出现过
            if (!hash.containsKey(res)) {
                hash.put(res, last);
                // 添加新的商
                sb.append(res);
                last++;
            }
            // 如果出现了，就记录下出现的位置pos
            else {
                break;
            }
        }
        // 整除直接返回
        if (c == 0) {
            return sb.toString();
        }
        // 循环小数需要计算循环节
        // 最后一个出现的商是res，位置是pos
        // 找到第一次出现res的位置，在这之间都是循环节，两端添加上括号
        //        int first=
        String raw = sb.toString();
        // 这里的first其实是相对于dot的位置
        // 若first==0，则表示是dot后面第0个索引
        // 所以左括号(应该插入的位置是dot后偏移first+1
        // 同理last的偏移量也是相对于dot的位置的
        // 右括号)应该插入的位置是dot后偏移last-1
        int first = hash.get(res);
        int dot = raw.indexOf(".");
        sb.insert(dot + first + 1, "(");
        sb.insert(dot + last + 2, ")");
        return sb.toString();
    }

    public static String fractionToDecimal1(int numerator, int denominator) {
        // 转换为long类型，防止溢出
        long a = numerator, b = denominator;
        StringBuilder sb = new StringBuilder();
        if (a * b == 0) {
            return "0";
        }
        // 负数
        if (a * b < 0) {
            sb.append("-");
        }
        a = Math.abs(a);
        b = Math.abs(b);
        // 整数整除
        if (a % b == 0) {
            return sb.append(a / b).toString();
        }
        // 计算整数部分
        sb.append(a / b).append(".");
        // 计算小数
        // 如果中途除尽，则是有限不循环小数
        // 如果被除数重复出现，则是无限循环小数
        HashMap<Long, Integer> hash = new HashMap<>();
        // 整数部分长度(包括小数点)
        int integerLen = sb.toString().length();
        // 余数
        long c = a % b;
        while (true) {
            // 余数作为新的被除数补0
            c = (c % b) * 10;
            // 小数整除
            if (c == 0) {
                break;
            }
            // 新的商
            long res = c / b;
            if (!hash.containsKey(c)) {
                hash.put(c, integerLen);
                integerLen++;
            } else {
                break;
            }
            // 添加新的商
            sb.append(res);
        }
        return c == 0 ? sb.toString() : sb.insert(hash.get(c), "(").append(")").toString();
    }
}