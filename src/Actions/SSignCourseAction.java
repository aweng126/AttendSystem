package Actions;

import Beans.Sign;
import Utils.CookieDetail;
import Utils.TimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by kingwen on 2016/10/7.
 * 学生签到
 */
@WebServlet(urlPatterns = "/sSignCourse")
public class SSignCourseAction extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stu_id = CookieDetail.getStudentIdFromReq(req);
        String course_id=req.getParameter("course_id");

       Timestamp now = TimeUtils.getCurrentTimeStamp();

       int i=Sign.save(stu_id,course_id,now);
        if(i==1){
            resp.getWriter().write(1);
        }else{
            resp.getWriter().write(0);
        }
    }

    /*public static void main(String[] args) {
        Timestamp now = TimeUtils.getCurrentTimeStamp();
        System.out.println(Sign.save("0001","0002",now));
    }*/
}
