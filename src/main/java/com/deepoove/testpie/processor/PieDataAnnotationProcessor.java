package com.deepoove.testpie.processor;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.convert.Converter;
import com.deepoove.testpie.convert.ConverterFactory;
import com.deepoove.testpie.convert.TypeContext;

public class PieDataAnnotationProcessor implements AnnotationProcessor<PieData> {

    private ResourceLoader resourceLoader = new LocalResourceLoader();

    public static final AnnotationProcessor<PieData> processor = new PieDataAnnotationProcessor();

    @Override
    public Object process(PieData annotation, Parameter parameter, Class<?> clazz) {
        TypeContext typeContext = new TypeContext(clazz, parameter);
        Converter converter = ConverterFactory.resolveConverter(annotation.value(),
                annotation.converter());
        return converter.convert(resourceLoader.load(annotation.value(), clazz), typeContext);
    }

    @Override
    public Object process(PieData annotation, Field field, Object testInstance, Class<?> clazz) {
        TypeContext typeContext = new TypeContext(clazz, field);
        Converter converter = ConverterFactory.resolveConverter(annotation.value(),
                annotation.converter());
        return converter.convert(resourceLoader.load(annotation.value(), clazz), typeContext);
    }

}
