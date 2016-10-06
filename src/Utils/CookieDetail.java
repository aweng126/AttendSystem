package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kingwen on 2016/10/6.
 */
public class CookieDetail {
    public static String getTeacherIdFromReq(HttpServletRequest req){
        String teacher_id=null;
        Cookie[] cookie=req.getCookies();
        for(Cookie cookie1:cookie){
            if(cookie1.getName().equals("teacher_id")){
                teacher_id=cookie1.getValue();
            }
        }
        return teacher_id;
    }
}
