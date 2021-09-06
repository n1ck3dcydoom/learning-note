package algorithm.leetcode.primeAlgorithm.day14;

public class _136_SingleNumber {

    public static void main(String[] args) {
        int[] n1 = new int[] { 2, 2, 1 };
        int[] n2 = new int[] { 4, 1, 2, 1, 2, };
        System.out.println(singleNumber(n1));
        System.out.println(singleNumber(n2));
    }

    public static int singleNumber(int[] nums) {
        // 1、异或运算满足交换律
        // a^b^c = a^c^b
        // 2、和自已异或结果等于0
        // a^a = 0
        // 3、和0异或结果等于自己本身
        // a^0 = a

        // 4^1^2^1^2 = 1^1^2^2^4 = 0^4 = 4
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
