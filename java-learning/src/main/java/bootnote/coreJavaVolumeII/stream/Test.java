package bootnote.coreJavaVolumeII.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Test {
    public static void main(String[] args) {
        List<String> strList = new ArrayList<>();
        strList.add("Tony");
        strList.add("Mike");
        strList.add("Smith");

        Optional<String> strOptional = strList.stream().filter(e -> e.startsWith("T")).findFirst();
        strOptional.orElse("");
        if (strOptional.isPresent()) {
            System.out.println("yes");
          System.out.println(strOptional.get());
        } else {
            System.out.println("no");
        }
    }
}