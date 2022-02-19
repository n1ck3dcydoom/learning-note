package daimasuixianglu._8_greedy;

public class _9_LemonadeChange {

    public boolean lemonadeChange(int[] bills) {
        // 维护2个变量，five，ten
        // 顾客分为三种情况
        // 支付5元，five+1
        // 支付10元，five-1，ten+1
        // 支付20元，分为两种找零方式
        // 1、优先找1张10元，1张5元，有five-1，ten-1
        // 2、找3张5元，five-3
        // 可以看到5元的适用范围更加广泛
        // 根据贪心策略，为了尽可能所有顾客，需要尽可能使用第一种20元的找零方案

        int five = 0, ten = 0;
        // 遍历所有顾客
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                // 没有5元找零了，无法满足所有顾客
                if (five == 0) {
                    return false;
                }
                five--;
                ten++;
            } else {
                // 优先使用10+5找零
                if (ten > 0 && five > 0) {
                    five--;
                    ten--;
                }
                // 其次使用5+5+5找零
                else if (five > 2) {
                    five -= 3;
                } else {
                    // 找不开就返回false
                    return false;
                }
            }
        }
        return true;
    }
}
