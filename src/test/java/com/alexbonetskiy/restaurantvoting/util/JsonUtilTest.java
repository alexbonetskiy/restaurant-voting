package com.alexbonetskiy.restaurantvoting.util;

import com.alexbonetskiy.restaurantvoting.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.alexbonetskiy.restaurantvoting.web.user.UserTestData.*;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class JsonUtilTest {

    @Test
    void readWriteValue() {
        String json = JsonUtil.writeValue(admin);
        System.out.println(json);
        User user = JsonUtil.readValue(json, User.class);
        USER_MATCHER.assertMatch(user, admin);
    }

    @Test
    void readWriteValues() {
        List<User> users = List.of(user, admin);
        String json = JsonUtil.writeValue(users);
        System.out.println(json);
        List<User> actual = JsonUtil.readValues(json, User.class);
        USER_MATCHER.assertMatch(actual, users);
    }

    @Test
    void writeOnlyAccess() {
        String json = JsonUtil.writeValue(user);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = jsonWithPassword(user, "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}