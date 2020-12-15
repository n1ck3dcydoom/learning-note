package factory.simple;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/12/15 10:39
 **/
public class ShapeFactory {
    public Shape getShape(String shapeType) {
        switch (shapeType) {
            case "circle":
                return new CircleShape();
            case "rect":
                return new RectShape();
            case "triangle":
                return new TriangleShape();
        }
        return null;
    }
}