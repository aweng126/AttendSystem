package Actions;

import Beans.Course;
import Beans.Teacher;
import Utils.CookieDetail;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kingwen on 2016/10/6.
 * 老师查看个人教课信息
 */
@WebServlet(urlPatterns = "/tGetMyTeach")
public class TGetMyTeachAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teacher_id = CookieDetail.getTeacherIdFromReq(req);

        List<Course>  courses = Teacher.getMyTeach(teacher_id);

        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSONArray.fromObject(courses).toString());

    }

    public static void main(String[] args) {
        System.out.println( Teacher.getMyTeach("0001").toString());
    }
}
