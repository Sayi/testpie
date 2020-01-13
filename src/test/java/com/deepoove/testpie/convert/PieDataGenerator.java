package com.deepoove.testpie.convert;

import java.util.Arrays;
import java.util.List;

import com.deepoove.testpie.target.Result;
import com.deepoove.testpie.target.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class PieDataGenerator {

    public static void main(String[] args) throws Exception {
        User user = new User();
        user.setPhone(12306);
        user.setName("Sayi");
        user.setAge(18);
        Result<List<User>> obj = new Result<>();
        obj.setMsg("200");
        obj.setData(Arrays.asList(user, user));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(obj));

        System.out.println("********");

        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        System.out.println(objectMapper.writeValueAsString(Arrays.asList(user, user)));
    }

}
