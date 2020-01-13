package com.deepoove.testpie.convert;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.deepoove.testpie.annotation.PieData;
import com.deepoove.testpie.junit5.PieExtension;
import com.deepoove.testpie.target.User;

@ExtendWith(PieExtension.class)
public class YamlConverterTest {

    @PieData(value = "/yaml/user.yml", converter = YamlConverter.class)
    private User user;

    @PieData(value = "/yaml/list_user_.yml", converter = YamlConverter.class)
    private List<User> userList;

    @Test
    public void testPieDataByYaml() {
        assertNotNull(user);
        assertEquals(user.getPhone(), 12309);

        assertNotNull(userList);
        assertEquals(userList.get(0).getPhone(), 12306);

    }

}
