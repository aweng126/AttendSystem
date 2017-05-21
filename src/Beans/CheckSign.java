package Beans;
import Constants.SignSqlStatement;
import Utils.DB;
import Utils.TimeUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
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

    private int current_week;

    public int getCurrent_week() {
        return current_week;
    }

    public void setCurrent_week(int current_week) {
        this.current_week = current_week;
    }

    public int getSign_week() {
        return sign_week;
    }

    public void setSign_week(int sign_week) {
        this.sign_week = sign_week;
    }

    private int sign_week;

    private int attendRate;

    public int getAttendRate() {
        return attendRate;
    }

    public void setAttendRate(int attendRate) {
        this.attendRate = attendRate;
    }

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
            int i=1;
            while(rs.next()){
                CheckSign sign=new CheckSign();
                sign.setStu_id(rs.getString("stu_id"));
                sign.setStu_name(rs.getString("stu_name"));
                sign.setStu_class(rs.getString("stu_class"));
                sign.setStu_grade(rs.getString("stu_grade"));
                sign.setSigntime(rs.getTimestamp("signtime"));
                sign.setSign_week(rs.getInt("sign_week"));
                sign.setCurrent_week(TimeUtils.getCurrentWeek());
                sign.setAttendRate(100*i/ TimeUtils.getCurrentWeek());
                i++;
                signlist.add(sign);
            }

            Collections.reverse(signlist);


            //如果始终没有签到数据，那么就返回空数值
            if(i==1){
                CheckSign sign=new CheckSign();
                sign.setStu_id(null);
                sign.setStu_name(null);
                sign.setStu_class(null);
                sign.setStu_grade(null);
                sign.setSigntime(null);
                sign.setSign_week(0);
                sign.setCurrent_week(TimeUtils.getCurrentWeek());
                sign.setAttendRate(0);
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
                ", current_week=" + current_week +
                ", sign_week=" + sign_week +
                ", attendRate=" + attendRate +
                '}';
    }
}
