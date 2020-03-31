package nowcode.WeiPinHui_A;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2019-02-21
 * Time: 22:35
 * Description:
 */
public class ListTest {
    private static void resetList(List<Integer> dataList) {
        dataList.subList(2, 4).set(0, 40);
        dataList = new ArrayList<>(dataList);
        dataList.add(50);
    }

    private static void setOne(List<Integer> dataList) {
        dataList.set(3, 100);
    }

    public static void main(String[] args) {
        List<Integer> dataList = new ArrayList<>(Arrays.asList(10, 20, 30, null));
        resetList(dataList);
        setOne(dataList);
        int sum = 0;
        for (Integer v : dataList) {
            sum += v;
        }
        System.out.println(sum);
    }
}