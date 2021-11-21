package leetcode.easy.array;

import java.util.Arrays;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2020/7/21 19:37
 **/
public class _274_HIndex {

    public static void main(String[] args) {
        int[] nums = new int[]{3, 0, 6, 1, 5};
        hIndex1(nums);
    }

    // 比较排序
    public static int hIndex(int[] citations) {
        int len = citations.length;
        // 排序后的数组（升序排序）
        Arrays.sort(citations);
        // 论文序号
        int h = 1;
        // 倒序遍历，但是比较序号需要从0开始
        for (int i = len - 1; i >= 0; i--) {
            if (citations[i] < h) {
                break;
            }
            h++;
        }
        return h - 1;
    }

    // 计数排序
    public static int hIndex1(int[] citations) {
        int len = citations.length;
        int[] temp = new int[len + 1];
        // 去掉超出数组长度的引用次数
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] > len) {
                citations[i] = len;
            }
        }
        // 计数排序
        for (int i = 0; i < len; i++) {
            temp[citations[i]]++;
        }
        // 取数
        return 0;
    }
}