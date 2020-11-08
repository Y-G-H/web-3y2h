package cn.y3h2.blog.web.controller.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WebResponseHandler {
    String error() default "";

    boolean validateArgs() default false;

    boolean needTrack() default false;
}
