package leetcode.array.easy;

public class _268_MissingNumber {

    public static void main(String[] args) {
        int[] nums1 = new int[]{3, 0, 1};
        System.out.println(missingNumber1(nums1));
        //        int[] nums2 = new int[]{0, 1};
        //        System.out.println(missingNumber(nums2));
        //        int[] nums3 = new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1};
        //        System.out.println(missingNumber(nums3));
        //        int[] nums4 = new int[]{0};
        //        System.out.println(missingNumber(nums4));
    }

    public static int missingNumber(int[] nums) {
        // 线性查找一遍，只能使用常数级别的额外空间
        int n = nums.length;
        // 不能再开辟数组或者使用hash保存已经出现过的数字
        // 数组长度为n，则所有数字范围在[0,n]
        // 直接计算1~n且公差为1的等差数列和
        // 项数n (n-1)/1+1=n
        // 和sum (1+n)*n/2
        int sum = (1 + n) * n / 2;
        // 遍历一遍，每次用sum减去出现的数字，最后剩下的即为没有出现的数字
        for (int num : nums) {
            sum -= num;
        }
        return sum;
    }

    public static int missingNumber1(int[] nums) {
        // 在nums后面添加0~n的每个整数
        // 这样总计有2n+1个数
        // 从n+1到2n+1代表了0到n的每个数
        // 前n个数代表了0到n但是缺少了一个数

        // 根据异或运算的性质 x^x=0 x^0=x 且异或运算满足交换律
        // 这样2n+1个数进行异或运算后，剩下的就是缺少的那个数
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < 2 * n + 1; i++) {
            if (i < n) {
                res ^= nums[i];
            } else {
                res ^= i - n;
            }
        }
        return res;
    }
}
