/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;
import java.sql.*;
/**
 *
 * @author 周志军
 */
public class DbUtil {
     public static final String URL = "jdbc:mysql://localhost:3306/RUNOOB?serverTimezone=UTC";
    public static final String USER = "root";
    public static final String PASSWORD = "a123";
    private static Connection conn = null;
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2. 获得数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.print("连接数据库成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        return conn;
    }
}
