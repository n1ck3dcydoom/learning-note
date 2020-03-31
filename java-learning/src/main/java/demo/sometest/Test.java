package demo.sometest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by n!Ck Date: 2018/12/24 Time: 8:13 PM Description:
 */
public class Test {
    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        String str1 = new String("test");
        String str2 = new String("test");

        System.out.println(n == m); // bool1
        System.out.println(str1 == str2); // bool2
        str1.equals(str2);

        List<Integer> arrList = new ArrayList<>();
        arrList.add(3);
        arrList.add(1);
        arrList.add(5);
        arrList.add(null);
        arrList.add(9);
        arrList.add(null);
        arrList.add(6);

        for (int i = 0; i < arrList.size(); i++) {
            System.out.print(arrList.get(i) + " ");
        }
        System.out.println("\n");

        HashSet<Integer> arrSet = new HashSet<>();
        arrSet.add(3);
        arrSet.add(1);
        arrSet.add(5);
        arrSet.add(null);
        arrSet.add(9);
        arrSet.add(null);
        arrSet.add(6);

        arrSet.forEach(e -> System.out.print(e + " "));

    }
}