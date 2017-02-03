package reflecttest;

/**
 * Created by EricLi on 2016/11/29.
 * Email me : EricLi1235@gmail.com.
 */
public class ToyTest {



    static void printInfo(Class cc){
        System.out.println("Class name: " + cc.getName() + " is interface? [" + cc.isInterface() + "]");
        System.out.println("Simple name: " + cc.getSimpleName());
        System.out.println(" Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args){
        Class c = null;
        try{
            c = Class.forName("Reflecttest.Reflecttest.FancyToy");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        printInfo(c);
        for(Class face:c.getInterfaces()){
            printInfo(face);
        }
        Class up = c.getSuperclass();//Toy
        Object obj = null;
        try{
            obj = up.newInstance();//Toy，只能使用Toy的方法
        }catch (InstantiationException e){
            System.out.println("Cannot instantiate");
        } catch (IllegalAccessException e){
            System.out.println("Cannot access");
        }
        printInfo(obj.getClass());
    }

//    Class<? extends Number> clazz = int.class;
}
