package Actions;

import Beans.Course;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/4.
 */
@WebServlet(urlPatterns = "/adminAddCourse" , name = "addcourse")
public class AddCourseAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String course_id=req.getParameter("course_id");
        String course_name=req.getParameter("course_name");
        int course_credit=Integer.parseInt(req.getParameter("course_credit"));

        if(!("".equals(course_credit)||"".equals(course_id)||"".equals(course_name))){
            Course course=new Course(course_id,course_name,course_credit);
            course.save();
            resp.getWriter().write(1);
        }else{
            resp.getWriter().write(0);
        }
    }

    public  static  void main (String args[]){
        Course course=new Course("00003","operator",4);
        course.save();
    }
}
