package com.deepoove.testpie.junit5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.deepoove.testpie.annotation.MockPrimary;
import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.mock.UserServiceMock;
import com.deepoove.testpie.target.User;
import com.deepoove.testpie.target.UserService;

public class PieMockitoTest {

    @PieData(value = "/pie/user.json")
    private User user;

    @PieData("/pie/list_user_.json")
    private List<User> userList;

    @Mock(lenient = true)
    @MockPrimary(UserServiceMock.class)
    private UserService userService;

    // another supplier style
    // private UserService userService1 = new UserServiceMock2().get();

    public PieMockitoTest() {
        // first mockito
        MockitoAnnotations.initMocks(this);
        // the testpie
        Pie.initAnnotations(this);
    }

    @Test
    public void testPieData() {
        assertNotNull(user);
        assertNotNull(userList);
        assertFalse(userService.delete(1590000));
        assertTrue(userService.delete(1581111));
    }

}
