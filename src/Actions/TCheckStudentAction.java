package Actions;

import Beans.Student;
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
 * Created by kingwen on 2016/10/6.
 */
@WebServlet(urlPatterns = "/tCheckStudent")
public class TCheckStudentAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String course_id=req.getParameter("course_id");
        int page=Integer.parseInt(req.getParameter("page_id"));

        System.out.println("tCheckStudent"+course_id+"  "+page);

        List<Student> students= Teacher.getStudentsWithCourse(course_id,page);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSONArray.fromObject(students).toString());

    }

    public static void main(String[] args) {
        System.out.println(Teacher.getStudentsWithCourse("0002",1));
    }

}
