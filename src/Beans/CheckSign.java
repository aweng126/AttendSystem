package Beans;

import Constants.SignSqlStatement;
import Constants.TeacherSqlStatement;
import Utils.DB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/10/7.
 * 用于老师查看学生签到情况
 */
public class CheckSign {

    private String stu_id;
    private String stu_name;
    private String stu_grade;
    private String stu_class;
    private Timestamp signtime;

    public  CheckSign(){}
    public CheckSign(String stu_id, String stu_name, String stu_grade, String stu_class, Timestamp signtime) {
        this.stu_id = stu_id;
        this.stu_name = stu_name;
        this.stu_grade = stu_grade;
        this.stu_class = stu_class;
        this.signtime = signtime;
    }

    public String getStu_id() {
        return stu_id;
    }

    public void setStu_id(String stu_id) {
        this.stu_id = stu_id;
    }

    public String getStu_name() {
        return stu_name;
    }

    public void setStu_name(String stu_name) {
        this.stu_name = stu_name;
    }

    public String getStu_grade() {
        return stu_grade;
    }

    public void setStu_grade(String stu_grade) {
        this.stu_grade = stu_grade;
    }

    public String getStu_class() {
        return stu_class;
    }

    public void setStu_class(String stu_class) {
        this.stu_class = stu_class;
    }

    public Timestamp getSigntime() {
        return signtime;
    }

    public void setSigntime(Timestamp signtime) {
        this.signtime = signtime;
    }


    public static List<CheckSign> getSigns(String stu_id,String course_id){
        List<CheckSign> signlist=new ArrayList<CheckSign>();
        Connection conn=null;
        ResultSet rs=null;
        try {
            conn= DB.getConnection();
            PreparedStatement statement=DB.getPStmt(conn, SignSqlStatement.SIGN_SEARCH);
            statement.setString(1,stu_id);
            statement.setString(2,course_id);
            rs=statement.executeQuery();
            while(rs.next()){
                CheckSign sign=new CheckSign();
                sign.setStu_id(rs.getString("stu_id"));
                sign.setStu_name(rs.getString("stu_name"));
                sign.setStu_class(rs.getString("stu_class"));
                sign.setStu_grade(rs.getString("stu_grade"));
                sign.setSigntime(rs.getTimestamp("signtime"));

                signlist.add(sign);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return signlist;

    }


    @Override
    public String toString() {
        return "CheckSign{" +
                "stu_id='" + stu_id + '\'' +
                ", stu_name='" + stu_name + '\'' +
                ", stu_grade='" + stu_grade + '\'' +
                ", stu_class='" + stu_class + '\'' +
                ", signtime=" + signtime +
                '}';
    }
}
