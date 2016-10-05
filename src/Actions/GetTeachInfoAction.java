package Actions;

import Beans.TeachInfo;
import net.sf.json.JSONArray;


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
@WebServlet(urlPatterns = "/adminGetTeachInfo")
public class GetTeachInfoAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TeachInfo> list =TeachInfo.getAllTeachInfo();
        resp.getWriter().write(JSONArray.fromObject(list).toString());
    }

    public static void main(String[] args) {
        List<TeachInfo> list =TeachInfo.getAllTeachInfo();
        System.out.println(list);
    }
}
