package Utils;

import Beans.Student;
import Beans.Teacher;

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
        String teacher_name=request.getParameter("teacher_id");
        String teacher_pass=request.getParameter("teacher_pass");
        String dept_name=request.getParameter("dept_name");
        teacher=new Teacher(teacher_id,teacher_name,teacher_pass,"0",dept_name);
        return teacher;
    }





}