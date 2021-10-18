package leetcode.bit.easy;

public class _476_NumberComplement {

    public static void main(String[] args) {
        System.out.println(findComplement(5));
        System.out.println(findComplement(7));
        System.out.println(findComplement(10));
    }

    public static int findComplement(int num) {
        if (num == 0) {
            return 1;
        }

        // 和1进行异或运算可以取反
        // 题目忽略前导0，所以不能直接和1进行异或
        // 要找到最高位的1所在的位置pos
        // 和剩下pos-1的所有位进行异或

        // 最高位1的位置
        int pos = 0;
        for (int i = 31; i >= 0; i--) {
            // 把当前数右移i位，得到最后一位
            if (((num >>> i) & 1) == 1) {
                pos = i;
                break;
            }
        }
        // 从pos-1到0按位取反，pos取反后等于0，忽略前导0
        // 将1左移pos+1次后-1，得到与nums相同位数的1
        // 76543210 -> pos=5 最高位1在第5位上
        // 00101010 = 42
        //  1000000 -> 1<<5+1  1左移6次的结果
        // 00111111 -> (1<<5+1)-1  得到与num相同位数的1
        //^00010101 -> 异或的结果，取反
        return num ^ ((1 << pos + 1) - 1);
    }
}