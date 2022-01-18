package daimasuixianglu._1_array;

public class _6_RemoveElement {

    public static void main(String[] args) {
        int[] n1 = new int[]{3, 2, 2, 2, 0, 4, 5, 2, 1};
        System.out.println(removeElement1(n1, 2));

        // int[] n2 = new int[]{1};
        // System.out.println(removeElement(n2, 1));
    }

    public static int removeElement(int[] nums, int val) {
        // 两层for循环暴力遍历
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 找到需要移除的元素
            if (nums[i] == val) {
                // 将后面的数组整体前移
                for (int j = i + 1; j < n; j++) {
                    nums[j - 1] = nums[j];
                }
                // 当j更新完最后一个元素n-1时，把删除的元素放到最后面，但是不用再去访问它
                // 调整i的遍历范围n-1
                n--;
                // 有可能i+1前移后仍然是需要移除的元素，此时外层for更新i=i+1，倒置前移后的i+1没法访问了
                // 调整i的位置--，继续访问前移后新的i+1位置
                // 3 2 2 4 5 0   1  n=7  i=1
                // 3 2 4 5 0 1 | 2  n=6  i=2 需要i--回到上一个位置
                i--;
            }
        }
        return n;
    }

    public static int removeElement1(int[] nums, int val) {
        // 一层for循环，快慢双指针
        int n = nums.length;
        // 慢指针永远指向更新数组的最后一个元素
        int s = 0, f = 0;
        for (; f < n; f++) {
            // 快指针指向的元素不是需要删除的，就和慢指针一起前进
            // 同时需要把非删除元素赋值给慢指针
            if (nums[f] != val) {
                nums[s++] = nums[f];
            }
            // 快指针指向的元素需要删除，就让快指针自己后移
        }
        return s;
    }
}
