package Constants;

/**
 * Created by kingwen on 2016/10/5.
 */
public class StudentSqlStatement {
    /**
     * 关于student表格
     */
    public static final String STUDNET_CREATE=
            "creat table student " +
                    "stu_id varchar(12) primary key " +
                    "stu_name varchar(10) not null " +
                    "stu_pass varchar(10) not null " +
                    "stu_sex varchar(10) not null " +
                    "stu_class varchar(10) not null " +
                    "stu_grade varchar(10) not null " +
                    "dept_name varchar(10) not null ";

    public static void main(String[] args) {
        System.out.print(STUDNET_CREATE);
    }

    /**
     * student插入
     */
    public static final String STUDENT_INSERT="insert into student values(?,?,?,?,?,?,?)";

    /**
     * student登录
     */
    public static final String STUDENT_LOGIN="select * from student where stu_id=? and stu_pass=?";

    /**
     * 搜索所有的学生
     */
    public static final String STUDENT_SEARCHALL="select * from student";


    /**
     * 搜索学生班级和年级和专业
     */
    public static final String STUDENT_SEARCH_WITHGCD="select * from student where stu_grade=? and stu_clss=? and dept_name=?";

}
