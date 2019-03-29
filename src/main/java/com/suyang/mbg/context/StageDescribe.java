package com.suyang.mbg.context;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface StageDescribe {
    String fxml();

    String css() default "";

    String title() default "";

    double width() default -1;

    double height() default -1;

    double minWidth() default -1;

    double minHeight() default -1;

    double left() default -1;

    double top() default -1;

    boolean resizable() default true;
}
