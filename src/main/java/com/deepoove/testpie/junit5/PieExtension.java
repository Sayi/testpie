package com.deepoove.testpie.junit5;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.processor.PieDataAnnotationProcessor;

/**
 * auto load data by @PieData
 * 
 * @author Sayi
 * @version
 */
public class PieExtension implements TestInstancePostProcessor, ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
            ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(PieData.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
            ExtensionContext extensionContext) throws ParameterResolutionException {
        return PieDataAnnotationProcessor.processor.process(
                parameterContext.findAnnotation(PieData.class).get(),
                parameterContext.getParameter(), extensionContext.getRequiredTestClass());
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext extensionContext)
            throws Exception {
        Pie.initPieData(testInstance, testInstance.getClass());
    }

}
