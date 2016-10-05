package Beans;

import Constants.SqlStatement;
import Constants.CourseSqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/10/4.
 *
 *课程 javabean
 */
public class Course {
    //课程号
    private String course_id;
    //课程名
    private String course_name;
    //课程学分
    private int course_credit;

    public Course(){}
    public Course(String course_id, String course_name, int course_credit) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.course_credit = course_credit;
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public int getCourse_credit() {
        return course_credit;
    }

    public void setCourse_credit( int course_credit) {
        this.course_credit = course_credit;
    }

    public void save(){
        Connection conn= null;
        PreparedStatement pStmt=null;
        try {
            conn= DB.getConnection();
            pStmt=DB.getPStmt(conn, Constants.CourseSqlStatement.COURSE_INSERT);
            pStmt.setString(1,course_id);
            pStmt.setString(2,course_name);
            pStmt.setInt(3,course_credit);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStmt(pStmt);
            DB.closeConn(conn);
        }
    }

    public static List<Course> getAllCourses(){
        List<Course> list=new ArrayList<Course>();
        Connection conn=null;
        ResultSet rs=null;
        try {
            conn=DB.getConnection();
            rs=DB.execteQuery(conn, CourseSqlStatement.COURSE_SEARCHAll);
            while(rs.next()){
                Course course=new Course();
                course.setCourse_id(rs.getString("course_id"));
                course.setCourse_credit(rs.getInt("course_credit"));
                course.setCourse_name(rs.getString("course_name"));
                list.add(course);
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
        return "Course{" +
                "course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_credit='" + course_credit + '\'' +
                '}';
    }
}
