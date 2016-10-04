package Beans;

import Utils.DB;
import Constants.SqlStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kingwen on 2016/9/13.
 * 教师 javabean
 */
public class Teacher {

    private boolean isfirst=true;
    private String teacher_id;
    private String teacher_name;
    private String teacher_pass;

    public String getTeacher_pass() {
        return teacher_pass;
    }

    public void setTeacher_pass(String teacher_pass) {
        this.teacher_pass = teacher_pass;
    }

    private String teacher_isadmin;
    private String dept_name;
    public Teacher(){}
    public Teacher(String teacherid, String teachername,String teacherpass,String teacherisadmin,String teacherdept_name){
        dept_name=teacherdept_name;
        teacher_name=teachername;
        teacher_pass=teacherpass;
        teacher_isadmin=teacherisadmin;
        teacher_id=teacherid;
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

    public void save(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Statement statement=null;
            try {

                conn= DB.getConnection();
                if(isfirst) {
                    statement = DB.getStmt(conn);
                    statement.execute(SqlStatement.TEACHER_CREATE);
                }else{
                    pstmt=DB.getPStmt(conn,SqlStatement.STUDENT_INSERT);
                    pstmt.setString(1,teacher_id);
                    pstmt.setString(2,teacher_name);
                    pstmt.setString(3,teacher_pass);
                    pstmt.setString(4,teacher_isadmin);
                    pstmt.setString(5,dept_name);
                    pstmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

    };

}
