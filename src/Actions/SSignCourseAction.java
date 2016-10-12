package Actions;

import Beans.Sign;
import Beans.Student;
import Constants.OtherConstants;
import Utils.CookieDetail;
import Utils.TimeUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
        if("".equals(stu_id)){
            Cookie cookie=new Cookie("from","sign");
            resp.addCookie(cookie);
            resp.sendRedirect(OtherConstants.getStudentRegisterUrl());
        }else {

            Student student=Student.getStudentById(stu_id);
            String course_id=req.getParameter("course_id");
            int j=Sign.save(student,course_id);
            resp.setCharacterEncoding("UTF-8");
            if(j==1){
                resp.sendRedirect("signSuccess.html");
            }else{
                resp.getWriter().write("签到失败");
            }
        }
    }

}
