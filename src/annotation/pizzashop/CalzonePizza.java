package annotation.pizzashop;

/**
 * Created by EricLi on 2017/1/11.
 * Email me : EricLi1235@gmail.com.
 */
@Factory(type = Meal.class, id = "Calzone")
public class CalzonePizza implements Meal {
    @Override
    public float getPrice() {
        return 5f;
    }
}
