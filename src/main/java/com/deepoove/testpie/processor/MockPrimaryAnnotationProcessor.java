package com.deepoove.testpie.processor;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.util.function.Consumer;

import org.junit.platform.commons.util.ReflectionUtils;

import com.deepoove.testpie.annotation.MockPrimary;
import com.deepoove.testpie.exception.PieException;

public class MockPrimaryAnnotationProcessor implements AnnotationProcessor<MockPrimary> {

    public static final AnnotationProcessor<MockPrimary> processor = new MockPrimaryAnnotationProcessor();

    @Override
    public Object process(MockPrimary annotation, Parameter parameter, Class<?> clazz) {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Object process(MockPrimary annotation, Field field, Object testInstance,
            Class<?> clazz) {
        Class<? extends Consumer<?>> consumerClass = annotation.value();
        Consumer consumer = ReflectionUtils.newInstance(consumerClass);
        Object object = null;
        try {
            object = ReflectionUtils.tryToReadFieldValue(field, testInstance).get();
            consumer.accept(object);
        } catch (Exception e) {
            throw new PieException("Process MockPrimay error", e);
        }
        return object;
    }

}
