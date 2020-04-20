package nowcode.WeiPinHui_A;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by n!Ck
 * Date: 2019-02-21
 * Time: 21:00
 * Description:
 * 输入两个字符串a和b，字符串内容为二进制数字，求两个字符串相加的结果，
 * 加法计算方法以二进制方式计算，并返回对应的字符串结果。要求程序尽可能的高效。
 * <p>
 * a = "1101"
 * b = "1100"
 * return "11001"
 */

public class StringAdd {
    public static void main(String[] args) {
        String aStr;
        String bStr;
        try (Scanner scanner = new Scanner(System.in)) {
            aStr = scanner.nextLine(); // 1101
            bStr = scanner.nextLine(); // 1100
        }

        int aInt = binary2dec(aStr);
        int bInt = binary2dec(bStr);
        int cInt = aInt + bInt;
        System.out.println(dec2binary(cInt));

    }

    private static int binary2dec(String binary) {
        char[] chars = binary.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum += (int) Math.pow(2, (double) chars.length - i - 1) * (chars[i] - '0');
        }
        return sum;
    }

    private static String dec2binary(int n) {
        Stack<Integer> numberStack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        while (n != 1) {
            numberStack.push(n % 2);
            n /= 2;
        }
        stringBuilder.append("1");
        while (!numberStack.isEmpty()) {
            stringBuilder.append(numberStack.pop());
        }
        return stringBuilder.toString();
    }
}
