package genericity.erase_t;

/**
 * Created by EricLi on 2016/12/10.
 * Email me : EricLi1235@gmail.com.
 */
public class Widget{
    public static class Factory implements FactoryI<Widget>{
        @Override
        public Widget create() {
            return new Widget();
        }
    }
}