package leetcode.array.easy;

/**
 * Created by n!Ck
 * Date: 2022/8/19
 * Time: 09:10
 * Description:
 */

public class _1450_NumberOfStudentsDoingHomeworkAtGivenTime {

    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int n = startTime.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]) {
                res++;
            }
        }
        return res;
    }
}
