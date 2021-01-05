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
 *                           0,  1,  2, 3, 4, 5, 6, 7
 * 数组中下标为i的结点，其左子结点（如果存在）下标 = 2i+1，其右子结点（如果存在）下标 = 2i + 2，其父节点（顶点除外）下标 = (i-1)/2
 * @date 2020/12/30 15:34
 **/
public class MaxHeap {

    // 存储数据的数组
    private int[] data;
    // 堆中当前的元素个数
    private int count;
    // 堆的最大容量
    private int capacity;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * 初始化一个容量大小为capacity的空堆
     */
    public MaxHeap(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Heap capacity must not less than 0");
        }
        this.data = new int[capacity];
        this.capacity = capacity;
        this.count = 0;
    }

    /**
     * 求结点k的左子结点
     */
    private int left(int k) {
        return k * 2 + 1;
    }

    /**
     * 求结点k的右子结点
     */
    private int right(int k) {
        return k * 2 + 2;
    }

    /**
     * 求结点k的父结点
     */
    private int parent(int k) {
        return k == 0 ? -1 : (k - 1) / 2;
    }

    /**
     * 往堆中插入一个元素
     */
    public void add(int e) {
        if (this.count == this.capacity) {
            System.out.println("Heap is full");
        } else {
            // 插入元素放到数组的最后一个位置，即堆树的最后一个结点位置
            data[count] = e;
            // 当前元素个数+1
            count++;
            // 自底向上堆化
            heapUp(count - 1);
        }
    }

    public int remove() {
        if (this.count == 0) {
            System.out.println("Heap is empty");
            return -1;
        } else {
            // 把堆树的根节点移除
            int root = data[0];
            // 把堆树的最后一个结点放到根节点，作为新的根
            data[0] = data[count - 1];
            // 当前元素个数-1
            count--;
            // 自顶向下堆化
            heapDown(0);
            // 返回移除掉的根节点值
            return root;
        }
    }

    /**
     * 自底向上堆化
     * 先把待插入元素放到堆最后，然后和父结点比较
     * 如果data[k]比父结点大，则交换位置，否则完成插入
     */
    private void heapUp(int k) {
        // 这里k是堆数组的下标
        // k>0表示还没有达到顶点，顶点的下标=0
        while (k > 0 && data[k] > data[parent(k)]) {
            // 交换当前结点和父结点
            swap(k, parent(k), data);
            // k指向父结点的位置
            k = parent(k);
        }
    }

    /**
     * 自顶向下堆化，删除堆顶元素
     * 把最后一个元素放到对顶，然后从上往下开始比较
     */
    private void heapDown(int k) {

        // while循环的结束条件就是遍历到当前节点k的左孩子的下标不超过当前堆的元素个数
        while (left(k) < count) {
            // m为左孩子的下标
            int m = left(k);
            // m+1为右孩子的下标，找到当前节点的左右子孩子中的较大值
            if (m + 1 < count && data[m + 1] > data[m]) {
                // 如果右孩子大于左孩子，m指向右孩子
                m = right(k);
            }
            // 如果当前结点大于左右孩子的最大值，则已经下沉到合适的位置，直接break
            if (data[k] > data[m]) {
                break;
            }
            // 否则当前结点和左右孩子的最大值交换，然后继续判断是否需要下沉
            swap(k, m, data);
            // k指向交换后的左右孩子最大值
            k = m;
        }
    }

    private void swap(int a, int b, int[] array) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
