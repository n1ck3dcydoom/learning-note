package algorithm.leetcode.primeAlgorithm.day14;

public class _190_ReverseBits {
    public int reverseBits(int n) {
        // res初始化全0
        int res = 0;
        // n&1 可以得到n的最后一位
        // 然后把 n&1 得到的最后一位依次移动到前32-i的位置 res0
        // 这样res0就得到每次n的最后一个位置颠倒后的结果
        // 需要把每次res0的结果都拼接到res上
        // res初始化全0，res0每次有一个bit的值是正确的
        // 每次res | res0 可以把res0中正确的位置从高到低依次赋值到res
        // 为了不改动res前面已经排号的bit，每次赋值结束后，n无符号右移1为，高位补0
        // 这样0和res前面已经排好的bit进行 | 运算后仍然是res的前高位

        for (int i = 1; i <= 32; i++) {
            res |= (n & 1) << (32 - i);
            // 无符号右移n
            n >>>= 1;
        }
        return res;
    }
}
