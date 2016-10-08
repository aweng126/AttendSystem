package Actions;

import Beans.Teaches;
import Utils.CookieDetail;
import Utils.FormTrans;
import Utils.QRCodeFactory;
import com.google.zxing.WriterException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/6.
 * 选课二维码生成并返回
 */

@WebServlet(urlPatterns = "/tGetChooseCourseQR")
public class TGetChooseCourseQR extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String teacher_id= CookieDetail.getTeacherIdFromReq(req);
        String course_id=req.getParameter("course_id");

        Teaches teaches = Teaches.getTeaches(teacher_id,course_id);
        /**
         * 接下来是teachers的拼接过程，用于学生选课的req
         */
        String chooseCourseReq= FormTrans.teachesToStringReq(teaches);

        //得到当前所在目录
        String rootpath=getServletContext().getRealPath(File.separator)+"/resources/imgs/";
        //选课url的设定
        String signQrContent= FormTrans.courseidToStringReq(course_id);

        //System.out.println("signQrContent"+signQrContent);

        /**
         * 接下来是生成二维码图片的代码
         */
        String logUri=rootpath+ "a.png";
        String outFileUri=rootpath+"chooseCourse.jpg";
        int[]  size=new int[]{430,430};
        String format = "jpg";

        try {
            new QRCodeFactory().CreatQrImage(signQrContent, format, outFileUri, logUri,size);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /**
         * 保存路径的发送
         */
        String ChooseCourseQRPath="resources/imgs/chooseCourse.jpg";
        //返回图片的相对地址
        resp.getWriter().write(ChooseCourseQRPath);

    }

}
