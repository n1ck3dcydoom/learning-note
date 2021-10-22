package leetcode.array.medium;

import java.util.ArrayList;
import java.util.List;

public class _229_MajorityElementII {

    public static void main(String[] args) {
        int[] nums = new int[]{1 };
        List<Integer> res = majorityElement(nums);
        for (int n : res) {
            System.out.print(n + " ");
        }
    }

    public static List<Integer> majorityElement(int[] nums) {
        // 摩尔投票算法
        int x = 0;
        int vx = 0;
        int y = 0;
        int vy = 0;
        for (int n : nums) {
            if (vx > 0 && x == n) {
                vx++;
            } else if (vy > 0 && y == n) {
                vy++;
            } else if (vx == 0) {
                x = n;
                vx++;
            } else if (vy == 0) {
                y = n;
                vy++;
            } else if (x != n && y != n) {
                vx--;
                vy--;
            }
        }
        // 投完票后只找到出现次数最多的两个数
        // 但这两个数出现此处不一定大于n/3
        // 还需要统计次数
        int cx = 0;
        int cy = 0;
        for (int n : nums) {
            if (vx > 0 && n == x) {
                cx++;
            } else if (vy > 0 && n == y) {
                cy++;
            }
        }
        int l = nums.length;
        List<Integer> res = new ArrayList<>();
        if (vx > 0 && cx > l / 3) {
            res.add(x);
        }
        if (vy > 0 && cy > l / 3) {
            res.add(y);
        }
        return res;
    }
}