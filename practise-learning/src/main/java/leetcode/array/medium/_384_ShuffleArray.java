package leetcode.array.medium;

import java.util.Arrays;
import java.util.Random;

public class _384_ShuffleArray {

    private int[] initArray;
    private int n;

    public _384_ShuffleArray(int[] nums) {
        this.initArray = nums;
        this.n = nums.length;
    }

    public int[] reset() {
        return this.initArray;
    }

    public int[] shuffle() {
        final int[] array = Arrays.copyOf(initArray, this.n);
        // 1~n里面随机选择一个，和最后一个交换
        // 1~n-1里面随机选择一个，和倒数第二个交换
        // 直到剩下最后一个和自己交换，完成洗牌

        Random r = new Random();
        // 自研洗牌算法 :)  从尾部开始置换
        //        for (int i = 0; i < this.n; i++) {
        //        第i趟洗牌从前n - i个数里面随机选择一个
        //            int select = r.nextInt(n - i);
        //        把这个数和倒数第i个数交换
        //            int temp = array[n - i - 1];
        //            array[n - i - 1] = array[select];
        //            array[select] = temp;
        //        }

        // Fisher-Yates洗牌算法
        // 从头开始置换
        for (int i = 0; i < this.n; i++) {
            // select = 0~n-1
            // 随机数前面加上趟数i，避开已经选择了的前select个数
            int select = i + r.nextInt(n - i);
            // 交换第i和select
            int temp = array[select];
            array[select] = array[i];
            array[i] = temp;
        }

        return array;
    }
}
