package com.deepoove.testpie.junit5;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.target.User;

public class PieTest {

    @PieData(value = "/pie/user.json")
    private User user;

    @PieData("/pie/list_user_.json")
    private List<User> userList;

    public PieTest() {
        // need initAnnotations
        Pie.initAnnotations(this);
    }

    @Test
    public void testPieData() {
        assertNotNull(user);
        assertNotNull(userList);
    }

}
