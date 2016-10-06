package Actions;

import Beans.Teacher;
import Utils.CookieDetail;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/6.
 *
 * 教师更改密码
 */
@WebServlet(urlPatterns = "/tChangePassword")
public class TChangePassAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
     String teacher_id= CookieDetail.getTeacherIdFromReq(req);
        String pass=req.getParameter("teacher_newpass");
        int id=Teacher.changePass(teacher_id,pass);
        if(id==1){
            resp.getWriter().write(1);
        }else {
            resp.getWriter().write(0);
        }
    }
/*
    public static void main(String[] args) {
        System.out.println(Teacher.changePass("0001","0000"));
    }*/
}
