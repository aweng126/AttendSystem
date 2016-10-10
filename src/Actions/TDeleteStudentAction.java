package Actions;

import Beans.Takes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/6.
 */
@WebServlet(urlPatterns = "/tDeleteStudent")
public class TDeleteStudentAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stu_id=req.getParameter("stu_id");
        String course_id=req.getParameter("course_id");

        int i = Takes.delete(stu_id,course_id);
        resp.setCharacterEncoding("UTF-8");
        if(i==1){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }

   /* public static void main(String[] args) {
        System.out.println(Takes.delete("0007","0002"));
    }*/

}
