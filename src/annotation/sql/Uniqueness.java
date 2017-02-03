package annotation.sql;

/**
 * Created by EricLi on 2017/1/10.
 * Email me : EricLi1235@gmail.com.
 */
public @interface Uniqueness {
    Constraints constraints() default @Constraints(unique = true);
}
