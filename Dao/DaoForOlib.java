package Dao;

import JDBCutils.DButil;
import Model.oBook;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DaoForOlib {
    oBook ob = new oBook();

    public void addBook() {
        Scanner scanner = new Scanner(System.in);
        oBook ob = new oBook(scanner);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "insert into wang_othlibrary" + "(BookName,BookNo,writer,publisher,Bvalue,genre,time,isLend)" + "value(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ob.getBookName());
            ps.setString(2, ob.getBookNo());
            ps.setString(3, ob.getWriter());
            ps.setString(4, ob.getPublicer());
            ps.setDouble(5, ob.getValue());
            ps.setString(6, "非计算机类");
            Timestamp t = new Timestamp(ob.getPurchaseDay().getTime());
            ps.setTimestamp(7, t);
//            ps.setDate(7, ob.getPurchaseDay());
            ps.setBoolean(8, ob.getIslend());
            int count = ps.executeUpdate();
            System.out.println(count==8?"操作成功":"操作失败");
//            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
//                if (conn != null) {
//                    conn.rollback();
//                }
            } catch (Exception throwables) {
                throwables.printStackTrace();
            }
//            释放资源
            DButil.close(conn, ps, rs);
        }
    }




    /**
     * 查询 非计算机类 书籍的所有信息
     *
     * @return
     */
    public List<oBook> retriveAll() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //创建Linklist结合接受数据
        List<oBook> linkedList = new ArrayList<oBook>();
        oBook oBook1 = null;
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "select * from wang_othlibrary";
            ps = conn.prepareStatement(sql);
            // 执行SQL
            rs = ps.executeQuery();

            //处理查询出来的结果集合
            if (rs.next()) {
                oBook1 = new oBook();
                oBook1.setBookNo(rs.getString("BookNo"));
                oBook1.setBookName(rs.getString("BookName"));
                oBook1.setWriter(rs.getString("writer"));
                oBook1.setPublicer(rs.getString("publisher"));
                oBook1.setValue(rs.getDouble("Bvalue"));
                oBook1.setGenre(rs.getString("genre"));
                Timestamp t = rs.getTimestamp("time");
                Date date = new Date(t.getTime());
                oBook1.setPurchaseDay(date);
//                oBook1.setPurchaseDay(t);
                oBook1.setPurchaseDay(rs.getDate("time"));
                oBook1.setIslend(rs.getBoolean("isLend"));
                linkedList.add(oBook1);
//                System.out.println(linkedList);
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
//                if (conn != null) {
//                    conn.rollback();
//                }
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
            //6. 释放资源
            DButil.close(conn, ps, rs);
        }
        return linkedList;
    }



    /**
     * 查询计算机内特定书籍的信息
     * @param s  书籍名
     * @return  展示集合
     */
    public List<oBook> retriveSpecificBook(String s) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //创建Linklist结合接受数据
        List<oBook> linkedList2 = new LinkedList<>();
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "select id,BookName,BookNo,writer,publisher,Bvalue,time,isLend from wang_othlibrary where BookName = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            // 执行SQL
            rs = ps.executeQuery();
            oBook oBook = new oBook();
//            oBook = null;
            //处理查询出来的结果集合
            if (rs.next()) {
                oBook.setBookNo(rs.getString("BookNo"));
                oBook.setBookName(rs.getString("BookName"));
                oBook.setWriter(rs.getString("writer"));
                oBook.setPublicer(rs.getString("publisher"));
                oBook.setValue(rs.getDouble("Bvalue"));
                oBook.setGenre(rs.getString("genre"));
//                oBook.setPurchaseDay(rs.getDate("time"));
                Timestamp t = rs.getTimestamp("time");
                Date date = new Date(t.getTime());
                oBook.setPurchaseDay(date);
                oBook.setIslend(rs.getBoolean("isLend"));
                linkedList2.add(oBook);
//                conn.commit();
                for (int i = 0; i < linkedList2.size(); i++) {
                    System.out.println(linkedList2.get(i).toString());
                }
            }
        } catch (Exception e) {
                e.printStackTrace();
        }finally {
            DButil.close(conn,ps,rs);
        }
        return linkedList2;
    }




    /**
     * 修改图书信息
     *
     */
    public String  modifyOBook() {
        Scanner scanner = new Scanner(System.in);
        oBook ob = new oBook(scanner);
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnetion();
//            获取预编译的数据库操作对象
            String sql = "update wang_othlibrary set BookName = ?,writer = ?,publisher = ?,Bvalue = ?,genre = ?,isLend = ?,time = ? where BookNo = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(8, ob.getBookNo());
//            执行SQL语句
            ps.setString(1, ob.getBookName());
//            ps.setString(2, ob.getBookNo());
            ps.setString(2, ob.getWriter());
            ps.setString(3, ob.getPublicer());
            ps.setDouble(4, ob.getValue());
            ps.setString(5, "非计算机类");
            Timestamp t = new Timestamp(ob.getPurchaseDay().getTime());
            ps.setTimestamp(7, t);
//            ps.setDate(7, ob.getPurchaseDay());
            ps.setBoolean(6, ob.getIslend());
            int count = ps.executeUpdate();
            System.out.println(count==1?"修改成功":"修改失败");
//            while (rs.next())
//            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            try {
//                if (conn != null){
//                conn.rollback();}
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            }
                DButil.close(conn, ps, rs);
//            }
            return "ISBN:" + ob.getBookNo() + "号图书信息修改成功";
        }
    }

    /**
     * 借书——》将图wang_othlibrary表的是否借出字段改为借出
     * @param s  图书isbn
     */
    public void borrow1(String s){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnetion();
            String sql = "update wang_othlibrary set isLend =true where bookNo = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            int count = ps.executeUpdate();
            System.out.println(count == 1 ? "主表借书成功" : "主表借书失败");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn,ps,rs);
        }
    }


    /**
     * 借书——》将图wang_othlibrary表的是否借出字段改为   未借出
     * @param s  图书isbn
     */
    public static void return2(String s){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButil.getConnetion();
            String sql = "update wang_othlibrary set isLend =false where bookNo = ? ";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s);
            int count = ps.executeUpdate();
            System.out.println(count == 1 ? "主表还书成功" : "主表还书失败");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DButil.close(conn,ps,rs);
        }
    }
}