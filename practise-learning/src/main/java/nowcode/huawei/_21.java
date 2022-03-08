package nowcode.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2022/3/9
 * Time: 0:00
 * Description:
 */

public class _21 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] lowerCase = new int[]{2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
        while (scanner.hasNext()) {
            String raw = scanner.nextLine();
            StringBuilder sb = new StringBuilder();
            char[] cs = raw.toCharArray();
            for (char c : cs) {
                // 数字不作处理
                if ('0' <= c && c <= '9') {
                    sb.append(c);
                }
                // 小写字母映射到手机9键上
                else if ('a' <= c && c <= 'z') {
                    sb.append(lowerCase[c - 'a']);
                }
                // 大写字母先转换为小写，在后移以为
                else {
                    char lower = (char) (Character.toLowerCase(c) + 1);
                    sb.append(lower > 'z' ? 'a' : lower);
                }
                System.out.println(sb.toString());
            }
        }
    }
}
