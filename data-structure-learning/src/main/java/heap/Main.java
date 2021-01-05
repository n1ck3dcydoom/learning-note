package heap;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2021/1/5 16:58
 **/
public class Main {
    public static void main(String[] args) {
        int[] data = new int[]{3, 9, 6, 14,5,1,12,4};
        int n = data.length;
        // 建堆
        MaxHeap maxHeap = new MaxHeap(n);
        // 插入元素
        for (int e : data) {
            maxHeap.add(e);
        }
        // 最大堆排序
        while (maxHeap.getCount() > 0) {
            int e = maxHeap.remove();
            if (e != -1) {
                System.out.print(e + " ");
            }
        }
    }
}