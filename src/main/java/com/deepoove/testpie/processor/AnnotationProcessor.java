package com.deepoove.testpie.processor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

public interface AnnotationProcessor<A extends Annotation> {

    public Object process(A annotation, Parameter parameter, Class<?> clazz);

    public Object process(A annotation, Field parameter, Object testInstance, Class<?> clazz);

}
