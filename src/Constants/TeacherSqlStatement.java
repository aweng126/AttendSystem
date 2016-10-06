package Constants;

/**
 * Created by kingwen on 2016/10/5.
 */
public class TeacherSqlStatement {

    /**
     * 老师表格  老师编号  老师姓名  院系  是否为管理员
     */

    public static final String TEACHER_CREATE="create table teacher (" +
            "teacher_id varchar(12) primary key " +
            "teacher_name varchar(12) " +
            "teacher_pass varchar(12) "+
            "dept_name varchar(12) " +
            "teacher_isadmin varchar(1) )" ;

    /**
     * 教师表格的插入界面，老师注册的时候使用
     */
    public static final String TEACHER_INSERT="insert into teacher values(?,?,?,?,?,?)";

    //教师表格的搜索界面，老师登录的时候使用
    public static final String TEACHER_LOGIN="select * from student where teacher_id=? and teacher_pass=?";

    //获得教师的所有数据
    public static final String TEACHER_SEARCHALL="select * from teacher";

    /**
     * 获得老师个人信息
     */
    public  static final String TEACHER_SEARCHWITHID="select * from teacher where teacher_id=?";

    /**
     * 修改密码
     */

    public static final String TEACHER_CHANGEPASS="update teacher set teacher_pass = ? where teacher_id = ? ";

}
