import java.util.ArrayList;
import java.util.List;

/**
 * Created by n!Ck
 * Date: 2022/5/2
 * Time: 12:52
 * Description:
 */

public class Sum {

    public static void main(String[] args) {
        int[] arr = new int[]{2, 5, 3, 4};

        // 计算前缀和数组
        int[] sum = new int[arr.length];
        sum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            sum[i] = arr[i] + sum[i - 1];
        }
        System.out.println(1);

        // 滑动窗口搜索前缀和数组
        List<int[]> res = new ArrayList<>();
        int l = -1, r = 0;

    }

}
