package com.deepoove.testpie.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.deepoove.testpie.annotation.MockPrimary;
import com.deepoove.testpie.annotation.MockProvider;
import com.deepoove.testpie.mock.UserServiceMock;
import com.deepoove.testpie.target.User;
import com.deepoove.testpie.target.UserService;

@ExtendWith({ MockitoExtension.class, PieMockitoExtension.class })
@MockProvider(MockProviderTestClassMock.class)
public class MockProviderTest {

    // annotation
    @Mock(lenient = true)
    @MockPrimary(UserServiceMock.class)
    private UserService userService;

    @Test
    public void testPrimary() {
        // use primary mock
        User findUser = userService.find(12306);
        Assertions.assertEquals(findUser.getName(), "Sayi");
    }

    @Test
    public void testProvider() {
        // user provider mock
        User findUser = userService.find(12306);
        Assertions.assertEquals(findUser.getName(), "Van");
    }

}
