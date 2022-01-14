package leetcode.heap.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _373_FindKPairsWithSmallestSums {

    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 7, 11};
        int[] nums2 = new int[]{2, 4, 6};
        List<List<Integer>> res = kSmallestPairs(nums1, nums2, 3);
        for (List<Integer> array : res) {
            System.out.print(array.get(0) + " " + array.get(1));
            System.out.println("\n");
        }
    }

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((o1, o2) -> nums1[o1[0]] + nums2[o1[1]] - (nums1[o2[0]] + nums2[o2[1]]));
        int n1 = nums1.length;
        int n2 = nums2.length;
        // 第一小的肯定是[0,0]
        // 次小的数是[1,0]还是[0,1]这个没法确定
        // 由于nums1和nums2已经排序,那么以下序列也是有序的
        // [nums1[0],nums2[0]] < [nums1[0],nums2[1]] < [nums1[0],nums2[2]] < .... <
        // [nums1[0],nums[n2]]
        // [nums1[1],nums2[0]] < [nums1[1],nums2[1]] < [nums1[1],nums2[2]] < .... <
        // [nums1[1],nums[n2]]
        // [nums1[2],nums2[0]] < [nums1[2],nums2[1]] < [nums1[2],nums2[2]] < .... <
        // [nums1[2],nums[n2]]
        // ....
        // [nums1[n1],nums2[0]] < [nums1[n1],nums2[1]] < [nums1[n1],nums2[2]] < .... <
        // [nums1[n1],nums[n2]]
        // 当确定TopMinK为[nums1[i],nums2[j]]之后,根据上面的表格可以知道下一个TopMinK的数一定在[nums1[i+1],nums2[j]]和[nums1[i],nums2[j+1]]中产生

        // 首先把[0,0],[1,0],[2,0]....[n1,0]加入堆
        // 下标入堆
        for (int i = 0; i < n1; i++) {
            minHeap.offer(new int[]{i, 0});
        }
        List<List<Integer>> res = new ArrayList<>();
        // 最多弹出k个元素
        while (k-- > 0 && minHeap.size() > 0) {
            // 弹出当前topK元素
            int[] min = minHeap.poll();
            res.add(Arrays.asList(nums1[min[0]], nums2[min[1]]));
            // 弹出元素所在行的下一个元素入队
            if (min[1] + 1 < n2) {
                minHeap.offer(new int[]{min[0], min[1] + 1});
            }
        }
        return res;
    }
}
