/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 周志军
 */
public class dbOption {
    //查询所有记录
    public static List<book> query() throws SQLException {
        List<book> books = new ArrayList<book>();
        Connection conn = DbUtil.getConnection();
       
        try {
            //开启事务
            conn.setAutoCommit(false);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM library");

        book g = null;
       
        while(rs.next()){
           
            g = new book();
            g.setId(rs.getInt("book_id"));
            g.setTitle(rs.getString("book_title"));
            g.setPrice(rs.getInt("book_price"));
            g.setAuthor(rs.getString("book_author"));
            g.setSubmission_date(rs.getDate("submission_date"));
            books.add(g);
        }
            // 提交事务:
            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
        }
         return books;    
    }
    
    //分页查询
    public static List<book> queryPage(int currPage,int pageSize) throws SQLException {
         List<book> bookslist = new ArrayList<book>();
         bookslist= query();
        
//4     // 从第几条数据开始
     int firstIndex = (currPage - 1) * pageSize;
//6     // 到第几条数据结束
     int lastIndex = currPage * pageSize;   
      if(bookslist.size()<lastIndex){
        List<book> subList =new ArrayList<book>(bookslist.subList(firstIndex, bookslist.size()));
        //bookslist.subList(firstIndex, lastIndex); 
     return subList;
      }
     List<book> subList =new ArrayList<book>(bookslist.subList(firstIndex, lastIndex));
             //bookslist.subList(firstIndex, lastIndex); 
     return subList;
    }
    //添加记录
       public static void addbook(book b) throws SQLException {
        //获取连接
        Connection conn = DbUtil.getConnection();
        try {
            // 开启事务
            conn.setAutoCommit(false);
            //sql
            String sql = "insert into library (book_title, book_price, book_author, submission_date) values( ?, ?, ?, ?)";
            //预编译
            PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

            //传参
       
            ptmt.setString(1, b.getTitle());
            ptmt.setInt(2, b.getPrice());
            ptmt.setString(3, b.getAuthor());
            ptmt.setDate(4, new java.sql.Date(new java.util.Date().getTime()));

            //执行
            ptmt.execute();
            // 提交事务:
            conn.commit();
        }
        catch (SQLException e){
            //数据回滚
            conn.rollback();
        }
    }

}
