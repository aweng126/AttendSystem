package Actions;

import Beans.Student;
import Utils.FormTrans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/9/13.
 */

@WebServlet(urlPatterns = "/sturegister" , name = "register")
public class StudentRegisterAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在表单中获得我们的注册的student数据，然后插入到我们对应的数据库中去

  /*      String stuid=req.getParameter("stu_id");
        System.out.println("stuid="+stuid);*/

        Student student = FormTrans.getStudent(req);
        int i=student.save();
        if(i==1){
            Cookie idcookie=new Cookie("stu_id",student.getStuid());
            Cookie passcookie=new Cookie("stu_pass",student.getStupass());
            idcookie.setMaxAge(60*60*24*30);
            passcookie.setMaxAge(60*60*24*30);
            resp.addCookie(idcookie);
            resp.addCookie(passcookie);
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }
    }

}
