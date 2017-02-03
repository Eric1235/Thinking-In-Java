package reflecttest;

/**
 * Created by EricLi on 2016/11/29.
 * Email me : EricLi1235@gmail.com.
 */
public class GenericToyTest {
    public static void main(String[] args)throws Exception{
        Class<FancyToy> ftClass = FancyToy.class;
        FancyToy fancyToy = ftClass.newInstance();
        Class<? super FancyToy> up = ftClass.getSuperclass();
        Toy toy = (Toy)up.newInstance();
    }
}
