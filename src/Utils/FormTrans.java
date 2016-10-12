package Utils;

import Beans.Student;
import Beans.Teacher;
import Beans.Teaches;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by kingwen on 2016/9/13.
 */
public class FormTrans {

    /**
     * 在我们的注册处理界面获得我们的数据，从request中获得我们的表单数据，从而得到我们需要的这个表单
     * @param req 处理注册的HttpServletRequest
     * @return  返回表单提交的表单，student对象
     */
    public static Student getStudent(HttpServletRequest req){
        String stuid=req.getParameter("stu_id");
        String stuname=req.getParameter("stu_name");
        String stupass=req.getParameter("stu_pass");
        String stusex=req.getParameter("stu_sex");
        String stuclass=req.getParameter("stu_class");
        String stugrade=req.getParameter("stu_grade");
        String dept_name= req.getParameter("dept_name");
        Student student=new Student(stuid,stuname,stupass,stusex,stuclass,stugrade,dept_name);
        System.out.print(student.toString());
        return student;
    }

    /**
     * 得到属于我们的老师的信号
     * @param request
     * @return
     */
    public static Teacher getTeacher(HttpServletRequest request){

        Teacher teacher=null;
        String teacher_id=request.getParameter("teacher_id");
        String teacher_name=request.getParameter("teacher_name");
        String dept_name=request.getParameter("dept_name");
        String teacher_isadmin=request.getParameter("isadmin");

        teacher=new Teacher(teacher_id,teacher_name,teacher_isadmin,dept_name);

        return teacher;
    }


    public static final String ip="121.250.222.14";

    /**
     * 将teaches的数据写入到StringReq中
     */
    public static String teachesToStringReq(Teaches teaches){
        String stringReq=ip+":8080/sChooseCourse?course_id="+teaches.getCourse_id()
                +"&room_id="+teaches.getRoom_id()+"&time_id="+teaches.getTime_id() +
                "&acadyear_id="+teaches.getAcadyear_id();
        return  stringReq;
    }


    /**
     * 将teaches的数据写入到StringReq中
     */
    public static String courseidToStringReq(String course_id){

        String stringReq=ip+":8080/sSignCourse?course_id="+course_id;

        return  stringReq;
    }


}
