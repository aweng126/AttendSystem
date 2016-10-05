package Utils;

import java.sql.*;

/**
 * Created by kingwen on 2016/9/11.
 * 数据库连接类，属于工具类
 */
public class DB {
    //Db对象
    private static DB db;

    static {
        db=new DB();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
   //构造方法
    private  DB(){}

    /**
     * 返回一个连接connection
     * @return 一个连接connection对象
     */
    public static Connection getConnection(){
        Connection conn=null;
        try {
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/sqldesign?user=root&password=mysql" +
                    "&serverTimezone=GMT&&useUnicode=true&characterEncoding=UTF8");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 关闭连接connection
     * @param conn 需要关闭的connection对象
     */
    public static void closeConn(Connection conn){
        if(conn!=null){
            try {
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 得到sql语句的对象
     * @param conn 与之建立连接的对象
     * @return
     */
    public static Statement getStmt(Connection conn){
        Statement stmt=null;
        try {
            stmt=conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    /**
     * 关闭语句
     * @param stmt
     */
    public static void closeStmt(Statement stmt){
        try {
            if(stmt!=null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * preparedstatament
     * @param conn 连接
     * @param sql    对应的sql语句
     * @return       返回一个preparedStatement
     */
    public static PreparedStatement getPStmt(Connection conn,String sql){
        PreparedStatement pstmt=null;
        try {
            pstmt=conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

   public static  ResultSet executeQuery(Statement stmt,String sql){
       ResultSet rs=null;
       try {
           rs=stmt.executeQuery(sql);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return rs;
   }


   public static ResultSet execteQuery(Connection conn,String sql){
       ResultSet rs=null;
       try {
           rs=conn.createStatement().executeQuery(sql);
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return rs;
   }

    /**
     * 关闭结果集
     * @param rs 需要关掉的结果集
     */
    public static void closeRS(ResultSet rs){
      if(rs!=null){
          try {
              rs.close();
              rs=null;
          } catch (SQLException e) {
              e.printStackTrace();
          }
        }
    }

    /**
     * 关闭connection
     * @param conn
     */
    public static void closeCon(Connection conn){
        if(conn!=null){
            try {
                conn.close();
                conn=null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 通过遍历得到我们resultset结果集中的数目
     * @param rs 我们需要进行计算结果数目的resultset
     * @return   返回rs中的结果的数目
     */
    public static int getRestultSetSize(ResultSet rs) {
        int rows=0;
        try {
            while(rs.next()){
                rows++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB+getResultsize");
        }
        return rows;
    }




}
