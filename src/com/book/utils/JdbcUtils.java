package com.book.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {

    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<>();    // 创建一个ThreadLocal对象

    static {
        try {

            Properties properties = new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);

            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
            DruidPooledConnection connection = dataSource.getConnection();

            System.out.println(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 获取连接
    public static Connection getConnection() {
        Connection connection = conns.get();

        if(connection == null) {     // 若这个连接是空的，证明之前没有被使用过。
            try {
                connection = dataSource.getConnection();   // 获取这个连接
                conns.set(connection);                     // 保存到ThreadLocal对象中
                connection.setAutoCommit(false);           // 设置事务为手动提交
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }


    // 提交事务并关闭连接
    public static void commitAndClose(){
        Connection connection = conns.get();

        if(connection != null){
            try {
                connection.commit();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        conns.remove();

    }


    // 回滚事务并关闭连接
    public static void rollbackAndClose(){
        Connection connection = conns.get();

        if(connection != null){
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        conns.remove();
    }



//    // 关闭连接
//    public static void closeConnection(Connection connection) {
//
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//
//    }

}
