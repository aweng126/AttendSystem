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

@WebServlet(urlPatterns = "/requestAttendByWeekBackward1")
public class AdRequestAttendByWeekBackward extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("*****************************");

        String mgrade=req.getParameter("grade_id");
        String mclass=req.getParameter("class_id");
        String course_id=req.getParameter("course_id");

        int firstweek=Integer.parseInt(req.getParameter("display_firstweek"));

        System.out.println("dopost"+mgrade+"  "+mclass+" "+course_id+" "+firstweek);

        List<AttendRate> list=AttendRate.getAttendRateBackward(mgrade,mclass,course_id,firstweek);

        resp.getWriter().write(JSONArray.fromObject(list).toString());

    }
}