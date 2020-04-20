package nowcode.YouZan;

import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-06
 * Time: 14:15
 * Description:
 *
 * 给定一个非空的整数数组，从数组第一个元素(下标为0的元素)开始遍历进行移动，下一次向后或向前移动该元素的值的位数（值为正数向后移动，值为负数向前移动，值为零不移动）
 * 依次类推进行移动，若某次移动数组出现越界，则说明数组可以跳出，返回true；不能跳出则返回false；（加分项：也可考虑不增加使用其他集合数组辅助完成算法）
 * 例1：
 * 输入数组a[5] = [1,2,3,2,5];从第一个元素开始a[0]=1,下次向后移动1位到第二个元素a[1]=2,再次向后移动2位到第四个元素a[3]
 * 因为下次向后移动2位(a[3]=2)后,向后数组越界,即跳出数组,输出true;
 * 例2：
 * 输入数组a[2] = [1,-3];从第一个元素开始a[0]=1,下次移动1位到第二个元素a[1]=-3,再次向前移动3位后,向前数组越界,即跳出数组,输出true;
 *
 * 解题思路：
 * 如果数组越界，则能跳出
 * 如果某个元素重复访问，则不能跳出
 */
public class ArrayJump {
    public static void main(String[] args) {
        String line;
        try (Scanner scanner = new Scanner(System.in)) {
            line = scanner.nextLine();
        }

        line = line.substring(1, line.length() - 1);
        String[] array = line.split(",");

        boolean[] visits = new boolean[array.length];

        int i = 0;
        while (true) {
            if (!visits[i]) {
                visits[i] = true;
                i += Integer.valueOf(array[i]);
            } else {
                System.out.println("false");
                break;
            }
            if (i >= array.length || i < 0) {
                System.out.println("true");
                break;
            }
        }
    }
}
