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
@WebServlet(urlPatterns = "/requsetAttendByWeek")
public class AdRequestAttendByWeek extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

          String mgrade=req.getParameter("sign_grade");
          String mclass=req.getParameter("sign_class");
          String course_id=req.getParameter("course_id");

         List<AttendRate> list=AttendRate.getAttendRate(mgrade,mclass,course_id);
         resp.setCharacterEncoding("UTF-8");
         resp.getWriter().write(JSONArray.fromObject(list).toString());

    }

    public static void main(String[] args) {
        System.out.println(AttendRate.getAttendRate("6","2014","0002"));
    }
}
