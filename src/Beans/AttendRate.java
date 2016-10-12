package Beans;

import Constants.SignSqlStatement;
import Constants.TeacherSqlStatement;
import Utils.DB;
import Utils.TimeUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/10/9.
 * 每周签到的javabean    周次和出勤率
 */
public class AttendRate {
    /**
     * 周次
     */
    private int week;
/**
 * 出勤率
 */
private int attendRate;

    public AttendRate(){}

    public AttendRate(int week, int attendRate) {
        this.week = week;
        this.attendRate = attendRate;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getAttendRate() {
        return attendRate;
    }

    public void setAttendRate(int attendRate) {
        this.attendRate = attendRate;
    }

    /**
     * 显示当前签到率
     * @param gradeid
     * @param classid
     * @param courseid
     * @return
     */
    public static List<AttendRate> getAttendRate(String gradeid,String classid,String courseid ){

        List<Integer> showeek=getShowWeeks(0,0);

        List<AttendRate> list=getAttendRateList(showeek,gradeid,classid,courseid);

        return list;

    }


    /**
     * 向前一个周
     * @param gradeid
     * @param classid
     * @param courseid
     * @param first
     * @return
     */
    public static List<AttendRate> getAttendRateForward(String gradeid,String classid,String courseid,int first ){

        List<Integer> showeek=getShowWeeks(first,-1);

        List<AttendRate> list=getAttendRateList(showeek,gradeid,classid,courseid);

        return list;

    }

    public static List<AttendRate> getAttendRateBackward(String gradeid,String classid,String courseid,int first ){

        List<Integer> showeek=getShowWeeks(first,1);

        List<AttendRate> list=getAttendRateList(showeek,gradeid,classid,courseid);

        return list;

    }




    private static List<AttendRate> getAttendRateList(List<Integer> showeek,String gradeid,String classid,String courseid) {
        List<AttendRate> list=new ArrayList<AttendRate>();
        Connection conn=null;
        ResultSet rs=null;
        PreparedStatement pstmt=null;

        int attendsum=0;
        int choosesum=0;
        try {
            conn= DB.getConnection();

            /**
             * 得到选课总人数
             */
            pstmt=DB.getPStmt(conn,SignSqlStatement.SIGN_CHOOSESUM);
            pstmt.setString(1,gradeid);
            pstmt.setString(2,classid);
            pstmt.setString(3,courseid);
            rs=pstmt.executeQuery();

            if(rs.next()){
                System.out.println(rs.toString());
                choosesum=rs.getInt("choosesum");
            }


            System.out.println("getAttendRate chooseSum"+ choosesum);


            /**
             * 这里是得到签到人数
             */
            pstmt=DB.getPStmt(conn, SignSqlStatement.SIGN_WEEKTIME);
            int currentweek= TimeUtils.getCurrentWeek();
            int bgweek=((currentweek-7)>0)?currentweek-7:1;
            pstmt.setString(2,gradeid);
            pstmt.setString(3,classid);
            pstmt.setString(4,courseid);

            for(int i=bgweek;i<currentweek+1;i++){
                pstmt.setInt(1,i);
                rs=pstmt.executeQuery();

                if(rs.next()){
                    System.out.println(rs.toString());
                    attendsum=rs.getInt("attendsum");
                    int attendRate=0;
                    if(choosesum>0){
                        attendRate=attendsum*100/choosesum;
                    }

                    AttendRate attendRate1=new AttendRate(i,attendRate);
                    list.add(attendRate1);
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

    /**
     * 用于表示展示的周次
     * @param firstshoweek
     * @param way  -1 向前一个周    0从这个位置开始展示  1 向后一个周
     * @return
     */
    private static List<Integer> getShowWeeks(int firstshoweek,int way) {

        List<Integer> lists=new ArrayList<>();

        int currentweek=TimeUtils.getCurrentWeek();

        System.out.println("current"+currentweek);

        switch (way){
            case -1:
                if(firstshoweek<=1&&currentweek<=7){
                lists=getWeekList(1,currentweek);
                }else if(firstshoweek<=1&&currentweek>7){
                    lists=getWeekList(1,7);
                }else if(firstshoweek>1&&(firstshoweek+7<currentweek)){
                    lists=getWeekList(firstshoweek-1,firstshoweek+6);
                }else if(firstshoweek>1&&(firstshoweek+7>currentweek)){
                    lists=getWeekList(firstshoweek-1,currentweek);
                }
                break;
            case 0:
                if(currentweek>7){
                lists=getWeekList(currentweek-7,currentweek);
                }else {
                    lists=getWeekList(1,currentweek);
                }
                break;
            case 1:
                if(firstshoweek+7>=currentweek){
                lists=getWeekList(firstshoweek,currentweek);
                }else if(firstshoweek+7<currentweek){
                    lists=getWeekList(firstshoweek+1,firstshoweek+8);
                }
                break;
        }
        return lists;
    }

    /**
     * 开始周，结束周
     * @param i 开始
     * @param j  结束
     * @return 开始到结束
     */
    private static List<Integer> getWeekList(int i,int j) {

        int last=j;
         List<Integer> list=new ArrayList<>();
        for(int k=i;k<last;k++ ){
            list.add(k);
        }
        return list;
    }




    @Override
    public String toString() {
        return "AttendRate{" +
                "week=" + week +
                ", attendRate=" + attendRate +
                '}';
    }
}
