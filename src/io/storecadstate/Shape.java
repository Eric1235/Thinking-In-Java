package io.storecadstate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */
abstract class Shape implements Serializable{
    public static final int RED = 1, BLUE = 2, GREEN = 3;
    private int xPos, yPos, dimension;
    private static Random rand = new Random(47);
    private static int counter = 0;
    private static int color = RED;
    public abstract void setColor(int newColor);
    public abstract int getColor();

    public Shape(int xPos, int yPos, int dimension) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return getClass() + "  color" + "[" + getColor() + "] xPos[" + xPos +"] yPos" + yPos +
                "] dim[" + dimension + "]" + "\n";
    }

    public static void serializeStaticState(ObjectOutputStream out)throws IOException{
        out.writeInt(color);
    }

    public static void deserializeStaticState(ObjectInputStream in) throws IOException{
        color = in.readInt();
    }

    public static Shape randomFactory(){
        int xVal = rand.nextInt(100);
        int yVal = rand.nextInt(100);
        int dim = rand.nextInt(100);
        switch (counter++ % 3){
            case 0:
                return new Circle(xVal, yVal, dim);
            case 1:
                return new Square(xVal, yVal, dim);
            case 2:
                return new Line(xVal, yVal, dim);
            default:
        }
        return null;
    }

    static class Circle extends Shape{
        private static int color = RED;

        public Circle(int xVal, int yVal, int dim){
            super(xVal, yVal, dim);
        }
        @Override
        public void setColor(int newColor) {
            color = newColor;
        }

        @Override
        public int getColor() {
            return color;
        }
    }

    static class Square extends Shape{
        private static int color;

        public Square(int xVal, int yVal, int dim){
            super(xVal, yVal, dim);
            color = RED;
        }

        @Override
        public void setColor(int newColor) {
            color = newColor;
        }

        @Override
        public int getColor() {
            return color;
        }
    }

    static class Line extends Shape{

        private static int color = RED;

        public static void serializeStaticState(ObjectOutputStream out)throws IOException{
            out.writeInt(color);
        }

        public static void deserializeStaticState(ObjectInputStream in) throws IOException{
            color = in.readInt();
        }

        public Line(int xVal, int yVal, int dim){
            super(xVal, yVal, dim);
        }
        @Override
        public void setColor(int newColor) {
            color = newColor;
        }

        @Override
        public int getColor() {
            return color;
        }
    }
}
