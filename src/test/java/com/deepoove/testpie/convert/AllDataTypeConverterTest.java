package com.deepoove.testpie.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.junit5.Pie;
import com.deepoove.testpie.target.Result;
import com.deepoove.testpie.target.User;

public class AllDataTypeConverterTest {

    @PieData(value = "/pie/user.json")
    private User user;

    @PieData("/pie/list_user_.json")
    private List<User> userList;

    @PieData("/pie/generic_user_.json")
    private Result<User> resultUser;

    @PieData("/pie/generic_list_user_.json")
    private Result<List<User>> resultUserList;

    @PieData("/pie/list_string_.json")
    private List<String> list;

    @PieData("/pie/map_string_user_.json")
    private Map<String, User> map;

    @PieData(value = "/yaml/user.yml")
    private User userFormYml;

    public AllDataTypeConverterTest() {
        // Pie.initAnnotations or @ExtendWith(PieExtension.class)
        Pie.initAnnotations(this);
    }

    @Test
    public void testPieDataByAllDataType() {
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

        System.out.println(userFormYml);
        assertNotNull(userFormYml);
        assertEquals(userFormYml.getPhone(), 12309);
    }

}
