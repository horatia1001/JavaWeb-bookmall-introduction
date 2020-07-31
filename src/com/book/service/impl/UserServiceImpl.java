package com.book.service.impl;

import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.pojo.User;
import com.book.service.UserService;

public class UserServiceImpl implements UserService {

    UserDao userDao = new UserDaoImpl();


    @Override
    public boolean existUsername(String username) {
        if ( userDao.queryUserByUsername(username) == null) {
            System.out.println("用户名可用");
            return false;
        } else {
            System.out.println("用户名已存在");
            return true;
        }
    }

    @Override
    public void register(User user) {
        int saveUserFlag = userDao.saveUser(user);
        if ((saveUserFlag > 0)) {
            System.out.println("注册成功");
        } else {
            System.out.println("注册失败");
        }
    }

    @Override
    public User login(User user) {
        User loginUser = userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (loginUser == null) {
            System.out.println("用户名或密码错误");
        } else {
            System.out.println("登录成功");
        }

        return loginUser;

    }

}
