package nowcode.program;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-03-18
 * Time: 19:26
 * Description:
 * 给定一个无序数组，包含正数、负数和0，要求从中找出3个数的乘积，使得乘积最大，要求时间复杂度：O(n)，空间复杂度：O(1)
 * <p>
 * 输入描述:
 * 第一行：数组长度n
 * 第二行：无序整数数组A[n]
 * <p>
 * 输出描述:
 * 满足条件的最大乘积
 * <p>
 * 示例1
 * 输入
 * 4
 * 3 4 1 2
 * 输出
 * 24
 */

public class TheMaxProd {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int size = Integer.parseInt(scanner.nextLine());
            String[] line = scanner.nextLine().split(" ");

            List<Long> nums = new ArrayList<>();
            for (String s : line) {
                nums.add(Long.parseLong(s));
            }

            nums.sort(Comparator.comparingLong(v -> v));

            long max1 = nums.get(size - 1) * nums.get(size - 2) * nums.get(size - 3);
            long max2 = nums.get(0) * nums.get(1) * nums.get(size - 1);

            System.out.println(Math.max(max1, max2));
        }
    }
}