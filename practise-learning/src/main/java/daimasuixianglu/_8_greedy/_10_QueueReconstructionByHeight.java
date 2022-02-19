package daimasuixianglu._8_greedy;

import java.util.Arrays;
import java.util.LinkedList;

public class _10_QueueReconstructionByHeight {

    public static void main(String[] args) {
        int[][] p = new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}};
        System.out.println(reconstructQueue(p));
        int a = 0;
    }

    public static int[][] reconstructQueue(int[][] people) {
        // 多个维度的问题，学会控制变量
        // 先确定一个维度的问题，在考虑另外一个维度

        // 如果先把ki排序，排好后的ki对应的hi仍然是无须的，且最终的ki也不等于排序后的ki
        // 先把hi排序，再根据ki做插入调整
        // hi排序时，ki更小的放前面

        // hi排序
        Arrays.sort(people, (p1, p2) -> {
            if (p2[0] > p1[0]) {
                return 1;
            } else if (p2[0] == p1[0]) {
                return p1[1] - p2[1];
            } else {
                return -1;
            }
        });
        // 每次根据ki调整的时候，就是把当前元素插入到索引为ki的位置
        // 因为ki前面的元素的h一定是大于等于hi的，所以可以直接插入到ki的位置上
        // 需要调整n次，使用辅助数组保存中间结果
        int n = people.length;
        LinkedList<int[]> tmp = new LinkedList<>();
        for (int[] person : people) {
            tmp.add(person[1], person);
        }
        return tmp.toArray(new int[0][]);
    }
}
