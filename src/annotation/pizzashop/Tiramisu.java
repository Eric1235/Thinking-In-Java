package annotation.pizzashop;

/**
 * Created by EricLi on 2017/1/11.
 * Email me : EricLi1235@gmail.com.
 */
@Factory(type = Meal.class, id = "Tiramisu")
public class Tiramisu implements Meal {
    @Override
    public float getPrice() {
        return 0;
    }
}
