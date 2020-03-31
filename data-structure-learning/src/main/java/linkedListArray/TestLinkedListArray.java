package linkedListArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exception.ArrayIndexException;

/**
 * Created by n!Ck
 * Date: 2019-01-13
 * Time: 17:32
 * Description:
 */
public class TestLinkedListArray {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Random random = new Random();
            intList.add(random.nextInt(10));
        }
        intList.add(999);

        LinkedListArray<Integer> intLinkedListArray = new LinkedListArray<>();
        try {
            intLinkedListArray.init(intList.toArray(new Integer[intList.size()]), intList.size());
        } catch (ArrayIndexException e) {
            e.printStackTrace();
        }

        System.out.println(intLinkedListArray.toString());
    }
}
