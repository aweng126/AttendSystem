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
@WebServlet(urlPatterns = "/requestAttendByWeekForward")
public class AdRequestAttendByWeekForward extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String mgrade=req.getParameter("sign_grade");
        String mclass=req.getParameter("sign_class");
        String course_id=req.getParameter("course_id");
        int firstweek=Integer.parseInt(req.getParameter("display_firstweek"));

        List<AttendRate> list=AttendRate.getAttendRateForward(mgrade,mclass,course_id,firstweek);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSONArray.fromObject(list).toString());

    }
}
