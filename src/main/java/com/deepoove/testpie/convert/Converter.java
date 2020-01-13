package com.deepoove.testpie.convert;

@FunctionalInterface
public interface Converter {
    Object convert(String source, TypeContext context);
}
