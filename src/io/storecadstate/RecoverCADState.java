package io.storecadstate;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */
public class RecoverCADState {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception{
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("src/io/file/CADState.out"));

        List<Class<? extends Shape>> shapeTypes = (List<Class<? extends Shape>>)in.readObject();

        Shape.Line.deserializeStaticState(in);

        List<Shape> shapes = (List<Shape>) in.readObject();
        System.out.println(shapes);
    }
}

//可能异常OptionalDataException，表示序列化的协议被修改了
