package nowcode.DiSiFanShi;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-24
 * Time: 22:46
 * Description:
 */
public class StringNumberMult {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {

            char[] a = scanner.nextLine().toCharArray();
            char[] b = scanner.nextLine().toCharArray();

            // 两个数相乘，结果的位数最大为两个数的位数和
            int[] reslutArray = new int[a.length + b.length];

            int carry = 0; // 进位标志

            // 直接遍历两个数组 O(n ^2) 复杂度
            for (int i = b.length - 1; i >= 0; i--) {
                carry = 0;
                for (int j = a.length - 1; j >= 0; j--) {
                    int temp = (a[j] - '0') * (b[i] - '0') + carry + reslutArray[i + j + 1];
                    reslutArray[i + j + 1] = temp % 10; // 保存一次运算结果的个位
                    carry = temp / 10; // 保存一次运算结果的进位
                }
                reslutArray[i] += carry;
            }
            StringBuilder stringBuilder = new StringBuilder();

            for (int n : reslutArray) {
                stringBuilder.append(n);
            }
            System.out.println(stringBuilder.toString().replaceAll("^(0+)", ""));
        }
    }
}
