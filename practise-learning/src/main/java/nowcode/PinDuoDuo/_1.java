package nowcode.PinDuoDuo;

import java.util.*;

/**
 * Created by n!Ck
 * Date: 2019-03-10
 * Time: 16:30
 * Description:
 * <p>
 * 给出长度都为n的两个数组a[n]和b[n]，特殊运算S=a[0]*b[0]+a[1]*b[1]+....+a[n-1]*b[n-1]
 * 你可以改变数组a[n]的顺序使得S的值最小，输出最小的S
 * 1<=n<=50，每个元素X   0<=X<=100
 * <p>
 * 输入共三行：
 * 第一行为n，表示两个数组的长度
 * 第二行为n个数字，以空格隔开，表示a[n]的每个元素
 * 第三行为n个数字，以空格隔开，表示b[n]的每个元素
 * <p>
 * 输出一行：
 * 输出最小的S值
 * <p>
 * 例如：
 * 输入：
 * 3
 * 1 1 3
 * 10 30 20
 * <p>
 * 输出：
 * 80
 */
public class _1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 数组长度
        int n = Integer.parseInt(scanner.nextLine());
        String[] aStr = scanner.nextLine().split(" ");
        String[] bStr = scanner.nextLine().split(" ");

        List<Integer> aList = new ArrayList<>();
        List<Integer> bList = new ArrayList<>();

        // a，b数组
        for (int i = 0; i < n; i++) {
            aList.add(Integer.parseInt(aStr[i]));
        }
        for (int i = 0; i < n; i++) {
            bList.add(Integer.parseInt(bStr[i]));
        }
        // 把b数组排序，并且保存每个元素排序后的位置
        List<Integer> temp = new ArrayList<>(bList);
        temp.sort(Comparator.comparingInt(v -> v));

        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer i : bList) {
            for (int j = 0; j < temp.size(); j++) {
                if (i.equals(temp.get(j))) {
                    map.put(j, i);
                }
            }
        }

        int sum = 0;
        aList.sort(Comparator.comparingInt(v -> v));
        for (int i = 0; i < aList.size(); i++) {
            sum += aList.get(i) * map.get(n - i - 1);
        }
        System.out.println(sum);
    }
}
