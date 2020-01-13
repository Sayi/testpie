package com.deepoove.testpie.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.junit5.PieExtension;
import com.deepoove.testpie.target.Result;
import com.deepoove.testpie.target.User;

@ExtendWith(PieExtension.class)
public class JacksonJsonConverterTest {
    @PieData(value = "/pie/user.json", converter = JacksonJsonConverter.class)
    private User user;

    @PieData(value = "/pie/list_user_.json", converter = JacksonJsonConverter.class)
    private List<User> userList;

    @PieData(value = "/pie/generic_user_.json", converter = JacksonJsonConverter.class)
    private Result<User> resultUser;

    @PieData(value = "/pie/generic_list_user_.json", converter = JacksonJsonConverter.class)
    private Result<List<User>> resultUserList;

    @PieData(value = "/pie/list_string_.json", converter = JacksonJsonConverter.class)
    private List<String> list;

    @PieData(value = "/pie/map_string_user_.json", converter = JacksonJsonConverter.class)
    private Map<String, User> map;

    @Test
    public void testPieDataByJackson() {
        System.out.println(user);
        assertNotNull(user);
        assertEquals(user.getPhone(), 12306);

        System.out.println(userList);
        assertNotNull(userList);
        assertEquals(userList.get(0).getPhone(), 12306);

        System.out.println(resultUser);
        assertNotNull(resultUser);
        assertEquals(resultUser.getData().getPhone(), 12306);

        System.out.println(resultUserList);
        assertNotNull(resultUserList);
        assertEquals(resultUserList.getData().get(0).getPhone(), 12306);

        assertNotNull(list);
        assertNotNull(map);
        assertEquals(map.get("12306").getPhone(), 12306);
    }

}
