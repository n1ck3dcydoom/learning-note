package leetcode.array.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/8/16
 * Time: 22:08
 * Description:
 */

public class _1656_DesignAOrderedStream {

    private String[] array;
    private int index = 1;

    public static void main(String[] args) {
        _1656_DesignAOrderedStream o = new _1656_DesignAOrderedStream(5);
        System.out.println(o.insert(3, "c"));
        System.out.println(o.insert(1, "a"));
        System.out.println(o.insert(2, "b"));
        System.out.println(o.insert(5, "e"));
        System.out.println(o.insert(4, "d"));
    }

    public _1656_DesignAOrderedStream(int n) {
        // 不使用下标0
        this.array = new String[n + 1];
    }

    public List<String> insert(int idKey, String value) {
        // 插入数组
        array[idKey] = value;

        List<String> res = new ArrayList<>();
        // 如果插入的位置正好是当前指针，则往后遍历直到断开或者结束
        if (index == idKey) {
            while (index < array.length && array[index] != null && !"".equals(array[index])) {
                res.add(array[index++]);
            }
        }
        return res;
    }
}
