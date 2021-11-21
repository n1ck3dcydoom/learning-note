package factory.simple;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description TODO
 * @date 2020/12/15 19:21
 **/
public class Main {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        shapeFactory.getShape("circle").draw();
        shapeFactory.getShape("rect").draw();
        shapeFactory.getShape("triangle").draw();
    }
}