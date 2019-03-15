package java8supplierfactory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FactoryPatternDemo {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        //call draw method of circle
        shapeFactory.getShape("circle").draw();
        //call draw method of Rectangle
        shapeFactory.getShape("rectangle").draw();
    }
}
class ShapeFactory {
    final static Map<String, Supplier<Shape>> map = new HashMap<>();

    static {
        map.put("CIRCLE", Circle::new);
        map.put("RECTANGLE", Rectangle::new);
    }

    public Shape getShape(String shapeType) {
        Supplier<Shape> shape = map.get(shapeType.toUpperCase());
        if (shape != null) {
            return shape.get();
        }
        throw new IllegalArgumentException("No such shape " + shapeType.toUpperCase());
    }
}
interface Shape {
    void draw();
}

class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}