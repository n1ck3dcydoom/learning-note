package leetcode.hash.medium;

import java.util.*;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description leetcode
 * <p>
 * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
 * <p>
 * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
 * <p>
 * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：orders = [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
 * 输出：[["Table","Beef Burrito","Ceviche","Fried Chicken","Water"],["3","0","2","1","0"],["5","0","1","0","1"],["10","1","0","0","0"]]
 * 解释：
 * 点菜展示表如下所示：
 * Table,Beef Burrito,Ceviche,Fried Chicken,Water
 * 3    ,0           ,2      ,1            ,0
 * 5    ,0           ,1      ,0            ,1
 * 10   ,1           ,0      ,0            ,0
 * 对于餐桌 3：David 点了 "Ceviche" 和 "Fried Chicken"，而 Rous 点了 "Ceviche"
 * 而餐桌 5：Carla 点了 "Water" 和 "Ceviche"
 * 餐桌 10：Corina 点了 "Beef Burrito"
 * 示例 2：
 * <p>
 * 输入：orders = [["James","12","Fried Chicken"],["Ratesh","12","Fried Chicken"],["Amadeus","12","Fried Chicken"],["Adam","1","Canadian Waffles"],["Brianna","1","Canadian Waffles"]]
 * 输出：[["Table","Canadian Waffles","Fried Chicken"],["1","2","0"],["12","0","3"]]
 * 解释：
 * 对于餐桌 1：Adam 和 Brianna 都点了 "Canadian Waffles"
 * 而餐桌 12：James, Ratesh 和 Amadeus 都点了 "Fried Chicken"
 * 示例 3：
 * <p>
 * 输入：orders = [["Laura","2","Bean Burrito"],["Jhon","2","Beef Burrito"],["Melissa","2","Soda"]]
 * 输出：[["Table","Bean Burrito","Beef Burrito","Soda"],["2","1","1","1"]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= orders.length <= 5 * 10^4
 * orders[i].length == 3
 * 1 <= customerNamei.length, foodItemi.length <= 20
 * customerNamei 和 foodItemi 由大小写英文字母及空格字符 ' ' 组成。
 * tableNumberi 是 1 到 500 范围内的整数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/display-table-of-food-orders-in-a-restaurant
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @date 2021/7/6 16:44
 **/
public class _1418_DisplayTableOfFoodOrdersInRestaurant {

    public static void main(String[] args) {
        List<List<String>> orders = new ArrayList<>();

        List<String> s1 = new ArrayList<>();
        s1.add("David");
        s1.add("3");
        s1.add("Ceviche");
        orders.add(s1);

        List<String> s2 = new ArrayList<>();
        s2.add("Corina");
        s2.add("10");
        s2.add("Beef Burrito");
        orders.add(s2);

        List<String> s3 = new ArrayList<>();
        s3.add("David");
        s3.add("3");
        s3.add("Fried Chicken");
        orders.add(s3);

        List<String> s4 = new ArrayList<>();
        s4.add("Carla");
        s4.add("5");
        s4.add("Water");
        orders.add(s4);

        List<String> s5 = new ArrayList<>();
        s5.add("Carla");
        s5.add("5");
        s5.add("Ceviche");
        orders.add(s5);

        List<String> s6 = new ArrayList<>();
        s6.add("Rous");
        s6.add("3");
        s6.add("Ceviche");
        orders.add(s6);

        List<List<String>> res = displayTable(orders);

        for (List<String> listOuter : res) {
            for (String str : listOuter) {
                System.out.print(str + "\t");
            }
            System.out.print("\n");
        }

    }

    public static List<List<String>> displayTable(List<List<String>> orders) {
        // 遍历orders每一行，可以得到哪一桌点了哪个菜，这里 (桌子，菜名) 可以构成一个hash表
        // 每一桌上的菜需要统计出现次数，这里 (菜名，次数) 可以构成一个hash表

        // 设计数据结构
        // (桌子，(菜名，次数)) 其中桌子需要按照桌号升序排序，外层使用TreeMap
        // 菜名也需要按照字典序升序排序，内层也是用TreeMap
        TreeMap<Integer, TreeMap<String, String>> resMap = new TreeMap<>();
        // 获得所有的菜名
        TreeSet<String> foodSet = new TreeSet<>();
        // 遍历点菜顺序
        // ["David","3","Ceviche"]
        for (List<String> order : orders) {
            // 获取桌号
            int tableNumber = Integer.parseInt(order.get(1));
            // 获取菜名
            String foodName = order.get(2);
            foodSet.add(foodName);
            // 获取桌号对应的菜名map
            TreeMap<String, String> tableMap = resMap.getOrDefault(tableNumber, new TreeMap<>());
            // 获取菜名出现的次数
            int count = Integer.parseInt(tableMap.getOrDefault(foodName, "0"));
            tableMap.put(foodName, String.valueOf(count + 1));
            resMap.put(tableNumber, tableMap);
        }
        List<List<String>> resList = new ArrayList<>();
        List<String> header = new ArrayList<>();
        header.add("Table");
        header.addAll(foodSet);
        resList.add(header);

        int foodCount = foodSet.size();
        for (Map.Entry<Integer, TreeMap<String, String>> entry : resMap.entrySet()) {
            List<String> tableList = new ArrayList<>();
            tableList.add(String.valueOf(entry.getKey()));
            for (int i = 0; i < foodCount; i++) {
                tableList.add(entry.getValue().getOrDefault(header.get(i + 1), "0"));
            }
            resList.add(tableList);
        }
        return resList;
    }
}