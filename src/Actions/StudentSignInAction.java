package Actions;

import Beans.Student;
import Constants.StudentSqlStatement;
import Utils.DB;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by kingwen on 2016/9/13.
 */

@WebServlet(urlPatterns = "/stulogin",name = "o")
public class StudentSignInAction extends HttpServlet {

   private boolean islegal=false;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

        String id=req.getParameter("stu_id");
        String pass=req.getParameter("stu_pass");


        /**
         * 如果登录成功，那么进入主界面
         */
        if(Student.islegal(id,pass)){
           resp.sendRedirect("/");
            Cookie cookie=new Cookie("stu_id",id);
            cookie.setMaxAge(100000000);
            resp.addCookie(cookie);
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }


    }


}
