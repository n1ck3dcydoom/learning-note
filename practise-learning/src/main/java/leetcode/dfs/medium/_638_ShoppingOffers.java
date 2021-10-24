package leetcode.dfs.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _638_ShoppingOffers {

    public static void main(String[] args) {
        List<Integer> price = new ArrayList<>(Arrays.asList(1, 1, 1));
        List<List<Integer>> special = new ArrayList<>();
        special.add(new ArrayList<>(Arrays.asList(1, 1, 0, 0)));
        special.add(new ArrayList<>(Arrays.asList(2, 2, 1, 0)));
        List<Integer> needs = new ArrayList<>(Arrays.asList(1, 1, 1));
        System.out.println(shoppingOffers1(price, special, needs));
    }

    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        List<Integer> res = new ArrayList<>();
        // 把购买单个物品预处理成一个新的礼包，即当前礼包仅包含一个物品i，花费为price[i]
        int n = price.size();
        for (int i = 0; i < n; i++) {
            List<Integer> newSpecial = new ArrayList<>(n + 1);
            for (int j = 0; j < n + 1; j++) {
                newSpecial.add(0);
            }
            // 物品i个数为1个
            newSpecial.set(i, 1);
            // 新礼包总价为物品i的价格
            newSpecial.set(n, price.get(i));
            special.add(newSpecial);
        }
        // 我他妈直接dfs搜爆每个礼包的购买方式
        dfs(res, 0, price, needs, special);
        // 所有购物组合里面求最小值
        int min = Integer.MAX_VALUE;
        for (int cost : res) {
            min = Math.min(min, cost);
        }
        return min;
    }

    private static void dfs(List<Integer> res, int cost, List<Integer> price, List<Integer> needs,
                            List<List<Integer>> specials) {
        // 回溯的条件是needs得到满足
        boolean need = true;
        for (int n : needs) {
            // 每个物品剩余需求等于0
            need &= n == 0;
        }
        if (need) {
            res.add(cost);
            return;
        }
        // dfs搜索每一个可以购买的礼包
        // 剪枝方式是购买礼包i后超出了物品的剩余需求
        int n = price.size();
        for (List<Integer> special : specials) {
            // 剪枝，检查当前礼包和物品的剩余需求的关系
            boolean can = true;
            for (int i = 0; i < n; i++) {
                if (needs.get(i) < special.get(i)) {
                    can = false;
                    break;
                }
            }
            if (!can) {
                continue;
            }
            cost += special.get(n);
            // 更新需求
            List<Integer> newNeeds = new ArrayList<>(needs);
            for (int i = 0; i < n; i++) {
                int t = needs.get(i) - special.get(i);
                newNeeds.set(i, t);
            }
            // 继续dfs搜索
            dfs(res, cost, price, newNeeds, specials);
            // 撤销当前购买
            newNeeds = new ArrayList<>(needs);
            for (int i = 0; i < n; i++) {
                int t = needs.get(i) + special.get(i);
                newNeeds.set(i, t);
            }
            cost -= special.get(n);
        }
    }

    private static int res = Integer.MAX_VALUE;

    public static int shoppingOffers1(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        // 把购买单个物品预处理成一个新的礼包，即当前礼包仅包含一个物品i，花费为price[i]
        int n = price.size();
        for (int i = 0; i < n; i++) {
            List<Integer> newSpecial = new ArrayList<>();
            for (int j = 0; j < n + 1; j++) {
                newSpecial.add(0);
            }
            // 物品i个数为1个
            newSpecial.set(i, 1);
            // 新礼包总价为物品i的价格
            newSpecial.set(n, price.get(i));
            special.add(newSpecial);
        }
        // 暴力dfs超时了 T_T
        // 需要预处理每个礼包的最大购买个数
        List<Integer> maxSpecials = new ArrayList<>();
        for (int i = 0; i < special.size(); i++) {
            maxSpecials.add(0);
            List<Integer> s = special.get(i);
            // 如果当前礼包能买，就继续买，直到不能购买为止就是最大购买个数
            int count = Integer.MAX_VALUE;
            // 遍历每个物品
            for (int j = 0; j < n; j++) {
                if (s.get(j) > needs.get(j)) {
                    count = Integer.MAX_VALUE;
                    break;
                } else {
                    if (s.get(j) == 0) {
                        continue;
                    }
                    count = Math.min(count, needs.get(j) / s.get(j));
                }
            }
            if (count != Integer.MAX_VALUE) {
                maxSpecials.set(i, count);
            }
        }

        // 我他妈直接dfs搜爆每个礼包的购买方式
        dfs1(0, 0, needs, special, maxSpecials);
        return res;
    }

    private static void dfs1(int cost, int index, List<Integer> needs,
                             List<List<Integer>> specials, List<Integer> maxSpecials) {
        // 回溯的条件
        // 当前花费已经超过了最低花费
        if (cost >= res) {
            return;
        }
        // 遍历完所有大礼包0 <= index < specials.size()
        // 最后一次dfs会让index = specials.size()，即访问了所有大礼包之后
        if (index == specials.size()) {
            // 检查剩余需求
            for (int i = 0; i < needs.size(); i++) {
                // 仍然有物品的剩余需求没有满足
                if (needs.get(i) != 0) {
                    return;
                }
            }
            res = Math.min(res, cost);
            return;
        }
        // 当前礼包
        List<Integer> curSpecial = specials.get(index);
        // dfs搜索每一个礼包的所有购买总数
        int n = needs.size();
        for (int i = 0; i <= maxSpecials.get(index); i++) {
            boolean can = true;
            List<Integer> newNeeds = new ArrayList<>(needs);
            for (int j = 0; j < n; j++) {
                // 不能够购买i个当前礼包
                if (newNeeds.get(j) < i * curSpecial.get(j)) {
                    can = false;
                    break;
                }
                // 可以的话就直接购买i个当前礼包
                newNeeds.set(j, newNeeds.get(j) - i * curSpecial.get(j));
            }
            if (!can) {
                break;
            }
            // 继续递归dfs搜索下一个礼包的所有购买种数
            dfs1(cost + i * curSpecial.get(needs.size()), index + 1, newNeeds, specials, maxSpecials);
        }
    }
}