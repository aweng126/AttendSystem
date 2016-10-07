package Actions;

import Utils.FormTrans;
import Utils.QRCodeFactory;
import com.google.zxing.WriterException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by kingwen on 2016/10/6.
 */
@WebServlet(urlPatterns = "/tGetSignQR")

public class TGetSignQRAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String course_id=req.getParameter("course_id");

        String rootpath=getServletContext().getRealPath(File.separator)+"/resources/imgs/";

        String signQrContent= FormTrans.courseidToStringReq(course_id);

        System.out.println("signQrContent"+signQrContent);

        /**
         * 接下来是生成二维码图片的代码
         */
        String logUri=rootpath+ "a.png";
        String outFileUri=rootpath+"hello.jpg";
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
        String signQRPath="resources/imgs/hello.jpg";

      //  System.out.println("path"+signQRPath);

        resp.getWriter().write(signQRPath);

    }
}
