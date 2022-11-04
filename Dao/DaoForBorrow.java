package Dao;

import JDBCutils.DButil;
import Model.borrowBook;
import Model.oBook;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DaoForBorrow {

    public void borrow(){
        Scanner sc =new Scanner(System.in);
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        borrowBook borrowBook = new borrowBook(sc);

        try {
//            获取数据库链接
            conn = DButil.getConnetion();
//            conn.setAutoCommit(false);
//            获取预编译的数据库操作对象
            String sql = "insert into borrowlist(borrReader,bookName,bookNo,Bvalue,borrTime,Tel)" + "values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,borrowBook.getBorrReader());
            ps.setString(2,borrowBook.getBookName());
            ps.setString(3,borrowBook.getBookNo());
            ps.setDouble(4,borrowBook.getValue());
            Timestamp t = new Timestamp(borrowBook.getBorrTime().getTime());
            ps.setTimestamp(5, t);
            ps.setString(6,borrowBook.getTel());
//            ps = conn.prepareStatement(sql);
//            String sql2 = ""
//            conn.commit();
            int count = ps.executeUpdate();
            System.out.println(count==1?"数据写入成功":"数据写入失败");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
            //6. 释放资源
            DButil.close(conn, ps, rs);
        }catch (Exception e){
                System.out.println("抛出成功"+e);
            }
    }
    }

//执行前要输入ISBN
    public void returnBook(String s){
        Scanner sc =new Scanner(System.in);
        Connection conn= null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
//            获取数据库链接
            conn = DButil.getConnetion();
//            conn.setAutoCommit(false);//设置JDBC事务自动提交改为手动提交
//            获取预编译的数据库操作对象
            String sql = "delete from borrowlist where bookNo= ?" ;
            ps = conn.prepareStatement(sql);
            ps.setString(1,s);
            int count = ps.executeUpdate();
//            System.out.println(count==1?"执行成功":"执行失败");
            System.out.println("执行完成");
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //6. 释放资源
            DButil.close(conn, ps, rs);
        }
    }


    /**
     * 查询计算机内所有   借出书籍    的信息
     *
     * @return
     */
    public List<borrowBook> retriveBorrowList() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //创建Linklist结合接受数据
        List<borrowBook> linkedList = new LinkedList<>();
        borrowBook bb = null;
        try {
            //设置成JDBC手动提交的事物
//            conn.setAutoCommit(false);
            bb= new borrowBook();
//        获取连接对象
            conn = DButil.getConnetion();
//        获取预编译的数据库操作对象
            String sql = "select * from borrowlist";
            ps = conn.prepareStatement(sql);
            // 执行SQL
            rs = ps.executeQuery();
            //处理查询出来的结果集合
            if (rs.next()) {
                bb.setBorrReader(rs.getString("borrReader"));
                bb.setBookName(rs.getString("bookName"));
                bb.setBookNo(rs.getString("bookNo"));
                bb.setValue(rs.getDouble("Bvalue"));
//                bb.setBorrTime(rs.getDate("borrTime"));
                Timestamp t = rs.getTimestamp("borrTime");
                Date date = new Date(t.getTime());
                bb.setTel(rs.getString("Tel"));
                linkedList.add(bb);
                for (int i = 0; i < linkedList.size(); i++) {
                    System.out.println(linkedList.get(i).toString());
                }
            }
        } catch (Exception e) {
            System.out.println("错误代码：" + e);
        } finally {
            //6. 释放资源
            DButil.close(conn, ps, rs);
        }
        return linkedList;
    }

}
