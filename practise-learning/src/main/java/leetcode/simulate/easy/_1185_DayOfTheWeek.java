package leetcode.simulate.easy;

/**
 * Created by n!Ck
 * Date: 2022/1/4
 * Time: 22:40
 * Description:
 */

public class _1185_DayOfTheWeek {

    public String dayOfTheWeek(int day, int month, int year) {
        // 计算从给定日期到1971年1月1日经过了多少天days
        int sum = countDays(day, month, year);
        // 已知条件1971年1月1日为周五
        String[] weekdays = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return weekdays[sum + 4 % 7];
    }

    private int countDays(int day, int month, int year) {
        int[] days = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int sum = 0;
        // 从1971年开始计算到year
        for (int i = 1971; i <= year; i++) {
            // 判断是否是闰年
            boolean leap = i % 400 == 0 || (i % 100 != 0 && i % 4 == 0);
            days[1] = leap ? 29 : 28;
            // year年单独计算(可能是不完整年)
            if (i == year) {
                // 计算到month-1
                for (int j = 1; j < month; j++) {
                    sum += days[j - 1];
                }
                // 最后一个月直接加上天数
                sum += day;
            } else {
                sum += leap ? 366 : 365;
            }
        }
        return sum;
    }
}
