package Beans;

import Constants.SignSqlStatement;
import Utils.DB;
import Utils.TimeUtils;

import java.sql.*;


/**
 * Created by kingwen on 2016/10/6.
 * 签到表格对应的javabean
 */
public class Sign {

    private String stu_id;
    private String course_id;
    private Timestamp signtime;
    private int sign_week;
    private String sign_grade;
    private String sign_class;

    public Sign(){}

    public Sign(String stu_id, String course_id, Timestamp signtime, int sign_week, String sign_grade, String sign_class) {
        this.stu_id = stu_id;
        this.course_id = course_id;
        this.signtime = signtime;
        this.sign_week = sign_week;
        this.sign_grade = sign_grade;
        this.sign_class = sign_class;
    }

    public int getSign_week() {
        return sign_week;
    }

    public void setSign_week(int sign_week) {
        this.sign_week = sign_week;
    }

    public String getSign_grade() {
        return sign_grade;
    }

    public void setSign_grade(String sign_grade) {
        this.sign_grade = sign_grade;
    }

    public String getSign_class() {
        return sign_class;
    }

    public void setSign_class(String sign_class) {
        this.sign_class = sign_class;
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


    public static int save(Student student,String course_id){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Timestamp now = TimeUtils.getCurrentTimeStamp();
        int weekOfSecondAcadeyar=TimeUtils.getWeekByData(now);
        boolean success=true;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, SignSqlStatement.SIGN_INSERT);

            pstmt.setString(1,student.getStuid());
            pstmt.setString(2,course_id);
            pstmt.setTimestamp(3,now);
            pstmt.setInt(4,weekOfSecondAcadeyar);
            pstmt.setString(5,student.getStugrade());
            pstmt.setString(6,student.getStuclass());
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

    }




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
