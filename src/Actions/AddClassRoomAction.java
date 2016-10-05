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


        ClassRoom room=new ClassRoom(building,roomnum);
        room.save();

        resp.getWriter().write(1);
        ClassRoom room1 = new ClassRoom();
    }
    void a(String... s)
    {
    }
    public static void main(String[] args) {
        ClassRoom classRoom = new ClassRoom("ww","tt");
        List<ClassRoom> list = Arrays.asList(new ClassRoom("11","22"),new ClassRoom("33","44"));
    //  Arrays.stream(classRoom.getClass().getMethods()).forEach(e-> e.invoke(classRoom,)));
        System.out.println(JSONArray.fromObject(list));
    }
}
