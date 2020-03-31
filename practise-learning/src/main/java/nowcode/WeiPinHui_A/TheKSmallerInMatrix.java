package nowcode.WeiPinHui_A;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2019-02-21
 * Time: 21:52
 * Description:
 */
public class TheKSmallerInMatrix {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] firstLine = scanner.nextLine().split(" ");
            int k = Integer.parseInt(firstLine[0]);
            int n = Integer.parseInt(firstLine[1]);
            List<Integer> numberList = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] nextLineNumbers = scanner.nextLine().split(" ");
                for (int j = 0; j < n; j++) {
                    numberList.add(Integer.parseInt(nextLineNumbers[j]));
                }
            }
            numberList.sort(Comparator.comparingInt(v -> v));
            System.out.println(numberList.get(k - 1));
        }
    }
}
