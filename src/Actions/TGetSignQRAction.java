package Actions;

import Constants.QrConstants;
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String course_id=req.getParameter("course_id");
        String rootpath=QrConstants.getRootPath(getServletContext().getRealPath(File.separator));
        String signQrContent= FormTrans.courseidToStringReq(course_id);

        /**
         * 接下来是生成二维码图片的代码
         */
        String logUri=rootpath+ QrConstants.LOGO_NAME;
        String outFileUri=rootpath+QrConstants.SIGN_NAME;
        try {
            new QRCodeFactory().CreatQrImage(signQrContent, QrConstants.QR_IMG_FORMAT, outFileUri, logUri,QrConstants.QR_IMG_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        /**
         * 保存路径的发送
         */
        String signQRPath=QrConstants.getQrPath(QrConstants.SIGN_NAME);
        System.out.println("signQrPath"+signQRPath);
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(signQRPath);

    }
}
