package Actions;

import Beans.Teaches;
import Constants.QrConstants;
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
public class TGetChooseCourseQRAction extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String teacher_id= CookieDetail.getTeacherIdFromReq(req);
        String course_id=req.getParameter("course_id");

        Teaches teaches = Teaches.getTeaches(teacher_id,course_id);
        String chooseCourseReq= FormTrans.teachesToStringReq(teaches);

        //得到当前所在目录
        String rootpath=QrConstants.getRootPath(getServletContext().getRealPath(File.separator));

        /**
         * 接下来是生成二维码图片的代码
         */
        String logUri=rootpath+ QrConstants.LOGO_NAME;
        String outFileUri=rootpath+QrConstants.CHOOSECOURSE_NAME;

        try {
            new QRCodeFactory().CreatQrImage(chooseCourseReq, QrConstants.QR_IMG_FORMAT, outFileUri, logUri,QrConstants.QR_IMG_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }

        /**
         * 保存路径的发送
         */
        String ChooseCourseQRPath="resources/imgs/"+QrConstants.CHOOSECOURSE_NAME;
        //返回图片的相对地址
        resp.getWriter().write(ChooseCourseQRPath);

    }

}
