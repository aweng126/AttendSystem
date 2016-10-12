package Beans;

import Constants.CourseSqlStatement;
import Constants.SignSqlStatement;
import Constants.TeacherSqlStatement;
import Utils.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kingwen on 2016/10/9.
 */
public class GradeClass {

    private String mgrade;
    private String mclass;

    private GradeClass(){}

    public GradeClass(String mgrade, String mclass) {
        this.mgrade = mgrade;
        this.mclass = mclass;
    }

    public String getMgrade() {
        return mgrade;
    }

    public void setMgrade(String mgrade) {
        this.mgrade = mgrade;
    }

    public String getMclass() {
        return mclass;
    }

    public void setMclass(String mclass) {
        this.mclass = mclass;
    }




    public static List<GradeClass> getMyGrade(String teacher_id,String course_id){
        List<GradeClass> list=new ArrayList<GradeClass>();
        Connection conn=null;
        ResultSet rs=null;

        try {
            conn= DB.getConnection();
            PreparedStatement statement=DB.getPStmt(conn, TeacherSqlStatement.TEACHER_GET_GREDEANDCLASS_COURSE);
            statement.setString(1,course_id);
            statement.setString(2,teacher_id);

            rs=statement.executeQuery();

            while(rs.next()){
                GradeClass gradeClass=new GradeClass();
                gradeClass.setMclass(rs.getString("stu_class"));
                gradeClass.setMgrade(rs.getString("stu_grade"));

                if(!hasContaingradeClass(list,gradeClass)){
                    list.add(gradeClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return list;

    }


    public static List<GradeClass> getMyGrade(String teacher_id){
        List<GradeClass> list=new ArrayList<GradeClass>();
        Set<GradeClass> mgradeClass=new HashSet<>();
        Connection conn=null;
        ResultSet rs=null;

        try {
            conn= DB.getConnection();
            PreparedStatement statement=DB.getPStmt(conn, SignSqlStatement.SIGN_SEARCH);
            statement.setString(1,teacher_id);
            rs=DB.execteQuery(conn, TeacherSqlStatement.TEACHER_GET_GREDEANDCLASS);
            while(rs.next()){
                GradeClass gradeClass=new GradeClass();
                gradeClass.setMclass(rs.getString("stu_class"));
                gradeClass.setMgrade(rs.getString("stu_grade"));

                if(!hasContaingradeClass(list,gradeClass)){
                    list.add(gradeClass);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        System.out.println(mgradeClass.toString());

        return list;

    }

    private static boolean hasContaingradeClass(List<GradeClass> mgradeClasses,GradeClass gradeClass) {
        int size=mgradeClasses.size();
        GradeClass gc=null;
        for(int i=0;i<size;i++){
            gc=mgradeClasses.get(i);
            if(gc.getMclass().equals(gradeClass.getMclass())&&gc.getMgrade().equals(gradeClass.getMgrade())){
                return true;
            }
        }
        return false;
    }


    @Override
    public String toString() {
        return "GradeClass{" +
                "mgrade='" + mgrade + '\'' +
                ", mclass='" + mclass + '\'' +
                '}';
    }

 /*   public static void main(String[] args) {
        System.out.println(getMyGrade("0002").toString());
    }*/

}
