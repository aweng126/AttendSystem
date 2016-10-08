package Actions;

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
        //得到cookie从而判断我们的用户名和密码

        String teacher_id=req.getParameter("teacher_id");
        String teacher_pass=req.getParameter("teacher_pass");


        /**
         * 如果登录成功，那么进入主界面
         */
        if(islegal(teacher_id,teacher_pass)){

            resp.getWriter().write(1);
            /**
             * 将教师的id和权限放到cookie中去，方便之后的使用
             */
            Cookie cookie=new Cookie("teacher_id",teacher_id);
            Cookie cookie1=new Cookie("teacher_isadmin",misadmin);
            cookie.setMaxAge(1000000000);
            resp.addCookie(cookie);
            resp.addCookie(cookie1);

            resp.sendRedirect("/");
            resp.sendRedirect(req.getContextPath() + "/index.html");



        }else {
            resp.getWriter().write(0);
        }
    }


    /**
     * 登录注册界面，用于检测我们的用户名和密码
     * @param id   老师的账号
     * @param pass  登录的密码
     * @return      返回是否存在
     */
    private boolean islegal(String id, String pass) {
        Connection conn=null;
        PreparedStatement pstmt = null;
        ResultSet rs=null;
        try {
            conn= DB.getConnection();
            pstmt=DB.getPStmt(conn, TeacherSqlStatement.TEACHER_LOGIN) ;
            pstmt.setString(1, id);
            pstmt.setString(2, pass);
            rs=pstmt.executeQuery();

            while (rs.next()){
              //得到是否是管理员
               misadmin=rs.getString("teacher_isadmin");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DB.closeStmt(pstmt);
            DB.closeConn(conn);
        }

        if(DB.getRestultSetSize(rs)==1) {
            return true;
        }else
            return false;
    }


}
