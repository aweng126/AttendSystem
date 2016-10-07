package Beans;

import Constants.TakesSqlStatement;
import Constants.TeachesSqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kingwen on 2016/10/6.
 */
public class Takes {

    private String stu_id;
    private String course_id;
    private int room_id;
    private int time_id;
    private int acadyear_id;
    private int score;


    public Takes(){}

    public Takes(String stu_id, String course_id, int room_id, int time_id, int acadyear_id, int score) {
        this.stu_id = stu_id;
        this.course_id = course_id;
        this.room_id = room_id;
        this.time_id = time_id;
        this.acadyear_id = acadyear_id;
        this.score = score;
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

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public int getTime_id() {
        return time_id;
    }

    public void setTime_id(int time_id) {
        this.time_id = time_id;
    }

    public int getAcadyear_id() {
        return acadyear_id;
    }

    public void setAcadyear_id(int acadyear_id) {
        this.acadyear_id = acadyear_id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static  int delete(String stuid,String courseid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Statement statement=null;
        boolean success=true;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TakesSqlStatement.TAKES_DELETE);

            pstmt.setString(1,stuid);
            pstmt.setString(2,courseid);
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

    public static int AddScore(int score,String stuid,String courseid){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Statement statement=null;
        boolean success=true;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TakesSqlStatement.TAKES_ADDSCORE);

            pstmt.setInt(1,score);
            pstmt.setString(2,stuid);
            pstmt.setString(3,courseid);

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

    public static void  saveByQR(String stuid,Teaches teaches){
        Takes takes=new Takes();
        takes.setStu_id(stuid);
        takes.setCourse_id(teaches.getCourse_id());
        takes.setRoom_id(teaches.getRoom_id());
        takes.setTime_id(teaches.getTime_id());
        takes.setAcadyear_id(teaches.getAcadyear_id());
        takes.save();
    }


    public int save(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Statement statement=null;
        boolean success=true;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn,TakesSqlStatement.TAKES_INSERT);

            pstmt.setString(1,stu_id);
            pstmt.setString(2,course_id);
            pstmt.setInt(3,room_id);
            pstmt.setInt(4,time_id);
            pstmt.setInt(5,acadyear_id);

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

    };


    @Override
    public String toString() {
        return "Takes{" +
                "stu_id='" + stu_id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", room_id=" + room_id +
                ", time_id=" + time_id +
                ", acadyear_id=" + acadyear_id +
                ", score=" + score +
                '}';
    }
}
