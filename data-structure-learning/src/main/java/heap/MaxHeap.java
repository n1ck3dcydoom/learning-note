package heap;

/**
 * @author zhanglei
 * @version 1.0
 * @description zl
 * 大顶堆:
 * 一颗完全二叉树的每个结点都大于等于其子结点
 * 小顶堆：
 * 一颗完全二叉树的每个结点都小于等于其子结点
 * ************  14
 * ************ /  \
 * *********** 9    12
 * ********** / \   / \
 * ********  6   1 4   5
 * ******** /
 * ******* 3
 * 可以用一个数组表示        [ 14, 9, 12, 6, 1, 4, 5, 3 ]
 * 数组不使用下标为0的元素  0,  1,  2, 3,  4, 5, 6, 7, 8
 * 数组中下标为i的结点，其左子结点（如果存在）下标 = 2i，其右子结点（如果存在）下标 = 2i + 1，其父节点（顶点除外）下标 = i/2，向下取整
 * @date 2020/12/30 15:34
 **/
public class MaxHeap {

    // 存储数据的数组
    private int[] data;
    // 堆中当前的元素个数
    private int count;
    // 堆容量的大小
    private int capacity;

    public MaxHeap(int capacity, int[] data) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Heap capacity must not less than 0");
        }
        if (data.length < capacity) {
            throw new IllegalArgumentException("Heap capacity must not less than data lenght");
        }
        this.capacity = capacity;
        this.count = 0;
        this.data = new int[data.length + 1];
        for (int i = 1; i <= data.length; i++) {
            this.data[i] = data[i - 1];
        }
    }

    /**
     * 求结点i的左子结点
     * 返回其左子结点的下标，如果不存在返回-1
     */
    private int left(int i) {
        return 2 * i > count ? -1 : 2 * i;
    }

    /**
     * 求结点i的右子结点
     * 返回其右子结点的下标，如果不存在返回-1
     */
    private int right(int i) {
        return 2 * i + 1 > count ? -1 : 2 * i + 1;
    }

    /**
     * 求结点i的父结点
     * 返回其父结点的下标，如果不存在返回-1
     */
    private int parent(int i) {
        return i / 2 == 0 ? -1 : i / 2;
    }

    /**
     * 堆化，从下往上，插入大顶堆的时候
     * 先把待插入元素放到堆最后，然后和父结点比较
     * 如果data[k]比父结点大，则交换位置，否则完成插入
     */
    private void heapUp(int k) {
        // 这里k是堆数组的下标
        // k>0表示还没有达到顶点，顶点的下标=1
        while (k > 0 && data[k] > data[parent(k)]) {

        }
    }

    private void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}