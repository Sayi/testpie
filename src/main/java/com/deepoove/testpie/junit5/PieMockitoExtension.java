package com.deepoove.testpie.junit5;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.util.ReflectionUtils;
import org.mockito.internal.util.MockUtil;

import com.deepoove.testpie.annotation.MockProvider;
import com.deepoove.testpie.exception.PieException;

/**
 * @author Sayi
 * @version
 */
public class PieMockitoExtension
        implements BeforeEachCallback, AfterEachCallback, BeforeTestExecutionCallback {

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        // mock primary
        List<Object> testInstances = context.getRequiredTestInstances().getAllInstances();
        testInstances.forEach(testClassInstance -> Pie.initMockPrimary(testClassInstance,
                context.getRequiredTestClass()));
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        Class<?> testClass = context.getRequiredTestClass();
        Object testInstance = context.getRequiredTestInstance();
        if (testClass.isAnnotationPresent(MockProvider.class)) {
            MockProvider annotation = testClass.getAnnotation(MockProvider.class);
            Class<?> providerClass = annotation.value();
            Object provider = newProviderWithMockField(providerClass, testClass, testInstance);
            Method testMethod = context.getRequiredTestMethod();
            Method declaredMethod = null;
            try {
                declaredMethod = providerClass.getDeclaredMethod("mock_" + testMethod.getName());
            } catch (NoSuchMethodException e) {} catch (Exception e) {
                throw new PieException("Retrive method mock_" + testMethod.getName()
                        + "form MockProvider class " + providerClass.getSimpleName() + " error", e);
            }
            if (null != declaredMethod) {
                declaredMethod.invoke(provider);
            }
        }

    }

    private Object newProviderWithMockField(Class<?> providerClass, Class<?> testClass,
            Object testInstance) {
        Object instance = ReflectionUtils.newInstance(providerClass);
        Field[] declaredFields = providerClass.getDeclaredFields();
        for (Field field : declaredFields) {
            try {
                Field sourceMockField = testClass.getDeclaredField(field.getName());
                if (null == sourceMockField) continue;
                if (!field.getType().equals(sourceMockField.getType())) continue;
                sourceMockField.setAccessible(true);
                Object value = sourceMockField.get(testInstance);
                if (!MockUtil.isMock(value)) continue;
                field.setAccessible(true);
                field.set(instance, value);
            } catch (NoSuchFieldException e) {} catch (Exception e) {
                throw new PieException("copy mock field " + field.getName()
                        + "to MockProvider class " + providerClass.getSimpleName() + " error", e);
            }
        }
        return instance;
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        // finish
    }

}
