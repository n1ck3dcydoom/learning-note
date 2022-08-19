package leetcode.queue.medium;

/**
 * Created by n!Ck
 * Date: 2022/8/11
 * Time: 09:56
 * Description:
 */

public class _622_DesignCircularQueue {

    public static void main(String[] args) {
        _622_DesignCircularQueue queue = new _622_DesignCircularQueue(3);
        System.out.println(queue.enQueue(1));
        System.out.println(queue.enQueue(2));
        System.out.println(queue.enQueue(3));
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());
        System.out.println(queue.isFull());
        System.out.println(queue.deQueue());
        System.out.println(queue.enQueue(4));
        System.out.println(queue.Rear());
    }

    private int[] array;
    // 队列容量
    private int maxSize;
    private int size;
    // 队头指针
    private int head;
    // 队尾指针
    private int tail;

    // 构造器，设置队列长度为 k
    public _622_DesignCircularQueue(int k) {
        this.array = new int[k];
        this.maxSize = k;
        this.size = 0;
        this.head = 0;
        this.tail = 0;
    }

    // 向循环队列插入一个元素。如果成功插入则返回真
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        size++;
        array[tail] = value;
        tail = (tail + 1) % maxSize;
        return true;
    }

    // 从循环队列中删除一个元素。如果成功删除则返回真
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        size--;
        head = (head + 1) % maxSize;
        return true;
    }

    // 从队首获取元素。如果队列为空，返回 -1
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return array[head];
    }

    // 获取队尾元素。如果队列为空，返回 -1
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return array[(tail - 1 + maxSize) % maxSize];
    }

    // 检查循环队列是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 检查循环队列是否已满
    public boolean isFull() {
        return size == maxSize;
    }
}
