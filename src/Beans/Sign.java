package Beans;

import Constants.SignSqlStatement;
import Constants.TakesSqlStatement;
import Utils.DB;
import sun.rmi.server.Util;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.sql.*;
import java.util.Date;

/**
 * Created by kingwen on 2016/10/6.
 * 签到表格对应的javabean
 */
public class Sign {

    private String stu_id;
    private String course_id;
    private Timestamp signtime;

    public Sign(String stu_id, String course_id, Timestamp signtime) {
        this.stu_id = stu_id;
        this.course_id = course_id;
        this.signtime = signtime;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public Timestamp getSigntime() {
        return signtime;
    }

    public void setSigntime(Timestamp signtime) {
        this.signtime = signtime;
    }

/*
    public static void addSign(String stu_id,String course_id){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Statement statement=null;
        boolean success=true;
        try {
            conn= DB.getConnection();
           // pstmt=DB.getPStmt(conn, SignSqlStatement);

            pstmt.setString(1,stu_id);
            pstmt.setString(2,course_id);
         //   pstmt.setInt(3,room_id);


            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            success=false;

        }finally {
            DB.closeStmt(pstmt);
            DB.closeConn(conn);
            if(success){
                return 1;
            }else {
                return 0;
            }
        }

           }*/


    @Override
    public String toString() {
        return "Sign{" +
                "stu_id='" + stu_id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", signtime=" + signtime +
                '}';
    }


    /*public static void main(String[] args) {
        java.util.Date date = new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        System.out.println(timeStamp);


    }*/
}
