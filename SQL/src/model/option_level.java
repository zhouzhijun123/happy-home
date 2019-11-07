package model;

import model.vote_records;
import model.DbUtil;
import java.util.Date;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class option_level {
    //插入数据
    //增加
    public static void addvotes(vote_records g) throws SQLException {
        //获取连接
        Connection conn = DbUtil.getConnection();
        //sql
        String sql = "insert into vote_records_test (id, user_id, vote_num, status, create_time) values(?, ?, ?, ?, ?)";
        //预编译
        PreparedStatement ptmt = conn.prepareStatement(sql); //预编译SQL，减少sql执行

        //传参
        ptmt.setInt(1, g.getId());
        ptmt.setString(2, g.getUser_id());
        ptmt.setInt(3, g.getVote_num());
        ptmt.setInt(4, g.getStatus());
        ptmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));

        //执行
        ptmt.execute();
        JOptionPane.showMessageDialog(null, "成功添加记录");
    }

    public static void delvotes(int id) throws SQLException {
        //获取连接
        Connection conn = DbUtil.getConnection();
        //sql, 每行加空格
        String sql = "delete from vote_records_test where id=?";
        //预编译SQL，减少sql执行
        PreparedStatement ptmt = conn.prepareStatement(sql);

        //传参
        ptmt.setInt(1, id);

        //执行
        ptmt.execute();
        JOptionPane.showMessageDialog(null, "成功删除id为"+id+"的记录");

    }
    //查
    public static vote_records query(int id) throws SQLException {
        vote_records g=null;
        exitData(id);
        Connection conn = DbUtil.getConnection();
        Statement stmt = conn.createStatement();
        String sql=("SELECT * FROM vote_records_test WHERE id=?");
        PreparedStatement ptmt = conn.prepareStatement(sql);
        ptmt.setInt(1, id);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next()) {
            g = new vote_records();
            g.setUser_id(rs.getString("user_id"));
            g.setVote_num(rs.getInt("vote_num"));
            g.setId(rs.getInt("id"));
            g.setStatus(rs.getInt("status"));
            g.setCreate_time(rs.getDate("create_time"));
        }
        JOptionPane.showMessageDialog(null, "查询成功");
        return g;
    }
    //改
    public static void reset(int id,int new_num) throws SQLException {
        vote_records g = null;
        //获取连接
        Connection conn = DbUtil.getConnection();
        //sql, 每行加空格
        String sql = "update vote_records_test set vote_num=?  where id=?";
        //预编译SQL，减少sql执行
        PreparedStatement ptmt = conn.prepareStatement(sql);
        //传参
        ptmt.setInt(1, new_num);
        ptmt.setInt(2, id);
        //执行
        int rs = ptmt.executeUpdate();
        if(rs>0) {
            JOptionPane.showMessageDialog(null, "成功修改id为"+id+"的记录");

        }else {
            JOptionPane.showMessageDialog(null, "修改id为"+id+"的记录失败");

        }
    }
    //建表
    public static void createTable( String tabName,String[] tab_fields) throws SQLException{
        //获取连接
        Connection conn = DbUtil.getConnection();
        try {
            String sql = "create table "+tabName+"(id int auto_increment primary key not null";
            if(tab_fields!=null&&tab_fields.length>0){
                sql+=",";
                int length = tab_fields.length;
                for(int i =0 ;i<length;i++){
                    //添加字段
                    sql+=tab_fields[i].trim()+" varchar(50)";
                    //防止最后一个,
                    if(i<length-1){
                        sql+=",";
                    }
                }
            }
            //拼凑完 建表语句 设置默认字符集
            sql+=")DEFAULT CHARSET=utf8;";
            System.out.println("建表语句是："+sql);
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.executeUpdate(sql);
            ptmt.close();
            conn.close();  //关闭数据库连接
            JOptionPane.showMessageDialog(null, "成功建立名为"+tabName+"的表");
        } catch (SQLException e) {
            System.out.println("建表失败" + e.getMessage());
        }
    }
    //数据是否存在
    public static boolean exitData(int id){
        boolean flag = false;
        Connection conn = DbUtil.getConnection();
        try {
            String sql = "SELECT * FROM vote_records_test WHERE id=?";
            //预处理SQL 防止注入
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //执行
            flag = ptmt.execute();
            //关闭流
            ptmt.close();
        } catch (SQLException e) {
            System.out.println("删除数据失败,表不存在" + e.getMessage());
        }
        return flag;
    }
    //表是否存在
    public static boolean exitTable(String tabName){
        boolean flag = false;
        Connection conn = DbUtil.getConnection();
        try {
            String sql = "select * from "+tabName+";";
            //预处理SQL 防止注入
            PreparedStatement ptmt = conn.prepareStatement(sql);
            //执行
            flag = ptmt.execute();
            //关闭流
            ptmt.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "数据表不存在");
            System.out.println("删除数据失败,表不存在" + e.getMessage());
        }
        return flag;
    }
    //删除表
    public static void deleteTable(String tabName) throws SQLException {
        //获取连接
        Connection conn = DbUtil.getConnection();
        try {
            String sql = "drop table "+tabName+";";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ptmt.execute();
            ptmt.close();
            JOptionPane.showMessageDialog(null, "名为" + tabName + "的表删除成功");
        }
        catch (SQLException e) {
            System.out.println("删除数据表失败" + e.getMessage());
        }
        }
    //连接表
    public static void joinTables(String[] tableNames) {
        //获取连接
        Connection conn = DbUtil.getConnection();
        try {
            String sql = "select " + tableNames[0] + ".*," + tableNames[1] + ".* FROM " + tableNames[0] + "," + tableNames[1] + " WHERE " + tableNames[0] + ".id=" + tableNames[1] + ".id;";
            PreparedStatement ptmt = conn.prepareStatement(sql);
            ResultSet rs = ptmt.executeQuery(sql);
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                //String user_id = rs.getString("user_id");
                String vote_num = rs.getString("vote_num");
                //String group_id = rs.getString("group_id");
                String status = rs.getString("status");
                //String create_time = rs.getString("create_time");
                // 输出数据
                System.out.print("ID: " + id);
                //System.out.print(", 用户ID: " + user_id);
                System.out.print(",投票数: " + vote_num);
               // System.out.print(",用户组id: " + group_id);
                System.out.print(",状态: " + status);
               // System.out.print(",创建时间:" + create_time);
                System.out.print("\n");
            }
            JOptionPane.showMessageDialog(null, "连接成功");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public static void main(String[] args) throws SQLException {
        while(true){
            int id;
            int new_num;
            String data;
            String[] choices = {"[0] 增", "[1] 删", "[2] 改", "[3] 查","[4] 建表","[5] 删表","[6] 连接表"};
            menuTool a = new menuTool();

            int choice = a.input_choice(choices);
            switch (choice){
                case 0:{
                    Date date=new Date();
                    vote_records g=new vote_records();
                    data = JOptionPane.showInputDialog
                            (null, "请依次输入id,用户id，投票数，状态", "增加记录", JOptionPane.PLAIN_MESSAGE);
                    String[] stringData = data.split(",");
                    int[] intData = new int[stringData.length];
                    for (int i = 0; i < stringData.length; i++)
                    {
                        if(i==1)continue;
                        intData[i] = Integer.parseInt(stringData[i]);
                    }
                   g.setId(intData[0]);
                   g.setVote_num(intData[2]);
                   g.setUser_id(stringData[1]);
                  // g.setCreate_time(date);
                   g.setStatus(intData[3]);
                    addvotes(g);
                }
                break;

                case 1:{
                    data = JOptionPane.showInputDialog
                            (null, "请输入要删除的记录的id", "删除记录", JOptionPane.PLAIN_MESSAGE);
                    id=Integer.parseInt(data);
                    delvotes(id);

                }
                break;
                //改
                case 2:{
                    data = JOptionPane.showInputDialog
                            (null, "请输入要修改的记录的id", "修改记录", JOptionPane.PLAIN_MESSAGE);
                    id=Integer.parseInt(data);
                    data = JOptionPane.showInputDialog
                            (null, "请输入新的投票数", "修改投票数", JOptionPane.PLAIN_MESSAGE);
                    new_num=Integer.parseInt(data);
                    reset(id, new_num);
                }
                break;
                //查
                case 3:{
                    vote_records g=null;
                    data = JOptionPane.showInputDialog
                            (null, "请输入要查询的记录的id", "查询记录", JOptionPane.PLAIN_MESSAGE);
                    id=Integer.parseInt(data);
                    if(!exitData(id))
                    {JOptionPane.showMessageDialog(null, "id为"+id+"的数据不存在");}
                   else{
                        g=query(id);
                        JOptionPane.showMessageDialog(null, g.toString());
                    }

                }
                break;
                //建表
                case 4:
                {
                    /*data = JOptionPane.showInputDialog
                            (null, "请输入要建立的表名", "创建表", JOptionPane.PLAIN_MESSAGE);
                    addtables(data);*/
                    //表名
            String tabName  = JOptionPane.showInputDialog
           (null, "请输入要创建的表格的名字", "建表", JOptionPane.PLAIN_MESSAGE);
                    //表字段
                    data = JOptionPane.showInputDialog
                            (null, "请依次表字段如（name,password,sex,age）", "建表", JOptionPane.PLAIN_MESSAGE);
                    String[] tab_fields = data.split(",");

                    //创建表
             createTable(tabName, tab_fields);
                }
                break;
                //删表
                case 5:{
                    String tabName  = JOptionPane.showInputDialog
                            (null, "请输入要删除的表格的名字", "删除表", JOptionPane.PLAIN_MESSAGE);
                    if(!exitTable(tabName))
                        JOptionPane.showMessageDialog(null, "名为"+tabName+"的表不存在");
                    else
                        deleteTable(tabName);
                }
                break;
                case 6:{
                    data = JOptionPane.showInputDialog
                            (null, "请依次输入要连接的两个表的表名", "join", JOptionPane.PLAIN_MESSAGE);
                    String[] stringData = data.split(",");
                    joinTables(stringData);
                }
                break;
            }
        }

    }
}
