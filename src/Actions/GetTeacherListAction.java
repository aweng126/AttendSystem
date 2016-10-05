package Actions;

import Beans.Teacher;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kingwen on 2016/10/5.
 */
@WebServlet(urlPatterns = "/adminGetTeacherList")
public class GetTeacherListAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Teacher> list=Teacher.getTeachers();
        resp.getWriter().write(JSONArray.fromObject(list).toString());

    }
}
