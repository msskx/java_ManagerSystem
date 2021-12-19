package com.msskx.java.util;



import java.sql.*;

/**
 * 数据库连接工具类
 * 对数据库进行连接和关闭操作
 */
public class DButil {
////本地数据库
//    public static final String USER ="root";
//    public  static final String PASSWORD="0213";
//    public  static final String DBURL="jdbc:mysql://localhost:3306/employee_manage?serverTimezone=UTC";
//    public  static final String DRIVER="com.mysql.cj.jdbc.Driver";

    /**
     * 静态代码块
     * 类加载的时候加载驱动
     */
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(DBURL,USER,PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接
     * @param connection
     */
    public static void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closePS(PreparedStatement preparedStatement){
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void closeRS(ResultSet resultSet){
        if(null!=resultSet){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
