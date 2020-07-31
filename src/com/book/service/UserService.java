package com.book.service;


import com.book.pojo.User;

public interface UserService {

    /**
     * 检查用户名是否可用
     *
     * @param [username]
     * @return boolean 返回true表示用户名已存在；返回false用户名不存在
     */
    boolean existUsername(String username);


    /**
     * 用户注册
     *
     * @param [user]
     * @return void
     */
    void register(User user);

    /**
     * 用户登录
     *
     * @param [user]
     * @return 返回null表示登录失败
     */
    User login(User user);     // 参数传什么？


}
