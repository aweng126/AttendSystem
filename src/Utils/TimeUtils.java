package Utils;

import java.sql.Timestamp;

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

}
