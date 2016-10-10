package Actions;

import Beans.AcademicYear;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by kingwen on 2016/10/5.
 */
@WebServlet(urlPatterns = "/adminGetAcademicYearList" )
public class GetAllAcademicYearAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AcademicYear> list=AcademicYear.getAllAcademicYears();
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(JSONArray.fromObject(list).toString());
    }

   /* public static void main(String[] args) {
        List<AcademicYear> list=AcademicYear.getAllAcademicYears();
        System.out.println(JSONArray.fromObject(list).toString());
    }*/

}
