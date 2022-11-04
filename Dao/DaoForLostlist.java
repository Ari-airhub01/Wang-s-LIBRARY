package Dao;

import JDBCutils.DButil;
import Model.lost;
import Model.oBook;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DaoForLostlist {

    /**
     * 丢失非计算机类的图书使用此方法
     * @param
     * @return
     */
    public String addLostBook(){
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入书籍体裁:");
        String genre = sc.nextLine();
        oBook oBook = new oBook(sc);
        try {
            //            设置事务手动提交
//            conn.setAutoCommit(false);
            conn = DButil.getConnetion();
//            获取预编译的数据库操作对象
            String sql = "insert into lostlist(BookName,BookNo,writer,publisher,Bvalue,genre,time,isLend)" + " value(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, oBook.getBookName());
            ps.setString(2, oBook.getBookNo());
            ps.setString(3, oBook.getWriter());
            ps.setString(4, oBook.getPublicer());
            ps.setDouble(5, oBook.getValue());
            ps.setString(6, genre);
            Timestamp t = new Timestamp(oBook.getPurchaseDay().getTime());
            ps.setTimestamp(7, t);
            ps.setBoolean(8,oBook.getIslend());
            int count = ps.executeUpdate();
            System.out.println( count == 1? "丢失成功" : "丢失失败");

//            ps = conn.prepareStatement(sql);
//            int result= ps.executeUpdate();
//            System.out.println(result);
//            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                conn.rollback();
                DButil.close(conn,ps,rs);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
        }
        return "操作完成";
    }


    /**
     * 查询计算机内所有  丢失书籍   的信息
     *
     * @return
     */
    public List<lost> retriveLostList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        lost lo =null;
        //创建Linklist结合接受数据
        List<lost> linkedList = new LinkedList<>();
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
            lo =new lost();
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "select * from lostlist";
            ps = conn.prepareStatement(sql);
            // 执行SQL
            rs = ps.executeQuery();
            //处理查询出来的结果集合
            if (rs.next()) {
                lo.setBookName(rs.getString("bookName"));
                lo.setBookNo(rs.getString("bookNo"));
                lo.setWriter(rs.getString("writer"));
                lo.setPublicer(rs.getString("publisher"));
                lo.setValue(rs.getDouble("Bvalue"));
                lo.setGenre(rs.getString("genre"));
                Timestamp ts = rs.getTimestamp("Time");
                Date date =new Date(ts.getTime());
                lo.setIslend(rs.getBoolean("isLend"));
                linkedList.add(lo);
//                conn.commit();
                for (int i = 0; i < linkedList.size(); i++) {
                    System.out.println(linkedList.get(i).toString());
                }
            }
        } catch (Exception e) {
            System.out.println("错误代码：" + e);
        } finally {
            // 设置事务回滚
//            try {
////                if (conn != null) {
////                    conn.rollback();
////                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
            //6. 释放资源
            DButil.close(conn, ps, rs);
        }
        return linkedList;
    }

//    /**
//     * 丢失计算机类的图书使用此方法
//     * @return
//     */
//    public String addLostCBook(){
//        Connection conn = null;
//        ResultSet rs = null;
//        PreparedStatement ps = null;
//        Scanner sc = new Scanner(System.in);
//        System.out.println("请输入书籍体裁:");
//        String genre = sc.nextLine();
//        cBook cBook = new cBook(sc);
//        try {
//            //            设置事务手动提交
//            conn.setAutoCommit(false);
//            conn = DButil.getConnetion();
////            获取预编译的数据库操作对象
//            String sql = "insert into lostlist(BookName,BookNo,writer,publisher,Bvalue,genre,time,isLend)\" + \"value(?,?,?,?,?,?,?,?)";
//            ps = conn.prepareStatement(sql);
//            ps.setString(1, cBook.getBookName());
//            ps.setString(2, cBook.getBookNo());
//            ps.setString(3, cBook.getWriter());
//            ps.setString(4, cBook.getPublicer());
//            ps.setDouble(5, cBook.getValue());
//            ps.setString(6, genre);
//            ps.setString(7, cBook.getPurchaseDay());
//            ps.setBoolean(8,cBook.getIslend());
//            int count = ps.executeUpdate();
//            System.out.println( count == 1?"转账成功" :"转账失败");
//
////            ps = conn.prepareStatement(sql);
////            int result= ps.executeUpdate();
////            System.out.println(result);
//            conn.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                conn.rollback();
//                DButil.close(conn,ps,rs);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
//        }
//        return "操作完成";
//    }
}
