package nowcode.program;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-03-18
 * Time: 20:09
 * Description:
 * 有两个用字符串表示的非常大的大整数,算出他们的乘积，也是用字符串表示。不能用系统自带的大整数类型。
 * <p>
 * 输入描述:
 * 空格分隔的两个字符串，代表输入的两个大整数
 * <p>
 * 输出描述:
 * 输入的乘积，用字符串表示
 * <p>
 * 示例1
 * 输入
 * 72106547548473106236 982161082972751393
 * <p>
 * 输出
 * 70820244829634538040848656466105986748
 */
public class BigNumMult {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] line = scanner.nextLine().split(" ");
            char[] num1 = line[0].toCharArray();
            char[] num2 = line[1].toCharArray();
            int[] result = new int[num1.length + num2.length];

            for (int j = num2.length - 1; j >= 0; j--) {
                for (int i = num1.length - 1; i >= 0; i--) {
                    result[i + j + 1] += (num2[j] - '0') * (num1[i] - '0');
                }
            }
            for (int i = result.length - 1; i >= 0; i--) {
                if (result[i] == 0) {
                    break;
                }
                if (result[i] > 10) {
                    result[i - 1] += result[i] / 10;
                    result[i] %= 10;
                }
            }

            for (int i : result) {
                if (i != 0) {
                    System.out.print(i);
                }
            }
        }
    }
}
