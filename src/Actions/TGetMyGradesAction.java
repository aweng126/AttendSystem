package Actions;

import Beans.GradeClass;
import Utils.CookieDetail;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kingwen on 2016/10/10.
 */
@WebServlet(urlPatterns = "/tGetMyGrades")
public class TGetMyGradesAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teacher_id= CookieDetail.getTeacherIdFromReq(req);
        String course_id=req.getParameter("course_id");


        List<GradeClass> mgc=GradeClass.getMyGrade(teacher_id,course_id);
        resp.setCharacterEncoding("UTF-8");
        System.out.println("tGetMyGrades"+mgc.toString());
        resp.getWriter().write(JSONArray.fromObject(mgc).toString());

    }

    public static void main(String[] args) {
        System.out.println(GradeClass.getMyGrade("0001","0002"));
    }

}
