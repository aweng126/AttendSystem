package Actions;

import Beans.AcademicYear;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by kingwen on 2016/10/4.
 */
@WebServlet(urlPatterns = "/adminAddAcademicYear" , name = "academicyear")
public class AddAcacemicYearAction extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String ecadyear=req.getParameter("acadyear");
        String term=req.getParameter("term");

        boolean islegal=false;

        /**
         * 必须是符合格式  2014-2015   1 这种格式才能插入数据
         */
       if(ecadyear.trim().length()==9&&(ecadyear.charAt(4)=='-')){
           if(Integer.parseInt(term)==1||Integer.parseInt(term)==2){
               islegal=true;
               AcademicYear myear=new AcademicYear(ecadyear,term);
               myear.saveYear();
           }
        }
        if(islegal){
            resp.getWriter().write("1");
        }else{
            resp.getWriter().write("0");
        }

    }

    public static void main(String args[]){

        AcademicYear academicYear=new AcademicYear("2012-2013","2");
        academicYear.saveYear();

    }
}
