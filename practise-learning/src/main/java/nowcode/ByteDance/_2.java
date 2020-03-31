package nowcode.ByteDance;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-03-16
 * Time: 10:05
 * Description:
 * <p>
 * 定义如下字符串修复规则：
 * <p>
 * 1. 如果连续出现三个同样的字母，则去掉一个即可：如 helllo -> hello
 * 2. 两对一样的字母 (形如AABB) ，去掉第二对字母中的一个即可：如 helloo -> hello
 * 3. 上面规则优先考虑 从左到右 的匹配原则，即如果是 AABBCC ，这里AABB和BBCC都符合规则二，那么只需要修复AABB为AAB即可，结果为AABCC
 * <p>
 * 输入描述：
 * 第一行包括一个数字N，表示待修复的字符串行数
 * 后面N行，每行为一条待修复的字符串
 * <p>
 * 输出描述：
 * N行，每行为修复后的字符串
 * <p>
 * 例如：
 * 2
 * helloo -> hello
 * wooooooow -> woooooow -> wooooow -> woooow -> wooow -> woow
 * <p>
 * hello
 * woow
 */

public class _2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] strs = new String[n + 1];
        for (int i = 0; i <= n; i++) {
            strs[i] = scanner.nextLine();
        }
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            result[i] = handleString(strs[i + 1].toCharArray());
        }
        for (String str : result) {
            System.out.println(str);
        }
    }

    private static String handleString(char[] chars) {

        StringBuilder stringBuilder = new StringBuilder();
        if (chars.length == 0) {
            return "";
        }
        if (chars.length == 1 || chars.length == 2) {
            return new String(chars);
        }
        stringBuilder.append(chars[0]);
        int i = 1;
        while (i < chars.length - 1) {
            if (chars[i] != chars[i - 1]) {
                stringBuilder.append(chars[i++]);
            } else {
                i++;
                if (chars[i] == chars[i - 1]) {
                    stringBuilder.append(chars[i++]).append(chars, i, chars.length - i);
                    break;
                } else {
                    if (i == chars.length - 1) {
                        stringBuilder.append(chars[i - 1]).append(chars[i]);
                        break;
                    }
                    stringBuilder.append(chars[i - 1]);
                    if (chars[i] == chars[i + 1]) {
                        stringBuilder.append(chars[i++]).append(chars, i + 1, chars.length - i - 1);
                        break;
                    }
                }
            }
        }
        if (Arrays.equals(stringBuilder.toString().toCharArray(), chars)) {
            return stringBuilder.toString();
        } else {
            return handleString(stringBuilder.toString().toCharArray());
        }
    }
}
