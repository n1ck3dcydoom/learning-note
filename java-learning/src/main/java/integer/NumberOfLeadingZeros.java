package integer;

import java.util.Scanner;

public class NumberOfLeadingZeros {

    public static void main(String[] args) {
        int n;
        int i = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            i = scanner.nextInt();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(Integer.numberOfLeadingZeros(i));

        // 负数前置0个数为0，首位符号位一定是1
        // 0有32个前置0
        if (i <= 0) {
            n = i == 0 ? 32 : 0;
        }
        // 如果一个数不等于0，它最多也只有31个前置0
        // 初始化假设i有31个前导0
        n = 31;
        // 巧妙地运用了二分的思想

        // 如果i >= 2^16，那么i的后16位一定不含有前导0
        // 前导0个数排除掉后16位
        // i无符号右移16位
        if (i >= 1 << 16) {
            n -= 16;
            i >>>= 16;
        }
        // 如果i >= 2^8，那么i的后8位一定不含有前导0
        // n排除后8位
        // i无符号右移8位
        if (i >= 1 << 8) {
            n -= 8;
            i >>>= 8;
        }
        // 同理
        if (i >= 1 << 4) {
            n -= 4;
            i >>>= 4;
        }
        // 同理
        if (i >= 1 << 2) {
            n -= 2;
            i >>>= 2;
        }
        // 经过上述4次二分查找后，i还剩下2位(32-16-8-4-2)
        // 将i再次二分(无符号右移1位)得到最后一个前导0个数
        System.out.println(n - (i >>> 1));
        // 例如 14
        // 00000000 00000000 00000000 00001110
        // 第1次二分i < 2^16 说明前面至少含有16位的前导0
        // 第2次二分i < 2^8 说明前面至少含有8位的前导0
        // 第3次二分i < 2^4 说面前面至少含有4位的前导0
        // 第4次二分i > 2^2 则说明i后两位一定不是前导0，n-=2，i无符号右移2位
        // n = 31-2 = 29
        // i >>>= 2
        // 00000000 00000000 00000000 00000011
        // 第5次二分移除最后一个非前导0的末位
        // n -= i >>> 1
        // n = 29 - 1 = 28
    }
}
