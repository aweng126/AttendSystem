package Constants;

/**
 * Created by kingwen on 2016/10/6.
 */
public class QrConstants {

    public static  int[]  QR_IMG_SIZE=new int[]{430,430};
    public static String QR_IMG_FORMAT = "jpg";

    public static String LOGO_NAME="attend.jpg";
    /**
     * 签到二维码
     */
    public static String SIGN_NAME="signQrImg."+QR_IMG_FORMAT;
    /**
     * 选课二维码
     */
    public static String CHOOSECOURSE_NAME="chooseQrImg"+QR_IMG_FORMAT;

    public static String getQrPath(String name){
        return "resources/imgs/"+name;
    }

    public static String getRootPath(String realPath){
        return realPath+"/resources/imgs/";
    }

}
