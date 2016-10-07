package Utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by kingwen on 2016/10/6.
 */
public class CookieDetail {

    /**
     * 通过req请求获得我们的teacher_id
     * @param req 对应的req请求
     * @return
     */
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


    /**
     * 通过req请求获得我们的 stu_id
     * @param req
     * @return
     */
 public  static String getStudentIdFromReq(HttpServletRequest req){
     String stu_id=null;
     Cookie[] cookie=req.getCookies();
     for(Cookie cookie1:cookie){
         if(cookie1.getName().equals("stu_id")){
             stu_id=cookie1.getValue();
         }
     }
     return stu_id;
 }
}
