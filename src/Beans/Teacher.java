package Beans;

import Constants.TeacherSqlStatement;
import Utils.DB;
import Constants.SqlStatement;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/9/13.
 * 教师 javabean
 */
public class Teacher {

    private String teacher_id;
    private String teacher_name;
    private String teacher_pass="0000";
    private String teacher_isadmin;
    private String dept_name;
    private String teacher_sex="男";

    public Teacher(){}

    public Teacher(String teacher_id, String teacher_name, String teacher_isadmin, String dept_name) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.teacher_isadmin = teacher_isadmin;
        this.dept_name = dept_name;
    }

    public Teacher(String teacher_id, String teacher_name, String teacher_pass, String teacher_isadmin, String dept_name, String teacher_sex) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.teacher_pass = teacher_pass;
        this.teacher_isadmin = teacher_isadmin;
        this.dept_name = dept_name;
        this.teacher_sex = teacher_sex;
    }

    public String getTeacher_sex() {
        return teacher_sex;
    }

    public void setTeacher_sex(String teacher_sex) {
        this.teacher_sex = teacher_sex;
    }

    public String getTeacher_pass() {
        return teacher_pass;
    }

    public void setTeacher_pass(String teacher_pass) {
        this.teacher_pass = teacher_pass;
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

    public String getTeacher_isadmin() {
        return teacher_isadmin;
    }

    public void setTeacher_isadmin(String teacher_isadmin) {
        this.teacher_isadmin = teacher_isadmin;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String teacher_dept_name) {
        this.dept_name = teacher_dept_name;
    }

    public static List<Teacher> getTeachers(){
        List<Teacher> list=new ArrayList<Teacher>();
        Connection conn=null;
        ResultSet rs=null;
        try {
            conn=DB.getConnection();
            rs=DB.execteQuery(conn, TeacherSqlStatement.TEACHER_SEARCHALL);
            while(rs.next()){
                Teacher teacher=new Teacher();
                teacher.setTeacher_id(rs.getString("teacher_id"));
                teacher.setTeacher_name(rs.getString("teacher_name"));
                teacher.setTeacher_pass(rs.getString("teacher_pass"));
                teacher.setDept_name(rs.getString("dept_name"));
                teacher.setTeacher_isadmin(rs.getString("teacher_admin"));
                teacher.setTeacher_sex(rs.getString("teacher_sex"));
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return list;
    }

    public void save(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Statement statement=null;
            try {

                conn= DB.getConnection();
                    pstmt=DB.getPStmt(conn,Constants.TeacherSqlStatement.TEACHER_INSERT);
                    pstmt.setString(1,teacher_id);
                     pstmt.setString(2,teacher_name);
                     pstmt.setString(3,teacher_pass);

                    pstmt.setString(4,dept_name);
                    pstmt.setString(5,teacher_isadmin);
                     pstmt.setString(6,teacher_sex);
                    pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

    };

    @Override
    public String toString() {
        return "Teacher{" +
                "teacher_id='" + teacher_id + '\'' +
                ", teacher_name='" + teacher_name + '\'' +
                ", teacher_pass='" + teacher_pass + '\'' +
                ", teacher_isadmin='" + teacher_isadmin + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
