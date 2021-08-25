package algorithm.leetcode.primeAlgorithm.day2;

public class _977_SquaresOfSortedArray {

    public static void main(String[] args) {
        int[] nums = new int[] { -7, -3, 2, 3, 11 };
        int[] res = sortedSquares(nums);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    /**
     * 要求O(n)时间复杂度完成，说明不能做排序，只能线性扫描
     */
    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        // 两头往中间扫描，正数负数的平方根大小取决于绝对值更大的那个数
        // 从res尾部开始记录扫描比较的较大值平方根
        // 直到两个指针在中间某个位置相遇，记录res最后一个头部位置

        int head = 0, tail = n - 1;
        n = n - 1;
        while (head <= tail) {
            int head2 = nums[head] * nums[head];
            int tail2 = nums[tail] * nums[tail];
            if (head2 >= tail2) {
                head++;
                res[n--] = head2;
            } else {
                tail--;
                res[n--] = tail2;
            }
        }
        return res;
    }
}
