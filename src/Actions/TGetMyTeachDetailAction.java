package Actions;

import Beans.DetailCourseInfo;
import Utils.CookieDetail;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/6.
 */

@WebServlet(urlPatterns = "/tGetMyTeachDetail")
public class TGetMyTeachDetailAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacher_id= CookieDetail.getTeacherIdFromReq(req);
        String course_id=req.getParameter("course_id");

        DetailCourseInfo detailCourseInfo=DetailCourseInfo.getDetailCourseInfo(teacher_id,course_id);
        resp.getWriter().write(JSONArray.fromObject(detailCourseInfo).toString());
    }

    public static void main(String[] args) {
        System.out.println(DetailCourseInfo.getDetailCourseInfo("0001","0002"));
    }

}
