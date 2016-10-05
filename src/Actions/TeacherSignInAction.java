package Actions;

import Constants.TeacherSqlStatement;
import Utils.DB;
import Constants.SqlStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet(urlPatterns = "teachersignin",name = "0")
public class TeacherSignInAction extends HttpServlet {
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
            resp.sendRedirect("/");
            resp.sendRedirect(req.getContextPath() + "/index.html");
        }
    }


    /**
     * 登录注册界面，用于检测我们的用户名和密码
     * （其实这个是完全没有必要的，我们的cookie都是我们自己放的，然后自己获得就好，根本没有什么必要说这里没有）
     * @param id   学生的账号
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
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(DB.getRestultSetSize(rs)==1) {
            return true;
        }else
            return false;
    }


}
