package com.deepoove.testpie.junit5;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import java.util.List;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.junit5.Pie;
import com.deepoove.testpie.target.User;
import com.deepoove.testpie.target.UserService;

public class MockProviderTestClassMock {

    @PieData("/pie/list_user_.json")
    private List<User> userList;

    private UserService userService;

    public MockProviderTestClassMock() {
        Pie.initAnnotations(this);
    }

    public void mock_testProvider() {
        given(userService.find(anyLong())).willReturn(userList.get(1));
    }

}
