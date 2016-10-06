package Utils;

import com.google.zxing.WriterException;

import java.io.File;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/6.
 */
public class QrTest {
    public static void main(String[] args) {

        String content="http://pan.baidu.com/s/1qXR0AfE";
        String logUri="C:\\Users/kingwen/Desktop" + File.separator + "150c900.png";
        String outFileUri="C:\\Users/kingwen/Desktop" + File.separator + "hello.jpg";
        int[]  size=new int[]{430,430};
        String format = "jpg";

        try {
            new QRCodeFactory().CreatQrImage(content, format, outFileUri, logUri,size);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (WriterException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
