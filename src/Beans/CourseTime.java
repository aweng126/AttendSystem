package Beans;

import Constants.SqlStatement;
import Constants.StudentSqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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


    public CourseTime(){}
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

    public static  int getTimeId(String time_week,String time_order){
        int timeId = 0 ;
        Connection conn=null;
        ResultSet rs=null;
        try {
            conn=DB.getConnection();
            PreparedStatement statement=DB.getPStmt(conn, SqlStatement.COURSETIME_SEARCH );
            statement.setString(1,time_week);
            statement.setString(2,time_order);
            rs=statement.executeQuery();

            while(rs.next()){

                timeId=rs.getInt("time_id");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return  timeId;
    }


    @Override
    public String toString() {
        return "CourseTime{" +
                "week='" + week + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
