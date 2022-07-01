package com.alexbonetskiy.restaurantvoting.util;

import com.alexbonetskiy.restaurantvoting.dto.UserTo;
import com.alexbonetskiy.restaurantvoting.model.Role;
import com.alexbonetskiy.restaurantvoting.model.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

    public static User prepareToSave(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}