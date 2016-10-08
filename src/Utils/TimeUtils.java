package Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by kingwen on 2016/10/7.
 *  用于生成当前时间，生成签到时间
 */
public class TimeUtils {

    /**
     * 工具类：得到当前的时间，timestamp格式
     * @return
     */
    public  static Timestamp getCurrentTimeStamp(){
        java.util.Date date = new java.util.Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        System.out.println(timeStamp);
        return timeStamp;
    }


    /**
     * 返回签到的周次 默认开学是9月7号  第37周
     * @param timestamp 签到时间
     * @return  签到的周次，比如说 1 2
     */
    public static int getWeekByData(Timestamp timestamp){
          int beginWeekOfYear=37;
          int weekOfYear=0;
        try {
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date=sdf.parse(timestamp.toString());

            Calendar calendar=Calendar.getInstance();
            calendar.setTime(date);
            weekOfYear=calendar.get(Calendar.WEEK_OF_YEAR);
            System.out.println("weekofyear:"+weekOfYear);
        } catch (ParseException e) {
            e.printStackTrace();
        }
     return  weekOfYear-beginWeekOfYear;
    }


    public static void main(String[] args) {
        /**
         * 测试，用于展示当前是本学期第几周
         */
        Timestamp now = TimeUtils.getCurrentTimeStamp();
        System.out.println( TimeUtils.getWeekByData(now));
    }

}
