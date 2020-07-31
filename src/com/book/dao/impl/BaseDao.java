package com.book.dao.impl;

import com.book.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @param
 * @author hongt
 * @date 2020/7/20 17:56
 * @return
 */
public abstract class BaseDao {

    // 需要queryRunner
    QueryRunner queryRunner = new QueryRunner();


    /**
     * 增、删、改操作，修改会返回影响的行数
     *
     * @param sql
     * @param args
     * @return
     */
    public int update(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);  // 可以捕获，且一定要抛出
        }
//        return -1;

    }

    /**
     * 针对一条记录的查询操作
     *
     * @param type 针对查询的JavaBean类
     * @param sql  查询语句
     * @return 返回查询结果的对象
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();

        try {
            return queryRunner.query(connection, sql, new BeanHandler<>(type), args);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
//        return null;
    }

    /**
     * 针对多条记录的查询操作
     *
     * @param type 针对查询的JavaBean类
     * @param sql  查询语句
     * @return 返回查询结果的对象
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
//        return null;
    }


    /**
     * @param sql
     * @param args
     */
    public Object queryForSingleValue(String sql,Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(),args);      // 这里查询的时候没把args参数写上
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

//        return null;
    }
}
