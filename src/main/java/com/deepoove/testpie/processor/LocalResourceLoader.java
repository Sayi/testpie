package com.deepoove.testpie.processor;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.junit.platform.commons.util.Preconditions;

import com.deepoove.testpie.exception.PieException;

public class LocalResourceLoader implements ResourceLoader {

    @Override
    public String load(String resource, Class<?> loadClass) {
        try (InputStream inputStream = Preconditions.notNull(
                loadClass.getResourceAsStream(resource),
                () -> "Classpath resource [" + resource + "] does not exist")) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PieException("Read resource [" + resource + "] content fail", e);
        }
    }

}
