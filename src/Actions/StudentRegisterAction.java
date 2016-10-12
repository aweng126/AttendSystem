package Actions;

import Beans.Student;
import Constants.OtherConstants;
import Utils.CookieDetail;
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


        Student student = FormTrans.getStudent(req);
        int i=student.save();

        System.out.println("i==1"+(1==i));

        //System.out.println("register"+i);
        if(i==1){

            System.out.println("注册成功");
            //resp.getWriter().write("1");

            Cookie idcookie=new Cookie("stu_id",student.getStuid());

            idcookie.setMaxAge(60*60*24*30);
            resp.addCookie(idcookie);

            resp.getWriter().write("注册成功，请重新扫描二维码");


        }else if(i==0) {
            System.out.println("学生注册失败");
            resp.getWriter().write("0");
        }
    }

}
