package serializable;

import lombok.Data;

import java.io.*;

public class SerializableUidTest {

    // serializableUID，用来校验序列化和反序列化的版本
    // 将对象序列化到文件时，指定的uid为x
    // 反序列化的时候，jvm会校验反序列化内容里面的uid，如果和指定反序列化对象的uid不一致
    // 则会抛一个反序列化失败的异常
    public static void main(String[] args) {
        Person smith = new Person("Smith", 45);
        // 序列化到文件
        serialize(smith);
        // 从文件反序列化
        Person john = deserialize("E:\\learning-note\\java-learning\\src\\main\\java\\serializable\\john.txt");
        System.out.println("This is john, hello guys: " + john.toString());
    }

    private static void serialize(Person person) {
        try (FileOutputStream fos = new FileOutputStream(
                "E:\\learning-note\\java-learning\\src\\main\\java\\serializable\\smith.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            System.out.println("We have serialized a person: " + person.toString());
            oos.writeObject(person);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 准备好文件john.txt，按照4616048657842066507L进行序列化的
     * <p>
     * 如果修改了下面Person类的serializableUID，则将会抛出异常
     */
    private static Person deserialize(String filePath) {
        try (FileInputStream fis = new FileInputStream(filePath);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            Person person = (Person) ois.readObject();
            System.out.println("We have deserialized a person: " + person.toString());
            return person;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

@Data
class Person implements Serializable {

    private static final long serialVersionUID = 4616048657842066507L;

    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{" + name + ", " + age + "}";
    }
}
