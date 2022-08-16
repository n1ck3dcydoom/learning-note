package leetcode.queue.medium;

import java.util.Arrays;

/**
 * Created by n!Ck
 * Date: 2022/8/16
 * Time: 22:23
 * Description:
 */

public class _641_DesignCircularDeque {

    // 数组
    private int[] array;
    // 头指针
    private int head;
    // 尾指针
    private int tail;
    // 元素个数
    private int size;
    // 最大元素个数
    private final int maxSize;

    public static void main(String[] args) {
        _641_DesignCircularDeque deque = new _641_DesignCircularDeque(3);
        System.out.println(deque.insertLast(1));
        System.out.println(deque.insertLast(2));
        System.out.println(deque.insertFront(3));
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getRear());
        System.out.println(deque.isFull());
        System.out.println(deque.deleteLast());
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getFront());
    }

    public _641_DesignCircularDeque(int k) {
        this.array = new int[k];
        Arrays.fill(this.array, -1);
        this.head = 0;
        this.tail = 0;
        this.size = 0;
        this.maxSize = k;
    }

    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        size++;
        // 头插元素，头指针的新索引等于 head-1，为了防止 head=0时，head-1 溢出
        // head 先加上 maxSize 后再 -1，最后对 maxSize 取模，即可得到新索引位置
        head = (head + maxSize - 1) % maxSize;
        array[head] = value;
        // 如果是首次添加元素，还需要更新尾指针的位置，以保持队列元素的连续性
        if (size == 1) {
            tail = head;
        }
        return true;
    }

    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        size++;
        // 尾插元素，尾指针的新索引等于 tail+1，整数不用考虑溢出直接对 maxSize取模
        tail = (tail + 1) % maxSize;
        array[tail] = value;
        // 如果是首次添加元素，还需要更新头指针的位置，以保持队列元素的连续性
        if (size == 1) {
            head = tail;
        }
        return true;
    }

    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        size--;
        array[head] = -1;
        // +1 不用考虑负数溢出
        head = (head + 1) % maxSize;
        return true;
    }

    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        size--;
        array[tail] = -1;
        // -1 需要考虑负数溢出
        tail = (tail + maxSize - 1) % maxSize;
        return true;
    }

    public int getFront() {
        return array[head] == -1 ? -1 : array[head];
    }

    public int getRear() {
        return array[tail] == -1 ? -1 : array[tail];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }
}
