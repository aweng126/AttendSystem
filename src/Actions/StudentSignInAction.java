package Actions;

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
 * Created by kingwen on 2016/9/13.
 */

@WebServlet(urlPatterns = "/stusignin",name = "o")
public class StudentSignInAction extends HttpServlet {

   private boolean islegal=false;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);

         //得到cookie从而判断我们的用户名和密码
        Cookie cookie[]=req.getCookies();
        String id ="";
        String pass="";
        //将cookie进行打印
        for(Cookie i:cookie){
            switch(i.getName().toString().trim()){
                case "stu_id":
                    id=i.getValue();
                    break;
                case "stu_pass":
                    pass=i.getValue();
                    break;
                default:
                    //donothing
            }
            System.out.print(i.toString().trim());
        }

        /**
         * 如果登录成功，那么进入主界面
         */
        if(islegal(id,pass)){
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
            pstmt=DB.getPStmt(conn, SqlStatement.STUDENT_SEARCH) ;
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
