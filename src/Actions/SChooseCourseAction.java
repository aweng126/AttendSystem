package Actions;

import Beans.Takes;
import Utils.CookieDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/7.
 * 学生选课
 */
@WebServlet(urlPatterns = "/sChooseCourse")
public class SChooseCourseAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stu_id= CookieDetail.getStudentIdFromReq(req);
        String course_id=req.getParameter("course_id");
        int    room_id=Integer.parseInt(req.getParameter("room_id"));
        int  time_id=Integer.parseInt(req.getParameter("time_id"));
        int  acadyear_id=Integer.parseInt(req.getParameter("acadyear"));

        Takes takes=new Takes(stu_id,course_id,room_id,time_id,acadyear_id,0);
        takes.save();
        resp.getWriter().write(1);
    }


}
