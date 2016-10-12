package Actions;

import Beans.AttendRate;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kingwen on 2016/10/9.
 */
@WebServlet(urlPatterns = "/requestAttendByWeek")
public class AdRequestAttendByWeek extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

          String mgrade=req.getParameter("grade_id");
          String mclass=req.getParameter("class_id");
          String course_id=req.getParameter("course_id");

        System.out.println("requsetAttendByWeek"+mgrade+"******"+mclass+"*******    "+course_id);

         List<AttendRate> list=AttendRate.getAttendRate(mgrade,mclass,course_id);
         //resp.setCharacterEncoding("UTF-8");
         resp.getWriter().write(JSONArray.fromObject(list).toString());

    }

    public static void main(String[] args) {
        AttendRate.getAttendRate("6","2014","0002").toString();
    }
}
