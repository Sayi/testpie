package com.deepoove.testpie.convert;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.platform.commons.util.Preconditions;
import org.junit.platform.commons.util.ReflectionUtils;

public class ConverterFactory {

    private static final Map<Class<? extends Converter>, Converter> cache = new ConcurrentHashMap<>();
    private static final Map<String, Class<? extends Converter>> cacheByExtension = new ConcurrentHashMap<>();

    static {
        cacheByExtension.put(".json", GsonJsonConverter.class);
        cacheByExtension.put(".yml", YamlConverter.class);
    }

    private ConverterFactory() {}

    public static Converter resolveConverter(String resource, Class<? extends Converter> clazz) {
        Class<? extends Converter> convertClass = clazz;
        if (DefaultConverter.class.equals(convertClass)) {
            convertClass = resolveConverterByResource(resource);
        }
        Preconditions.notNull(convertClass, "Converter for [" + resource + "] not found.");
        Converter converter = cache.get(convertClass);
        if (null == converter) {
            converter = ReflectionUtils.newInstance(convertClass);
            cache.put(convertClass, converter);
        }
        return converter;
    }

    public static Class<? extends Converter> resolveConverterByResource(String resource) {
        int indexOf = resource.lastIndexOf('.');
        return -1 != indexOf ? cacheByExtension.get(resource.substring(indexOf)) : null;
    }

}
