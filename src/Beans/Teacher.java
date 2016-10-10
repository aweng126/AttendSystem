package Beans;

import Constants.StudentSqlStatement;
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
            list=getTeacherInfoFromRS(rs);
        } finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return list;
    }

    public int  save(){
        Connection conn=null;
        PreparedStatement pstmt=null;
        boolean success=true;
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

    public static  int changePass(String tid,String newpass){
        Connection conn=null;
        PreparedStatement pstmt=null;
        Statement statement=null;
        try {

            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TeacherSqlStatement.TEACHER_CHANGEPASS);
            pstmt.setString(1,newpass);
            pstmt.setString(2,tid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }finally {
            DB.closeStmt(pstmt);
            DB.closeConn(conn);
        }
        return 1;
    };

    public static  List<Teacher> getTeacherInfo(String t_id){
        List<Teacher> list=new ArrayList<Teacher>();
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        try {
            conn=DB.getConnection();
            pstmt=DB.getPStmt(conn,Constants.TeacherSqlStatement.TEACHER_SEARCHWITHID);
            pstmt.setString(1,t_id);
            rs=pstmt.executeQuery();
            list=getTeacherInfoFromRS(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return list;
    }

    private  static  List<Teacher> getTeacherInfoFromRS(ResultSet rs){
        List<Teacher> list=new ArrayList<Teacher>();
        try {
            while(rs.next()){
                Teacher teacher=new Teacher();
                teacher.setTeacher_id(rs.getString("teacher_id"));
                teacher.setTeacher_name(rs.getString("teacher_name"));
                teacher.setTeacher_pass(rs.getString("teacher_pass"));
                teacher.setDept_name(rs.getString("dept_name"));
                teacher.setTeacher_isadmin(rs.getString("teacher_isadmin"));
                teacher.setTeacher_sex(rs.getString("teacher_sex"));
                list.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return  list;
    }

    public static List<Student>  getStudentsWithCourse (String courseid,int page){
        List<Student> studentlist=new ArrayList<Student>();
        Connection conn=null;
        ResultSet rs=null;
        try {
            conn=DB.getConnection();
            PreparedStatement statement=DB.getPStmt(conn,TeacherSqlStatement.TEACHER_CHECKSTUDENT);
            statement.setString(1,courseid);
            statement.setInt(2,(page-1)*7);
            rs=statement.executeQuery();
            while(rs.next()){
                Student student=new Student();
                student.setStuid(rs.getString("stu_id"));
                student.setStuname(rs.getString("stu_name"));
                student.setStupass("****");
                student.setStusex(rs.getString("stu_sex"));
                student.setStuclass(rs.getString("stu_class"));
                student.setStugrade(rs.getString("stu_grade"));
                student.setDept_name(rs.getString("dept_name"));
                studentlist.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return studentlist;
    }


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

    public static List<Course> getMyTeach(String teacherid) {
        List<Course> courses=new ArrayList<Course>();
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        try {
            conn=DB.getConnection();
            pstmt=DB.getPStmt(conn, TeacherSqlStatement.TEACHER_GETMTEACH);
            pstmt.setString(1,teacherid);
            rs=pstmt.executeQuery();
            while (rs.next()){
                Course course=new Course();
                course.setCourse_id(rs.getString("course_id"));
                course.setCourse_name(rs.getString("course_name"));
                course.setCourse_credit(rs.getInt("course_credit"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return courses;
    }

    /**
     * 登录注册界面，用于检测我们的用户名和密码
     * @param id   老师的账号
     * @param pass  登录的密码
     * @return      返回是否存在
     */
    public static int islegal(String id, String pass) {
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        int   admin=-1;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TeacherSqlStatement.TEACHER_LOGIN) ;
            pstmt.setString(1, id);
            pstmt.setString(2, pass);
            rs=pstmt.executeQuery();

            if (rs.next()){
                //得到是否是管理员
                admin=Integer.parseInt(rs.getString("teacher_isadmin"));
                System.out.println("admin"+admin+"admin");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStmt(pstmt);
            DB.closeConn(conn);

            return admin;
        }
    }

}
