package leetcode.simulate.medium;

/**
 * Created by n1ck3dcydoom
 * Date: 2021/11/30
 * Time: 22:18
 * Description: TODO
 */
public class _400_NthDigit {

    public static void main(String[] args) {
        System.out.println(findNthDigit(3));
        System.out.println(findNthDigit(11));
    }

    public static int findNthDigit(int n) {
        // 一位数共9个，占位9位
        // 两位数共90个，占位90*2=180位
        // 三位数共900个，占位900*3=2700位
        // ...
        // 九位数共900000000个，占位900000000*9=8100000000
        //               已经超过Integer.MAX = 2147483647
        // 所以n最多也就是个9位数

        // 先计算出n是几位数
        int bit = 1;
        // 基数1,10,100
        int pow = 1;
        // 最多9位数的时候占位已经超过了Integer.MAX，需要转换为long类型计算
        while (n > (long) 9 * pow * bit) {
            // 从一位数开始递减，每次减去所有当前位数占据的位
            n -= 9 * pow * bit;
            // 下一位基数
            pow *= 10;
            // 位数加1
            bit++;
        }
        // 减去前面所有bit-1位数所占的位数后，此时的n表示当前位数下第n个位置
        // 例如n=15，减去一位数的9位后，还剩6位，此时剩下的位数不超过所有二位数的占位总和
        // 表明n是一个二位数，而此时的n表示从第一个二位数开始的第n个数字
        // 而第一个数则是当前位数的基数pow
        // 此时pow=10，n=6表示从10开始往后数6位
        // 10占2位，11占2位...，所以6/2=3，则表示处于第3个数
        // 第3个数的下标得从0开始计数，10、11、12
        // 所以此时应该处于12上面，而非10+3=13上
        // 所以所剩位数n可以整除bit时，得到的位置需要-1才是正确的位置
        int num = n % bit == 0 ? pow + (n / bit) - 1 : pow + n / bit;
        // 还需要计算处于12的第几位数上
        // 此时n还剩6位需要计算，处于两位数时，每2个位置构成一个两位数
        // 同样的，如果是整除，则说明处于当前数的最后一位，索引bit-1
        // 否则减去前面所有的数，即可得到位数(同样的需要下标-1)

        // 假如n是5位数，已经得到num=12345，且n处于第3位(下标减1后实际上是第2位)
        int res = (n % bit == 0 ? bit : n % bit) - 1;
        return String.valueOf(num).charAt(res) - '0';
    }
}
