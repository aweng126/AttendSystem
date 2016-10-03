package Beans;

import Constants.SqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by kingwen on 2016/10/4.
 *上课时间的javabean  周次 和  节次
 * */
public class CourseTime  {
    /**
     * 周次
     */
  private  String week;

    /**
     * 节次
     */
    private String order;

    public CourseTime(String week, String order) {
        this.week = week;
        this.order = order;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public void save(){
        System.out.println(this.toString());

        Connection conn= null;
        PreparedStatement pStmt=null;
        try {
            conn= DB.getConnection();
            pStmt=DB.getPStmt(conn, SqlStatement.COURSETIME_INSERT);
           // System.out.print(ecadyear);
            pStmt.setString(1,week);
            pStmt.setString(2,order);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStmt(pStmt);
            DB.closeConn(conn);
        }
    }

    @Override
    public String toString() {
        return "CourseTime{" +
                "week='" + week + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
