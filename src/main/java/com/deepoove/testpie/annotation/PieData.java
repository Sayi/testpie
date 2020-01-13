package com.deepoove.testpie.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.deepoove.testpie.convert.Converter;
import com.deepoove.testpie.convert.DefaultConverter;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PieData {

    String value() default "";

    Class<? extends Converter> converter() default DefaultConverter.class;

}
