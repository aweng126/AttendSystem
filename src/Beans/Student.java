package Beans;

import Utils.DB;
import Constants.SqlStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by kingwen on 2016/9/13.
 * 对于学生实体的javabean
 */
public class Student  {
    private String stuid;
    private String stuname;
    private String stupass;
    private String stusex;
    private String stuclass;
    private String stugrade;
    private String dept_name;

    public Student(){

    }

    public Student(String id, String name, String pass, String sex, String calss, String grade, String deptname) {
        stuid=id;
        stuname=name;
        stupass=pass;
        stusex=sex;
        stuclass=calss;
        stugrade=grade;
        dept_name=deptname;
    }



    public String getStuid() {
        return stuid;
    }

    public void setStdid(String stdid) {
        this.stuid = stdid;
    }

    public String getStuname() {
        return stuname;
    }

    public void setStuname(String stuname) {
        this.stuname = stuname;
    }

    public String getStusex() {
        return stusex;
    }

    public void setStuid(String stuid) {
        this.stuid = stuid;
    }

    public String getStupass() {
        return stupass;
    }

    public void setStupass(String stupass) {
        this.stupass = stupass;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }

    public void setStusex(String stusex) {
        this.stusex = stusex;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public String getStugrade() {
        return stugrade;
    }

    public void setStugrade(String stugrade) {
        this.stugrade = stugrade;
    }

    /**
     * 鉴于前端后台分离的原则，对于javabean进行自定义的数据库插入
     */
    public void save(){
        Connection conn= null;
        PreparedStatement pStmt=null;
        try {
            conn= DB.getConnection();
            pStmt=DB.getPStmt(conn, SqlStatement.STUDENT_INSERT);
            System.out.print(stuid);
            pStmt.setString(1,stuid);
            pStmt.setString(2,stuname);
            pStmt.setString(3,stupass);
            pStmt.setString(4,stusex);
            pStmt.setString(5,stuclass);
            pStmt.setString(6,stugrade);
            pStmt.setString(7,dept_name);
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStmt(pStmt);
            DB.closeConn(conn);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuid='" + stuid + '\'' +
                ", stuname='" + stuname + '\'' +
                ", stupass='" + stupass + '\'' +
                ", stusex='" + stusex + '\'' +
                ", stuclass='" + stuclass + '\'' +
                ", stugrade='" + stugrade + '\'' +
                ", dept_name='" + dept_name + '\'' +
                '}';
    }
}
