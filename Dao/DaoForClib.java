package Dao;

import JDBCutils.DButil;
import Model.cBook;
import Model.oBook;
//import Model.oBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DaoForClib {
    cBook cb = new cBook();

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        cBook cb = new cBook(scanner);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "insert into wang_clibrary" + "(BookName,BookNo,writer,publisher,Bvalue,genre,time,isLend)" + "value(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cb.getBookName());
            ps.setString(2, cb.getBookNo());
            ps.setString(3, cb.getWriter());
            ps.setString(4, cb.getPublicer());
            ps.setDouble(5, cb.getValue());
            ps.setString(6, "计算机类");
            Timestamp t = new Timestamp(cb.getPurchaseDay().getTime());//将时间转换成Timestamp类型，这样便可以存入到Mysql数据库中
            ps.setTimestamp(7, t);
//            ps.setString(7,cb.getPurchaseDay());
            ps.setBoolean(8, cb.getIslend());
            int count = ps.executeUpdate();
            System.out.println("count = " + count);
//            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } //finally {
//            try {
////                if (conn != null){
////                    conn.rollback();
////                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }

//            释放资源
        DButil.close(conn, ps, rs);
    }


    /**
     * 查询计算机类书籍的所有信息
     *
     * @return
     */
    public List<cBook> retriveComputerAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //创建Linklist结合接受数据
        List<cBook> linkedList3 = new ArrayList<cBook>();
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "select * from wang_clibrary";
            ps = conn.prepareStatement(sql);
            // 执行SQL
            rs = ps.executeQuery();
//            cBook cBook = new cBook();
//            cBook = null;
            cBook cBook0 = null;
            //处理查询出来的结果集合
            while (rs.next()) {
                cBook0 = new cBook();
                cBook0.setBookNo(rs.getString("BookNo"));
                cBook0.setBookName(rs.getString("BookName"));
                cBook0.setWriter(rs.getString("writer"));
                cBook0.setPublicer(rs.getString("publisher"));
                cBook0.setValue(rs.getDouble("Bvalue"));
                cBook0.setGenre(rs.getString("genre"));
//                cBook.setPurchaseDay(rs.getString("time"));
                Timestamp t = rs.getTimestamp("time");
                Date date = new Date(t.getTime());
                cBook0.setPurchaseDay(date);
                cBook0.setIslend(rs.getBoolean("isLend"));

//                int id = rs.getInt("id");
//                String BookName = rs.getString("BookName");
//                String BookNo = rs.getString("BookNo");
//                String writer = rs.getString("writer");
//                String publisher = rs.getString("publisher");
//                String genre = rs.getString("genre");

                linkedList3.add(cBook0);
//                conn.commit();
                System.out.println(linkedList3);
            }
        } catch (Exception e) {
            System.out.println("错误代码：" + e);
        } finally {
            // 设置事务回滚
//            try {
//                if (conn != null) {
//                    conn.rollback();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
            //6. 释放资源
            DButil.close(conn, ps, rs);
        }
        return linkedList3;
    }


    /**
     * 查询计算机内特定书籍的信息
     *
     * @param s 书籍名
     * @return 展示集合
     */
    public List<cBook> retriveSpecificComputerBook(String s) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //创建Linklist结合接受数据
        List<cBook> linkedList4 = new LinkedList<>();
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "select id,BookName,BookNo,writer,publisher,Bvalue,time,isLend from wang_clibrary where BookName =?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            // 执行SQL
            rs = ps.executeQuery();
            cBook cBook = new cBook();
            //处理查询出来的结果集合
            if (rs.next()) {
                cBook.setBookNo(rs.getString("BookNo"));
                cBook.setBookName(rs.getString("BookName"));
                cBook.setWriter(rs.getString("writer"));
                cBook.setPublicer(rs.getString("publisher"));
                cBook.setValue(rs.getDouble("Bvalue"));
//                cBook.setGenre(rs.getString("genre"));
//                cBook.setPurchaseDay(rs.getString("time"));
//                Timestamp t = rs.getTimestamp("time");
                Timestamp t = rs.getTimestamp("time");
                Date date = new Date(t.getTime());
                cBook.setPurchaseDay(date);
                cBook.setIslend(rs.getBoolean("isLend"));
                linkedList4.add(cBook);
//                conn.commit();
                for (int i = 0; i < linkedList4.size(); i++) {
                    System.out.println(linkedList4.get(i).toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return linkedList4;
    }

    /**
     * 借书时修改wang_clibrary表，将书籍状态改为  借出
     *
     * @param s 书记的ISBN书号
     */
    public void borrow1(String s) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnetion();
            String sql = "update wang_clibrary set isLend=true where bookNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            int count = ps.executeUpdate();
            System.out.println(count == 1 ? "修改成功" : "修改失败");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }
    }


    /**
     * 还书时修改wang_clibrary表，将书籍状态改为  未借出
     *
     * @param s 书记的ISBN书号
     */
    public static void return2(String s) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnetion();
            String sql = "update wang_clibrary set isLend=false where bookNo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            int count = ps.executeUpdate();
            System.out.println(count == 1 ? "主表还书成功" : "主表还书失败");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn, ps, rs);
        }
    }


    /**
     * 修改图书信息
     */
    public String modifyCBook() {
        Scanner scanner = new Scanner(System.in);
        cBook cb = new cBook(scanner);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnetion();
//            获取预编译的数据库操作对象
            String sql = "update wang_clibrary set BookName = ?,writer = ?,publisher = ?,Bvalue = ?,genre = ?,isLend = ?,time = ? where BookNo = ? ";
            ps = conn.prepareStatement(sql);
//            执行SQL语句
            ps.setString(1, cb.getBookName());
            ps.setString(8, cb.getBookNo());
            ps.setString(2, cb.getWriter());
            ps.setString(3, cb.getPublicer());
            ps.setDouble(4, cb.getValue());
            ps.setString(5, "计算机类");
//            ps.setString(7, cb.getPurchaseDay());
            Timestamp t = new Timestamp(cb.getPurchaseDay().getTime());
            ps.setTimestamp(7, t);
            ps.setBoolean(6, cb.getIslend());
            int count = ps.executeUpdate();
            System.out.println(count == 1 ? "修改成功" : "修改失败");
//            while (rs.next())
//            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (conn != null){
//                    conn.rollback();}
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
            DButil.close(conn, ps, rs);
//        }
        }
        return "ISBN:" + cb.getBookNo() + "号图书信息修改成功";
    }

}
