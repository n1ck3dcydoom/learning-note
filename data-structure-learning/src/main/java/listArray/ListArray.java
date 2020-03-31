package listArray;

import exception.ArrayIndexException;
import exception.ArrayOverflowException;

/**
 * Created by n!Ck
 * Date: 2019-01-12
 * Time: 19:36
 * Description:
 */
public class ListArray<T> {
    private T[] ts;
    private int length;
    private int count;

    /**
     * 初始化一个线性表
     */
    public ListArray() {
        this.ts = null;
        this.length = 0;
    }

    /**
     * 使用已经存在的数组初始化一个线性表
     */
    public void init(T[] ts, int length) {
        this.ts = (T[]) new Object[length];
        for (int i = 0; i < ts.length; i++) {
            this.ts[i] = ts[i];
        }
        this.length = length;
        this.count = ts.length;
    }

    /**
     * 判断一个线性表是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        if (this.ts == null) {
            return true;
        } else if (this.ts != null && this.count == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 清空一个线性表
     */
    public void clearListArray() {
        this.ts = null;
        this.length = 0;
        this.count = 0;
    }

    /**
     * 获取第i个元素，如果下标越界，则抛出数组越界异常
     *
     * @return T
     */
    public T getElem(int i) throws ArrayIndexException {
        if (i < 0 || i > count) {
            throw new ArrayIndexException("位置i下标越界");
        } else {
            return (T) ts[i];
        }
    }

    /**
     * 查询线性表是否存在元素e，如果存在则返回下标pos，如果不存在则返回-1
     *
     * @return int
     */
    public int containsElem(T elem) {
        for (int i = 0; i < this.count; i++) {
            if (ts[i].equals(elem)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 在线性表中第i位置插入元素elem，如果插入后超出线性表容量，则抛出异常
     *
     * 首先从尾部开始遍历，依次向后移动一个位置，直到指针达到位置i，插入元素
     *
     * @return T
     */
    public T insert(T elem, int pos) throws ArrayIndexException, ArrayOverflowException {
        if (pos < 0 || pos >= length) {
            throw new ArrayIndexException("位置i下标越界");
        }
        if (count + 1 > length) {
            throw new ArrayOverflowException("超出数组长度");
        }
        if (pos > count) {
            ts[pos] = elem;
            return elem;
        }
        // 从尾部开始遍历
        int i = count - 1;
        while (i >= pos) {
            ts[i + 1] = ts[i--];
        }
        // 插入元素
        ts[pos] = elem;
        count++;
        return elem;
    }

    /**
     * 删除线性表中第i位置的元素，并返回这个元素的值，其他情况则抛出异常
     *
     * @return T
     */
    public T delete(int pos) throws ArrayIndexException, ArrayOverflowException {
        if (length == 0) {
            throw new ArrayOverflowException("不能删除空数组的元素");
        }
        if (pos < 0 || pos >= count) {
            throw new ArrayIndexException("位置i下标越界");
        }
        // 从pos位置开始，后面元素依次向前移动一个位置
        int i = pos;
        T temp = ts[i];
        while (i < count - 1) {
            ts[i] = ts[i + 1];
            i++;
        }
        // 删除最后一个元素
        ts[i] = null;
        count--;
        return temp;
    }

    /**
     * 返回线性表的元素个数
     *
     * @return int
     */
    public int getElemCount() {
        return this.count;
    }

    /**
     * 返回线性表的容量大小
     *
     * @return int
     */
    public int getLength() {
        return this.length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[ listArray : ");
        if (ts != null) {
            for (T t : ts) {
                if (t != null) {
                    stringBuilder.append(t.toString()).append(" ");
                } else {
                    stringBuilder.append("null").append(" ");
                }
            }
        } else {
            stringBuilder.append("null ");
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
