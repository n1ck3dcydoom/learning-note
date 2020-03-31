package algorithm.sort;

/**
 * Created by n!Ck
 * Date: 2019-03-04
 * Time: 21:57
 * Description:
 * 堆排序
 * <p>
 * 大顶堆排序
 */

//                              〇50
//                              /   \
//                          ①10     90②
//                           /  \    / \
//                       ③30  ④70 40  80⑥
//                        /  \      ⑤
//                     ⑦60  20⑧
public class HeapSort {
    public static void main(String[] args) {
        // 待排序数组
        int[] num = new int[]{50, 10, 90, 30, 70, 12, 15, 17, 22, 36, 48, 68, 92, 49, 40, 80, 60, 20};
        heapSort(num);
        for (int n : num) {
            System.out.print(n + " ");
        }
    }

    private static void heapSort(int[] num) {
        // 把无序数组变为大顶堆数组，数组从0开始索引
        // 从 (num.length/2) - 1 开始，这个节点和这个节点之前的节点都是有孩子的
        for (int i = (num.length / 2) - 1; i >= 0; i--) {
            heapAdjust(num, i, num.length);
        }
        // 堆排序
        for (int i = num.length - 1; i >= 0; i--) {
            // 每次排序开始时把堆顶元素和最后一个元素交换，然后移除最后一个元素，接着调整剩下的元素为新的大顶堆
            int temp = num[0];
            num[0] = num[i];
            num[i] = temp;
            // 这里的i即为移除最后元素后新的待排序数组的长度
            heapAdjust(num, 0, i);
        }
    }

    private static void heapAdjust(int[] num, int s, int length) {
        // 保存当前节点
        int temp = num[s];
        // 当前节点索引为j，则他的左孩子节点索引为2*j+1，右孩子节点索引为2*j+2
        // 如果当前节点j还有左孩子节点（j<length），则继续遍历它的孩子节点，直到找到比它大的孩子节点
        for (int j = 2 * s + 1; j < length - 1; j = 2 * j + 1) {
            // 如果右孩子大于左孩子，则j指向更大的右孩子
            if (num[j + 1] > num[j]) {
                j++;
            }
            if (temp > num[j]) {
                break;
            }
            // 交换当前节点和孩子节点
            // 交换的时候要注意把s更新为大的孩子节点的index
            // 因为交换后的孩子节点的子树可能会受到影响，所以要继续循环调整
            num[s] = num[j];
            s = j;
            num[s] = temp;
        }
    }
}
