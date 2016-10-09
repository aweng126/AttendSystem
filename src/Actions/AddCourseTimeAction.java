package Actions;

import Beans.CourseTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/4.
 */
@WebServlet(urlPatterns = "/adminAddCourseTime", name = "addCourseTime")
public class AddCourseTimeAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean success=false;

        String week=req.getParameter("time_week");
        String order=req.getParameter("time_order");
        int intweek=Integer.parseInt(week);
        int intorder=Integer.parseInt(order);
        if(intweek <= 7 && intweek>=1 && intorder>0&& intorder<=10 ){
            CourseTime time=new CourseTime(week,order);
            time.save();
            success=true;
        }else {
            success=false;
        }

        if(success){
            resp.getWriter().write("1");
        }else{
            resp.getWriter().write("0");
        }

    }

}
