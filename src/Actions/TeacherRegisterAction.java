package Actions;

import Beans.Teacher;
import Utils.FormTrans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/9/14.
 */
@WebServlet(urlPatterns = "/tregister",name ="0")
public class TeacherRegisterAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        Teacher teacher= FormTrans.getTeacher(req);
        teacher.save();
        System.out.println("teacher save");
        resp.setStatus(200);
    }
}
