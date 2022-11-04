package JDBCutils;

import java.sql.*;

public class DButil {

    /**
     * 静态代码块在类执行时执行，且只执行一次
     */
    static{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 工具类中的构造方法都是私有的
     * 因为工具当中的方法都是静态的，不需要new对象，直接类名.方法名调用
     */
    public static Connection getConnetion() throws Exception{
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/wang_library","root","admin");
    }

    /**
     * 关闭资源
     * @param conn  数据库连接对象
     * @param stmt  数据库操作对象
     * @param rs  结果集合
     */
    public static void close(Connection conn, Statement ps, ResultSet rs){
        try {
            if (rs != null){
                rs.close();
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        try {
            if (ps != null){
                ps.close();
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

        try {
            if (conn != null){
                conn.close();
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
    }
}
