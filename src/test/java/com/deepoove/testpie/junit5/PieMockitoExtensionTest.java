package com.deepoove.testpie.junit5;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deepoove.testpie.annotation.MockPrimary;
import com.deepoove.testpie.mock.UserServiceMock;
import com.deepoove.testpie.target.User;
import com.deepoove.testpie.target.UserService;

@ExtendWith({ MockitoExtension.class, PieMockitoExtension.class })
public class PieMockitoExtensionTest {

    // annotation
    @Mock(lenient = true)
    @MockPrimary(UserServiceMock.class)
    private UserService userService;

    @Test
    public void testPrimary() {
        User findUser = userService.find(12306);
        Assertions.assertEquals(findUser.getName(), "Sayi");
        assertFalse(userService.delete(1590000));
        assertTrue(userService.delete(1581111));
    }

}
