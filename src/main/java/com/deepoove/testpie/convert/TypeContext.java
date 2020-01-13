package com.deepoove.testpie.convert;

import java.lang.reflect.Field;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;

public class TypeContext {

    private Class<?> testClass;
    private Type type;
    private Parameter parameter;
    private Field field;

    public TypeContext(Class<?> testClass, Parameter parameter) {
        this.testClass = testClass;
        this.type = parameter.getParameterizedType();
        this.parameter = parameter;
    }

    public TypeContext(Class<?> testClass, Field field) {
        this.testClass = testClass;
        this.type = field.getGenericType();
        this.field = field;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public void setTestClass(Class<?> testClass) {
        this.testClass = testClass;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Parameter getParameter() {
        return parameter;
    }

    public void setParameter(Parameter parameter) {
        this.parameter = parameter;
    }

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

}
