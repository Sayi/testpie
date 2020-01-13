package com.deepoove.testpie.junit5;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.mockito.Mock;

import com.deepoove.testpie.annotation.MockPrimary;
import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.exception.PieException;
import com.deepoove.testpie.processor.MockPrimaryAnnotationProcessor;
import com.deepoove.testpie.processor.PieDataAnnotationProcessor;

/**
 * @author Sayi
 * @version
 */
public class Pie {

    /**
     * init some annotations, pay attention to life cycle
     * 
     * @param instance
     */
    public static void initAnnotations(Object instance) {
        initPieData(instance, instance.getClass());
        // other annotations init
        try {
            Class.forName("org.mockito.Mock");
            initMockPrimary(instance, instance.getClass());
        } catch (ClassNotFoundException e) {
            // no-op
        }
        
    }

    public static void initPieData(Object testInstance, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(PieData.class)) {
                Object mock = PieDataAnnotationProcessor.processor
                        .process(field.getAnnotation(PieData.class), field, testInstance, clazz);
                if (mock != null) {
                    try {
                        field.setAccessible(true);
                        field.set(testInstance, mock);
                    } catch (Exception e) {
                        throw new PieException("Problems setting field " + field.getName()
                                + " annotated with " + PieData.class.getSimpleName(), e);
                    }
                }
            }
        }
    }

    public static void initMockPrimary(Object testInstance, Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Arrays.stream(fields)
                .filter(field -> (field.isAnnotationPresent(Mock.class)
                        && field.isAnnotationPresent(MockPrimary.class)))
                .forEach(field -> MockPrimaryAnnotationProcessor.processor.process(
                        field.getAnnotation(MockPrimary.class), field, testInstance, clazz));
    }

}
