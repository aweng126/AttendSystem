package Beans;

import Constants.TeachesSqlStatement;
import Utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/10/5.
 *  教师教课表格
 * */
public class Teaches  {
    private String teaches_id;
    private String course_id;
    private int room_id;
    private int time_id;
    private int acadyear_id;

    public Teaches(){}

    public Teaches(String teaches_id, String course_id) {
        this.teaches_id = teaches_id;
        this.course_id = course_id;
    }

    public String getTeaches_id() {
        return teaches_id;
    }

    public void setTeaches_id(String Teaches_id) {
        this.teaches_id = Teaches_id;
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

    public Teaches(String teaches_id, String course_id, int room_id, int time_id, int acadyear_id) {
        this.teaches_id = teaches_id;
        this.course_id = course_id;
        this.room_id = room_id;
        this.time_id = time_id;
        this.acadyear_id = acadyear_id;
    }

public int  delete(){
    Connection conn=null;
    PreparedStatement pstmt=null;
    boolean success=true;
    try {
        conn= DB.getConnection();
        pstmt=DB.getPStmt(conn, TeachesSqlStatement.TEACHES_DELETE);
        pstmt.setString(1,teaches_id);
        pstmt.setString(2,course_id);
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

    public int  save(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        boolean success=true;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TeachesSqlStatement.TEACHES_INSERT);

            pstmt.setString(1,teaches_id);
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

    public static Teaches getTeaches(String teachesid,String courseid){

        Connection conn=null;
        PreparedStatement pstmt=null;
        ResultSet rs=null;
        Teaches teaches=new Teaches();
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TeachesSqlStatement.TEACHES_SEARCH);
            pstmt.setString(1,teachesid);
            pstmt.setString(2,courseid);
            rs= pstmt.executeQuery();

            while (rs.next()){
                teaches.setTeaches_id(rs.getString("teacher_id"));
                teaches.setCourse_id(rs.getString("course_id"));
                teaches.setRoom_id(rs.getInt("room_id"));
                teaches.setAcadyear_id(rs.getInt("acadyear_id"));
                teaches.setTime_id(rs.getInt("time_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStmt(pstmt);
            DB.closeConn(conn);
        }
        return teaches;
    }


    @Override
    public String toString() {
        return "Teaches{" +
                "Teaches_id='" + teaches_id + '\'' +
                ", course_id='" + course_id + '\'' +
                ", room_id='" + room_id + '\'' +
                ", time_id='" + time_id + '\'' +
                ", acadyear_id='" + acadyear_id + '\'' +
                '}';
    }
}
