package algorithm.leetcode.primeAlgorithm.day3;

public class _167_TwoSum_II_InputArrayIsSorted {
    public static void main(String[] args) {
        int[] nums = new int[] { 2, 7, 11, 15 };
        int[] res = twoSum0(nums, 9);
        for (int i : res) {
            System.out.print(res[i] + " ");
        }
    }

    public static int[] twoSum0(int[] numbers, int target) {
        int n = numbers.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[] { i + 1, j + 1 };
                }
            }
        }
        return null;
    }

    public static int[] twoSum1(int[] numbers, int target) {
        // 双指针
        // 左指针头部，右指针尾部
        // 如果双指针之和小于target，说明需要增大一点
        // 右指针只能左移，和会更小
        // 左指针只能右移，和会更大，所以左指针右移
        // 同理可得，如果双指针之和大于target，需要减小和，则只能右指针左移
        // 明确有唯一解，双指针一定能在某个时刻和等于target
        int n = numbers.length;
        int l = 0, r = n - 1;
        while (l < r) {
            if (numbers[l] + numbers[r] < target) {
                l++;
            } else if (numbers[l] + numbers[r] > target) {
                r--;
            } else {
                return new int[] { l + 1, r + 1 };
            }
        }
        return null;
    }
}
