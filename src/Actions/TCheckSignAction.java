package Actions;

import Beans.CheckSign;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kingwen on 2016/10/7.
 */
@WebServlet(urlPatterns = "/tCheckStudentSign")
public class TCheckSignAction extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String stu_id=req.getParameter("stu_id");
        String course_id=req.getParameter("course_id");

        List<CheckSign> list=CheckSign.getSigns(stu_id,course_id);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSONArray.fromObject(list).toString());

    }
/*
    public static void main(String[] args) {
        System.out.println(CheckSign.getSigns("0001","0002").toString());
    }*/

}
