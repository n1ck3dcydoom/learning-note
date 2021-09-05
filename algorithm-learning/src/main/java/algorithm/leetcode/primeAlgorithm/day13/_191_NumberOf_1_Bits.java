package algorithm.leetcode.primeAlgorithm.day13;

public class _191_NumberOf_1_Bits {
    public int hammingWeight(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }
}
