package com.book.test;

import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.pojo.User;
import org.junit.Test;

public class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void saveUser() {
        System.out.println(userDao.saveUser(new User(null, "hongting", "123456", "hongting@qq.com")));
    }

    @Test
    public void queryUserByUsername() {
        User user = userDao.queryUserByUsername("admin");
        if (user == null) {
            System.out.println("用户名可用");
        } else {
            System.out.println("用户名已存在");
        }
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        User user = userDao.queryUserByUsernameAndPassword("admin", "admin");
        if (user == null) {
            System.out.println("用户名或密码错误");
        } else {
            System.out.println("登录成功");
        }

    }
}