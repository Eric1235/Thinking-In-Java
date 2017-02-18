package thread.cooperation;

/**
 * Created by EricLi on 2017/2/18.
 * Email me : EricLi1235@gmail.com.
 */
public class Food {

    private static final String FOOD1 = "SOUP";
    private static final String FOOD2 = "SPRING_ROLLS";
    private static final String FOOD3 = "BURRITO";
    private static final String FOOD4 = "VINDALOO";
    private static final String FOOD5 = "FRUIT";

    private String food;

    public Food(int n) {
        switch (n){
            case 0:
                food = FOOD1;
                break;
            case 1:
                food = FOOD2;
                break;
            case 2:
                food = FOOD3;
                break;
            case 3:
                food = FOOD4;
                break;
            case 4:
                food = FOOD5;
                break;
            default:
                food = FOOD1;
                break;
        }
    }



    @Override
    public String toString() {
        return food;
    }
}
