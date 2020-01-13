package com.deepoove.testpie.convert;

public class DefaultConverter implements Converter {

    @Override
    public Object convert(String source, TypeContext context) {
        // no-op
        return source;
    }

}
