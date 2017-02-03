package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by EricLi on 2017/1/9.
 * Email me : EricLi1235@gmail.com.
 */
@Target(ElementType.METHOD)//在哪里可以用
@Retention(RetentionPolicy.RUNTIME)//运行级别，SOURCE,CLASS,RUNTIME三个运行级别
/**
 * 没有元素的注解为标记注解
 */
public @interface Test {
}
