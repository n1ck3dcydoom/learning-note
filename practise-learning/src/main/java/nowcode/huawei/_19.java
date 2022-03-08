package nowcode.huawei;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by n!Ck
 * Date: 2022/3/8
 * Time: 23:30
 * Description:
 */

public class _19 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // 按照输入顺序排序的hash
        Map<String, Integer> hash = new LinkedHashMap<>();
        while (scanner.hasNext()) {
            String str = scanner.nextLine();
            String[] split = str.split("\\\\");
            String key = split[split.length - 1];
            String[] keySplit = key.split(" ");
            String fileName = keySplit[0];
            String line = keySplit[1];
            // 长度超过16
            if (fileName.length() > 16) {
                fileName = fileName.substring(fileName.length() - 16);
            }
            // 循环计数
            String rKey = fileName + " " + line;
            hash.put(rKey, hash.getOrDefault(rKey, 0) + 1);
        }
        // 输出最后8个
        int size = hash.size();
        int counter = size - 8;
        for (Map.Entry<String, Integer> entry : hash.entrySet()) {
            if (counter-- > 0) {
                continue;
            }
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
