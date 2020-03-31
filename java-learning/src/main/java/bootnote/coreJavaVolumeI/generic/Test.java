package bootnote.coreJavaVolumeI.generic;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {

        // 测试泛型的类型擦除
        List<String> strList = new ArrayList<String>();
        List<Integer> intList = new ArrayList<Integer>();
        System.out.println(strList.getClass() == intList.getClass() ? "true" : "false");
    }
}
