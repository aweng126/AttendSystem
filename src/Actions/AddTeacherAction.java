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
@WebServlet(urlPatterns = "/adminAddTeacher")
public class AddTeacherAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Teacher teacher= FormTrans.getTeacher(req);

        int i=teacher.save();
        resp.setCharacterEncoding("UTF-8");

        if(i==1){
            resp.getWriter().write("2");
        }else {
            resp.getWriter().write("0");
        }

    }

 /*   public static void main(String[] args) {
        Teacher teacher=new Teacher();
        teacher.setTeacher_id("0002");
        teacher.setDept_name("软件工程");
        teacher.setTeacher_isadmin("0");
        teacher.setTeacher_name("张三");
        System.out.println(teacher.toString());
        teacher.save();
    }*/

}
