package leetcode.array.easy;

public class _414_ThirdMaximumNumber {

    public static void main(String[] args) {

        //        int[] nums1 = new int[]{2, 2, 3, 1};
        //        System.out.println(thirdMax(nums1));
        //
        //        int[] nums2 = new int[]{1, 2};
        //        System.out.println(thirdMax(nums2));
        //
        //        int[] nums3 = new int[]{3, 2, 1};
        //        System.out.println(thirdMax(nums3));

        // 边界情况
        int[] nums4 = new int[]{1, 2, Integer.MIN_VALUE};
        System.out.println(thirdMax(nums4));
    }

    public static int thirdMax(int[] nums) {
        // 记录最大的数
        long fmax = Long.MIN_VALUE;
        // 记录第二大的数
        long smax = Long.MIN_VALUE;
        // 记录第三大的数
        long tmax = Long.MIN_VALUE;

        for (int n : nums) {
            // 如果n比fmax更大
            // n -> fmax -> smax -> tmax 传递更新
            if(n > fmax) {
                tmax = smax;
                smax = fmax;
                fmax = n;
            }
            // 如果 fmax > n > smax
            // n -> smax -> tmax 传递更新
            else if (fmax > n && n > smax) {
                tmax = smax;
                smax = n;
            }
            // 如果 smax > n > tmax
            // n -> tmax 传递更新
            else if (smax > n && n > tmax) {
                tmax = n;
            }
            // 如果 tmax > n 或者 n == tmax | smax | tmax
            // 啥也不做，访问下一个数
        }
        return (int) (tmax == Long.MIN_VALUE ? fmax : tmax);
    }
}