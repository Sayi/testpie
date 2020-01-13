package com.deepoove.testpie.convert;

import com.deepoove.testpie.exception.PieException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlConverter implements Converter {

    static final ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    @Override
    public Object convert(String source, TypeContext context) {

        JavaType javaType = TypeFactory.defaultInstance().constructType(context.getType());
        try {
            return objectMapper.readValue(source, javaType);
        } catch (JsonProcessingException e) {
            throw new PieException("convert source error", e);
        }
    }

}
