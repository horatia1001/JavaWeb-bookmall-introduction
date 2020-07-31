package com.book.dao;

import com.book.dao.impl.BaseDao;
import com.book.pojo.User;

/**
 * 用户操作时需要关联到哪些数据库操作
 * 登录：用户名验证，密码验证
 * 注册：用户名添加，密码添加
 *
 * @param
 * @author hongt
 * @date 2020/7/20 18:43
 * @return
 */
public interface UserDao {

    //   * 接口里的方法默认是public，由于重写不能降低可访问性，因此类实现的时候相应方法都要声明为public

    /**
     * 保存用户信息
     *
     * @param [com.book.pojo.User]
     * @return int 如果为-1就是保存失败
     */
    int saveUser(User user);

    /**
     * 根据用户名查询用户信息
     *
     * @param [username]
     * @return com.book.pojo.User
     */
    User queryUserByUsername(String username);

    /**
     * @param [username]
     * @param [password]
     * @return com.book.pojo.User
     */
    User queryUserByUsernameAndPassword(String username, String password);

}
