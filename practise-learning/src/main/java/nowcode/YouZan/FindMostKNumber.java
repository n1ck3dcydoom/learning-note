package nowcode.YouZan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-06
 * Time: 15:01
 * Description:
 * 给定一个无序的整型数组A[n],数组大小大于等于3,允许有值相同的元素;请设计算法找到该数组排序后第三大的元素值并输出.
 * <p>
 * 解题思路：
 * 倒序排序后输出第三个？
 */
public class FindMostKNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        line = line.substring(1, line.length() - 1);
        String[] array = line.split(",");

        List<Integer> numberList = new ArrayList<>();
        for (String str : array) {
            numberList.add(Integer.valueOf(str));
        }
        // 排序
        numberList.sort((num1, num2) -> num2 - num1);

        System.out.println(numberList.get(2));
    }
}
