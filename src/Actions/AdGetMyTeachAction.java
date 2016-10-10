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
 * Created by kingwen on 2016/10/9.
 */
@WebServlet(urlPatterns = "/adminGetMyTeach")
public class AdGetMyTeachAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teacher_id = req.getParameter("teacher_id");
        List<Course> courses = Teacher.getMyTeach(teacher_id);

        System.out.println("adminGetTeach :"+"teacher_id="+teacher_id+"courses"+courses.toString());
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSONArray.fromObject(courses).toString());

    }
}
