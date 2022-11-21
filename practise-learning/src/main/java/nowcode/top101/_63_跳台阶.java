package nowcode.top101;

/**
 * Created by n!Ck
 * Date: 2022/11/19
 * Time: 15:41
 * Description:
 */

public class _63_跳台阶 {

    public int jumpFloor(int target) {
        if (target == 1) {
            return 1;
        }
        int p = 1;
        int pp = 2;
        for (int i = 3; i <= target; i++) {
            int t = p + pp;
            p = pp;
            pp = t;
        }
        return pp;
    }
}
