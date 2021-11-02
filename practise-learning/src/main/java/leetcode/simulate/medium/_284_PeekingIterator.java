package leetcode.simulate.medium;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _284_PeekingIterator implements Iterator<Integer> {

    private Integer[] datas;
    private int size;
    private int cursor;

    public _284_PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        if (!iterator.hasNext()) {
            this.datas = new Integer[0];
            this.size = 0;
            this.cursor = 0;
            return;
        }
        List<Integer> tmp = new ArrayList<>();
        while (iterator.hasNext()) {
            tmp.add(iterator.next());
        }
        this.datas = new Integer[tmp.size()];
        for (int i = 0; i < tmp.size(); i++) {
            this.datas[i] = tmp.get(i);
        }
        this.size = tmp.size();
        this.cursor = 0;
        tmp = null;
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        if (hasNext()) {
            // 仅仅返回游标指向的元素，不移动游标
            return this.datas[cursor];
        }
        return -1;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        // 题目保证了每次next和peek操作均有效
        // 也就是说next和peek操作一定会在还有元素的情况下进行
        // 虽然如此，还是要判断cursor和size的关系
        if (this.hasNext()) {
            return this.datas[cursor++];
        }
        return -1;
    }

    @Override
    public boolean hasNext() {
        return cursor < size;
    }
}