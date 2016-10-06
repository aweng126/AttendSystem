package Beans;

import Constants.TeacherSqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/10/6.
 *
 * 课程的详细信息
 */
public class DetailCourseInfo  {
    private Course course;
    private Teacher teacher;
    private ClassRoom classRoom;
    private AcademicYear academicYear;
    private CourseTime courseTime;
    private String realtime;

    public DetailCourseInfo(){

    }

    public DetailCourseInfo(Course course, Teacher teacher, ClassRoom classRoom, AcademicYear academicYear, CourseTime courseTime, String realtime) {
        this.course = course;
        this.teacher = teacher;
        this.classRoom = classRoom;
        this.academicYear = academicYear;
        this.courseTime = courseTime;
        this.realtime = realtime;
    }

    public String getRealtime() {
        return realtime;
    }

    public void setRealtime(String realtime) {
        this.realtime = realtime;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(AcademicYear academicYear) {
        this.academicYear = academicYear;
    }

    public CourseTime getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(CourseTime courseTime) {
        this.courseTime = courseTime;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public static DetailCourseInfo getDetailCourseInfo(String teacherid,String courseid){
        DetailCourseInfo detailCourseInfo=new DetailCourseInfo();
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TeacherSqlStatement.TEACHER_GETCOURSEDETIAL_OPTIMISE2);

            pstmt.setString(1,teacherid);
            pstmt.setString(2,courseid);
            rs=pstmt.executeQuery();

            while (rs.next()){

                Course course=new Course();
                course.setCourse_id(rs.getString("course_id"));
                course.setCourse_credit(rs.getInt("course_credit"));
                course.setCourse_name(rs.getString("course_name"));
                detailCourseInfo.setCourse(course);

                Teacher teacher=new Teacher();
                teacher.setTeacher_id(rs.getString("teacher_id"));
                teacher.setTeacher_name(rs.getString("teacher_name"));
                teacher.setTeacher_sex(rs.getString("teacher_sex"));
                teacher.setDept_name(rs.getString("dept_name"));
                detailCourseInfo.setTeacher(teacher);

                ClassRoom room=new ClassRoom();
                room.setBuilding(rs.getString("building"));
                room.setRoomnum(rs.getString("roomnum"));
                detailCourseInfo.setClassRoom(room);

                AcademicYear year=new AcademicYear();
                year.setTerm(rs.getString("term"));
                year.setEcadyear(rs.getString("acadyear"));
                detailCourseInfo.setAcademicYear(year);

                CourseTime time=new CourseTime();
                time.setOrder(rs.getString("order_id"));
                time.setWeek(rs.getString("time_week"));
                detailCourseInfo.setCourseTime(time);

                detailCourseInfo.setRealtime(rs.getString("real_time"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return detailCourseInfo;

    }

    @Override
    public String toString() {
        return "DetailCourseInfo{" +
                "course=" + course +
                ", teacher=" + teacher +
                ", classRoom=" + classRoom +
                ", academicYear=" + academicYear +
                ", courseTime=" + courseTime +
                ", realtime='" + realtime + '\'' +
                '}';
    }
}
