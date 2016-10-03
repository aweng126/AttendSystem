package Beans;

import Constants.SqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by kingwen on 2016/10/4.
 * 这个类是学年的bean。
 */
public class AcademicYear {
    /**
     * 学年 如14-15学年
      */
    private String ecadyear;
    /**
     * 学期 第一学期，第二学期
     */
    private String term;

    /**
     * 构造方法
     * @param ecadyear 学年
     * @param term 学期
     */
    public AcademicYear(String ecadyear, String term) {
        this.ecadyear = ecadyear;
        this.term = term;
    }

    /**
     * 将数据存入数据库
     */
    public  void saveYear(){

        System.out.println(this.toString());

            Connection conn= null;
            PreparedStatement pStmt=null;
            try {
                conn= DB.getConnection();
                pStmt=DB.getPStmt(conn, SqlStatement.ACADEMICYEAR_INSERT);
                System.out.print(ecadyear);
                pStmt.setString(1,ecadyear);
                pStmt.setString(2,term);
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
        return "AcademicYear{" +
                "ecadyear='" + ecadyear + '\'' +
                ", term='" + term + '\'' +
                '}';
    }

    public String getEcadyear() {
        return ecadyear;
    }

    public void setEcadyear(String ecadyear) {
        this.ecadyear = ecadyear;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
