package cn.y3h2.blog.web.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
/**
* @ClassName WebResponseHandler
* @Author kongming
* @Date 2020/11/8 5:19 下午
* @Description 统一异常处理注解
*/
public @interface WebResponseHandler {
    String error() default "";

    boolean validateArgs() default false;

    boolean needTrack() default false;
}
