package com.deepoove.testpie.convert;

import com.google.gson.Gson;

public class GsonJsonConverter implements Converter {

    @Override
    public Object convert(String source, TypeContext context) {
        Gson gson = new Gson();
        return gson.fromJson(source, context.getType());
    }

}
