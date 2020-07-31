package com.book.test;

import com.book.pojo.User;
import com.book.service.UserService;
import com.book.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {


    UserService userService = new UserServiceImpl();

    @Test
    public void existUsername() {
        userService.existUsername("admin456");
    }

    @Test
    public void register() {
        userService.register(new User(null, "abc", "1234", null));
    }

    @Test
    public void login() {
        System.out.println(userService.login(new User(null, "hongting", "123456", null)));
    }

}