package com.deepoove.testpie.junit5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.target.User;

@ExtendWith(PieExtension.class)
public class PieExtensionTest {

    @PieData(value = "/pie/user.json")
    private User user;

    @PieData("/pie/list_user_.json")
    private List<User> userList;

    @Test
    public void testPieData() {
        assertNotNull(user);
        assertEquals(user.getPhone(), 12306);

        assertNotNull(userList);
        assertEquals(userList.get(0).getPhone(), 12306);
    }

    @Test
    public void testPieDataFromParameter(@PieData("/pie/user.json") User user,
            @PieData("/pie/list_user_.json") List<User> userList) {
        assertNotNull(user);
        assertEquals(user.getPhone(), 12306);

        assertNotNull(userList);
        assertEquals(userList.get(0).getPhone(), 12306);

    }

}
