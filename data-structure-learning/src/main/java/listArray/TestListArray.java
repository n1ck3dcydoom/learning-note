package listArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exception.ArrayIndexException;
import exception.ArrayOverflowException;

/**
 * Created by n!Ck
 * Date: 2019-01-13
 * Time: 15:02
 * Description:
 */
public class TestListArray {
    public static void main(String[] args) {
        List<Integer> intList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Random random = new Random();
            intList.add(random.nextInt(10));
        }
        intList.add(999);
        ListArray<Integer> intListArray = new ListArray<>();
        intListArray.init(intList.toArray(new Integer[intList.size()]), 15);

        // 查询元素999
        System.out.println("查询线性表是否存在999元素： " + intListArray.containsElem(999));

        // 查询0号位置的元素
        try {
            System.out.println("查询线性表0号位置的元素： " + intListArray.getElem(0));
        } catch (ArrayIndexException e) {
            e.printStackTrace();
        }

        // 删除元素
        System.out.println("删除位置5之前的线性表： " + intListArray.toString());
        System.out.println("删除之前的线性表元素个数： " + intListArray.getElemCount());
        try {
            System.out.println("删除的元素是： " + intListArray.delete(5));
        } catch (ArrayIndexException | ArrayOverflowException e) {
            e.printStackTrace();
        }
        System.out.println("删除位置5之后的线性表： " + intListArray.toString());
        System.out.println("删除之后的线性表长度： " + intListArray.getElemCount());

        System.out.println();
        System.out.println();

        // 增加元素
        System.out.println("增加位置5之前的线性表： " + intListArray.toString());
        System.out.println("增加之前的线性表长度： " + intListArray.getElemCount());
        try {
            System.out.println("增加的元素是： " + intListArray.insert(5555, 5));
        } catch (ArrayIndexException | ArrayOverflowException e) {
            e.printStackTrace();
        }
        System.out.println("增加位置5之后的线性表： " + intListArray.toString());
        System.out.println("增加之后的线性表长度： " + intListArray.getElemCount());

        System.out.println();
        System.out.println();

        // 有元素的线性表是否为空
        System.out.println("此时线性表是否为空： " + intListArray.isEmpty());

        // 置空线性表
        intListArray.clearListArray();
        System.out.println("清空线性表");

        // 再次判空
        System.out.println("此时线性表是否为空： " + intListArray.isEmpty());
        System.out.println("此时的线性表： " + intListArray.toString());
    }
}
