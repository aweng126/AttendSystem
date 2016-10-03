package Constants;

/**
 * Created by kingwen on 2016/9/11.
 * 本类用于存储对应的sql语句
 */
public class SqlStatement {

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
  public static final String STUDENT_SEARCH="select * from student where stu_id=? and stu_pass=?";


    /**
     * 课程表 课程号 课程名 学分 开始周  结束周  上课时间  每周上课次数 老师
     */
    public  static final String CLASS_CREATE="create table class " +
            "class_id varchar(12) primary key" +
            "class_name varchar(20)" +
            "class_credit varchar(20)" +
            "class_beginweek varchar(3)" +
            "class_endweek varchar(3)" +
            "class_time varchar(2)" +
            "class_pweek varchar(2)" +
            "teacher_id varchar(12) " ;

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
    public static final String TEACHER_INSERT="insert table teacher values(?,?,?,?,null)";

    //教师表格的搜索界面，老师登录的时候使用
    public static final String TEACHER_SEARCH="select * from student where teacher_id=? and teacher_pass=?";




    /**
     * 院系表格
     */
    //建表
    public static final String DEPARTMENT_CREATE="create table department" +
            "dept_name varchar(12) primary key" ;


    /**
     * 选课
     */
    public static final String CHOOSECLASS_CREATE="create table chooseclss" +
            "stu_id varchar(12) primary key" +
            "class_id varchar(12)" +
            "score numeric(8,2)" +
            "foreign key(stu_id) reference student" +
            "foreign key(class_id) reference class";

    /**
     * 签到表格..时间格式需要考虑一下
     */
    public static  final String SIGN_CREATE="create table sign" +
            "stu_id varchar(12) primary key" +
            "class_id varchar(12)" +
            "sign_time Data()" +
            "sign_week varchar(3)" +
            "foreign key(stu_id) reference student" +
            "foreign key(class_id) reference class";


    /**
     * 建立学年信息表格
     */
    public static final String ACADEMICYEAR_CREATE="create table academicyear (" +
            "ecadyear_id  int auto_increment primary key ," +
            "ecadyear varchar(12) not null, " +
            "term varchar(2) not null )";

    /**
     * 将学年信息插入数据库
     */
    public static  final String ACADEMICYEAR_INSERT="insert into academicyear " +
            "value(null,?,?)";

}
