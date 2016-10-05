package Constants;

/**
 * Created by kingwen on 2016/9/11.
 * 本类用于存储对应的sql语句
 */
public class SqlStatement {




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
     * 将学年信息插入数据库
     */
    public static  final String ACADEMICYEAR_INSERT="insert into academicyear " +
            "value(null,?,?)";


    /**
     * 课程时间的表格 包含周次和节次
     */
    public static final String COURSETIME_CREATE="creat table coursetime（ " +
            " time_id int auto_increment primary key ," +
            "time_week varchar(2) not null ," +
            "time_order varchar(2) not null," +
            " foreign key (time_order) reference ordertime(order_id)" +
            "）";
    /**
     * 上课时间进行添加
     */
    public static final String COURSETIME_INSERT="insert into coursetime" +
            " value(null,?,?)";





    /**
     * 建立ordertime表格并进行数值的设定
     */
    public static final String ORDERTIME_CREATE="CREATE TABLE ordertime" +
            "(order_id VARCHAR(2) PRIMARY KEY ,real_time VARCHAR(12) NOT NULL)";

    public static final String ORDERTIME_INSERT1="INSERT INTO ordertime VALUE('1','8:00——9:50')";
    public static final String ORDERTIME_INSERT2= "INSERT INTO ordertime VALUE('2','10:10——12:00')";
    public static final String ORDERTIME_INSERT3= "INSERT INTO ordertime VALUE('3','13:30——15:20')";
    public static final String ORDERTIME_INSERT4= "INSERT INTO ordertime VALUE('4','15:40——17:30')";
    public static final String ORDERTIME_INSERT5=  "INSERT INTO ordertime VALUE('5','18:30——20:20')";




}
