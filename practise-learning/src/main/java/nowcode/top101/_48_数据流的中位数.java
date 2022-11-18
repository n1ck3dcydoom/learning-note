package nowcode.top101;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/11/18
 * Time: 15:38
 * Description:
 */

public class _48_数据流的中位数 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 2, 3, 4, 1, 6, 7, 0, 8};
        int n = arr.length;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            Insert(arr[i]);
            res[i] = GetMedian();
        }
    }

    private static List<Integer> list = new ArrayList<>();

    public static void Insert(Integer num) {
        // 每次获取到一个数后,本质就是插入到之前已经排好序的数组里面
        // 采用插入排序
        int n = list.size();
        int i = 0;
        // 注意在迭代里面不允许插入或者删除集合当中的元素
        while (i < n) {
            if (list.get(i) <= num) {
                i++;
            } else {
                break;
            }
        }
        list.add(i, num);
    }

    public static Double GetMedian() {
        int n = list.size();
        if (n % 2 == 0) {
            int a = list.get(n / 2 - 1);
            int b = list.get(n / 2);
            return (double) (a + b) / 2;
        } else {
            return (double) list.get(n / 2);
        }
    }
}
