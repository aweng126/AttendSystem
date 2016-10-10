package Utils;

import Constants.OtherConstants;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;

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

 public static String getRedirectFrom(HttpServletRequest req){
     Cookie[] cookie=req.getCookies();
     String from="";
     for(Cookie cookie1:cookie){
         if("from".equals(cookie1.getName())){
             from=cookie1.getValue();
         }
     }

     if("sign".equals(from)){

         return OtherConstants.getUrl(OtherConstants.getSignCourse());

     }else if("choose".equals(from)){
          return OtherConstants.getUrl(OtherConstants.getChooseCourse());
     }else {
         System.out.println("OtherConstants 的 getRedirect sign from ");
         return null;
     }

 }



}
