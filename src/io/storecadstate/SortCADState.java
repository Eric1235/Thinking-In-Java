package io.storecadstate;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */
public class SortCADState {
    public static void main(String[] args)throws Exception{
        List<Class<? extends Shape>> shapeTypes = new ArrayList<>();
        shapeTypes.add(Shape.Circle.class);
        shapeTypes.add(Shape.Square.class);
        shapeTypes.add(Shape.Line.class);

        List<Shape> shapes = new ArrayList<>();
        for (int i = 0 ; i < 10 ; i ++){
            shapes.add(Shape.randomFactory());
        }
        for (int i = 0 ; i < 10 ; i ++){
            (shapes.get(i)).setColor(Shape.BLUE);
        }

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("src/io/file/CADState.out"));
        out.writeObject(shapeTypes);
        Shape.Line.serializeStaticState(out);
        out.writeObject(shapes);
        System.out.println(shapes);
    }
}
