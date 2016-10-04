package Actions;

import Beans.ClassRoom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/4.
 */

@WebServlet(urlPatterns = "/adminAddClassRoom" , name = "classroom")
public class AddClassRoomAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String building=req.getParameter("building");
        String roomnum=req.getParameter("roomnum");


        ClassRoom room=new ClassRoom(building,roomnum);
        room.save();
        resp.getWriter().write(1);

    }
}
