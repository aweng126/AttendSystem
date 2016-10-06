package Actions;

import Beans.Teaches;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/5.
 */
@WebServlet(urlPatterns = "/adminDeleteTeaches")
public class DeleteTeachesAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String course_id=req.getParameter("course_id");
        String teacher_id=req.getParameter("teacher_id");

        Teaches teaches=new Teaches(course_id,teacher_id);
        teaches.delete();
        resp.getWriter().write(1);
    }


    public static void main(String[] args) {
        Teaches teaches=new Teaches("0001","0001");
        teaches.delete();
    }
}
