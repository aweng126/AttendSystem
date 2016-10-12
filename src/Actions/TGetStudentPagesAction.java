package Actions;

import Beans.Teacher;
import Utils.CookieDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/12.
 */
@WebServlet(urlPatterns = "/tGetStudentPages")
public class TGetStudentPagesAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String course_id =req.getParameter("course_id");

        int i= Teacher.getStudentPages(course_id);

        resp.getWriter().write(String.valueOf(i));

    }

}
