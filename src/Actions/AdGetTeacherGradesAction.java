package Actions;

import Beans.GradeClass;
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
 * 通过教师号得到教师所教授的班级号
 */
@WebServlet(urlPatterns = "/adminGetMyGrades")
public class AdGetTeacherGradesAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teacher_id=req.getParameter("teacher_id");

       List<GradeClass> mgc=GradeClass.getMyGrade(teacher_id);
       resp.getWriter().write(JSONArray.fromObject(mgc).toString());

    }
}
