package com.xqf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by XQF on 2016/12/7.
 */
public class DBHelper {

    Connection connection;
    String driverString="com.mysql.jdbc.Driver";
    String urlString="jdbc:mysql://localhost:3306/mydb?useSSL=false";
    String userName="root";
    String password="125880";
    private  DBHelper() {
        try {
            Class.forName(driverString);
            connection= DriverManager.getConnection(urlString,userName,password);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("驱动加载失败");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库获取连接失败！");
        }
    }

    public  static  DBHelper getDbHelper(){
        return new DBHelper();
    }
    public Connection getConnection(){
        return connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("数据库关闭失败！");
        }
    }
    public static void main(String[] args) {
        Connection connection=  DBHelper.getDbHelper().getConnection();
    }
}
