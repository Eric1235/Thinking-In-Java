package thread.cooperation;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by EricLi on 2017/2/18.
 * Email me : EricLi1235@gmail.com.
 */
public class Course {
    private static Random rand = new Random(47);
    public static List<Course> values(){
        java.util.List<Course> courses = new ArrayList<>();
        int n = rand.nextInt(5) + 1;
        for (int i = 0 ; i < n ; i ++){
            Course course = new Course();
            courses.add(course);
        }
        return courses;
    }

    public Food randomSelection(){
        int n = rand.nextInt(5);
        return new Food(n);
    }
}
