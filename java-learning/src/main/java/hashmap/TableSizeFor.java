package hashmap;

import java.util.Scanner;

public class TableSizeFor {

    public static void main(String[] args) {
        // 给定一个整数n
        // 返回大于等于n且最接近2的幂次
        // eg: n=5 return 8
        // eg: n=8 return 8
        // eg: n=9 return 16

        int n = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            n = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 函数NumberOfLeadingZeros返回给定整数的前导0个数
        int preZeros = Integer.numberOfLeadingZeros(n);

        // int n = -1 >>> Integer.numberOfLeadingZeros(cap - 1);
        // return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
