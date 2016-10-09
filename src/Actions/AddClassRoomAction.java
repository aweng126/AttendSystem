package Actions;

import Beans.ClassRoom;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kingwen on 2016/10/4.
 */

@WebServlet(urlPatterns = "/adminAddClassRoom" , name = "classroom")
public class AddClassRoomAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.getSession().setAttribute();
        //()req.getSession().getAttribute("");

        String building=req.getParameter("building");
        String roomnum=req.getParameter("roomnum");


        System.out.println(req.getParameter("roomnum"));

        ClassRoom room=new ClassRoom(building,roomnum);
        int i =room.save();

        if(i==1){
            resp.getWriter().write("1");
        }else {
            resp.getWriter().write("0");
        }


    }

}
