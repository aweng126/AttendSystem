package Actions;

import Beans.Teacher;
import Beans.Teaches;
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
 * Created by kingwen on 2016/10/6.
 */
@WebServlet(urlPatterns = "/tGetInfo")
public class GetTeacherInfo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacher_id=null;
        Cookie[] cookie=req.getCookies();
        for(Cookie cookie1:cookie){
            if(cookie1.getName().equals("teacher_id")){
                 teacher_id=cookie1.getValue();
            }
        }

      List<Teacher> theTeacher= Teacher.getTeacherInfo(teacher_id);

        resp.getWriter().write(JSONArray.fromObject(theTeacher).toString());
    }

  /*  public static void main(String[] args) {

        System.out.println(Teacher.getTeacherInfo("0001"));

    }*/
}
