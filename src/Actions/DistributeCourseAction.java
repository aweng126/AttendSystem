package Actions;

import Beans.CourseTime;
import Beans.Teaches;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/5.
 */
@WebServlet(urlPatterns ="/adminDistributeCourse" )
public class DistributeCourseAction extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("distribute");

        String teacher_id=req.getParameter("teacher_id");
        System.out.println("teacher_id"+teacher_id);
        String course_id=req.getParameter("course_id");
        System.out.println("course_id"+course_id);
        int room_id=Integer.parseInt(req.getParameter("room_id"));
        System.out.println("room_id"+room_id);
        String time_week=req.getParameter("time_week");
        System.out.println("time_week"+time_week);
        String time_order=req.getParameter("time_order");
        System.out.println("time_order"+time_order);
        int time_id= CourseTime.getTimeId(time_week,time_order);
        System.out.println("time_id"+time_id);
        int academic_id=Integer.parseInt(req.getParameter("academic_id"));
        System.out.println("academic_id"+academic_id);


        System.out.println("teacher_id"+teacher_id);
        Teaches teaches=new Teaches(teacher_id,course_id,room_id,time_id,academic_id);


        System.out.println("teaches"+teaches.toString());

        int i=teaches.save();

        System.out.println("distribute "+i);
        resp.setCharacterEncoding("UTF-8");
        if(i==1){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }

  /*  public static void main(String[] args) {
        Teaches teaches=new Teaches("0002","0001",2,4,1);
        teaches.save();
    }*/


}
