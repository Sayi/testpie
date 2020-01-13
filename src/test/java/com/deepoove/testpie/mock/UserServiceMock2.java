package com.deepoove.testpie.mock;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;

import java.util.List;
import java.util.function.Supplier;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.junit5.Pie;
import com.deepoove.testpie.target.User;
import com.deepoove.testpie.target.UserService;

public class UserServiceMock2 implements Supplier<UserService> {

    @PieData("/pie/user.json")
    private User user;
    @PieData("/pie/list_user_.json")
    private List<User> userList;

    public UserServiceMock2() {
        Pie.initAnnotations(this);
    }

    @Override
    public UserService get() {
        UserService userService = Mockito.mock(UserService.class);
        given(userService.findAll()).willReturn(userList);
        given(userService.find(anyLong())).willReturn(user);
        doAnswer(new Answer<Void>() {

            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                return null;
            }
        }).when(userService).add(user);
        given(userService.delete(1590000)).willReturn(false);
        given(userService.delete(1581111)).willReturn(true);
        return userService;
    }

}
