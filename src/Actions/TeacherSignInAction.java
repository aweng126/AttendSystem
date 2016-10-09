package Actions;

import Beans.Teacher;
import Constants.TeacherSqlStatement;
import Utils.DB;
import Constants.SqlStatement;

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
 * Created by kingwen on 2016/9/14.
 */
@WebServlet(urlPatterns = "tlogin",name = "0")
public class TeacherSignInAction extends HttpServlet {

    private String misadmin="0";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teacher_id = req.getParameter("teacher_id");
        String teacher_pass = req.getParameter("teacher_pass");

        /**
         * admin 返回值代表含义
         * -1：登录失败
         * 0： 普通老师登录
         * 1：教务老师登录
         */
        int admin = Teacher.islegal(teacher_id, teacher_pass);
        switch (admin) {
            case -1:
                resp.getWriter().write("0");
                break;
            case 0:
                Cookie cookie = new Cookie("teacher_id", teacher_id);
                resp.addCookie(cookie);
                resp.getWriter().write("1");
                break;
            case 1:
                resp.getWriter().write("2");
                break;
        }
    }
}
