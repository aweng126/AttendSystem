package Beans;

import Constants.TeachesSqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/10/5.
 */
public class TeachInfo  {


    public TeachInfo(String course_id, String course_name, int course_credit, String teacher_id, String teacher_name, String dept_name) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_credit = course_credit;
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.dept_name = dept_name;
    }
    private String course_id ;
    private String course_name;
    private int course_credit;
    private String teacher_id;
    private String teacher_name;
    private String dept_name;


    public TeachInfo(){}

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_credit(int course_credit) {
        this.course_credit = course_credit;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_credit() {
        return course_credit;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public static List<TeachInfo> getAllTeachInfo(){
        List<TeachInfo> list=new ArrayList<TeachInfo>();
        Connection conn=null;
        ResultSet rs=null;
        try {
            conn= DB.getConnection();
            rs=DB.execteQuery(conn, TeachesSqlStatement.TEACHES_SEARCHALL);
            while(rs.next()){
                TeachInfo teachInfo=new TeachInfo();
                teachInfo.setCourse_id(rs.getString("course_id"));
                teachInfo.setCourse_credit(rs.getInt("course_credit"));
                teachInfo.setTeacher_name(rs.getString("teacher_name"));
                teachInfo.setTeacher_id(rs.getString("teacher_id"));
                teachInfo.setCourse_name(rs.getString("course_name"));
                teachInfo.setDept_name(rs.getString("dept_name"));
                list.add(teachInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return list;
    }
    
    @Override
    public String toString() {
        return "TeachInfo{" +
                "course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_credit='" + course_credit + '\'' +
                ", teacher_id='" + teacher_id + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
