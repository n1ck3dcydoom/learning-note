package nowcode.top101;

import java.util.ArrayList;

import leetcode.data.Interval;

/**
 * Created by n!Ck
 * Date: 2022/11/20
 * Time: 20:07
 * Description:
 */

public class _89_合并区间 {

    public static void main(String[] args) {
        ArrayList<Interval> l1 = new ArrayList<>();
        l1.add(new Interval(10, 30));
        l1.add(new Interval(20, 60));
        l1.add(new Interval(80, 100));
        l1.add(new Interval(150, 180));
        for (Interval i : merge(l1)) {
            System.out.println(i);
        }

        ArrayList<Interval> l2 = new ArrayList<>();
        l2.add(new Interval(0, 10));
        l2.add(new Interval(10, 20));
        System.out.println(merge(l2));
    }

    public static ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> res = new ArrayList<>();
        if (intervals == null || intervals.size() == 0) {
            return res;
        }
        // 我他妈上来就是一个排序
        intervals.sort((i1, i2) -> i1.start - i2.start);

        Interval pre = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (pre.end < cur.start) {
                res.add(new Interval(pre.start, pre.end));
                pre = cur;
            } else {
                pre.end = Math.max(pre.end, cur.end);
            }
        }
        res.add(pre);
        return res;
    }
}
