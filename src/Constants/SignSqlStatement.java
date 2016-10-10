package Constants;

/**
 * Created by kingwen on 2016/10/6.
 */
public class SignSqlStatement {
    /**
     * 签到表格的建表语句
     */
    public static final String SIGN_CREATE="create table sign( sign_id  int auto_increment primary key ," +
            "stu_id varchar(12),course_id varchar(12),signtime timestamp)";

    /**
     * 签到表格的插入语句
     */
  public static  final  String SIGN_INSERT="insert into sign value(null,?,?,?,?,?,?)";


    /**
     *查询签到情况
     */
   public static final String SIGN_SEARCH="select * from sign natural join student where stu_id=? and course_id=?";


    /**
     * 查看签到情况  本周签到人数
     */
    public static final String SIGN_WEEKTIME="select count(*) as attendsum from sign where sign_week=? and sign_grade=? and sign_class=? and course_id=?";

    /**
     * 查看班级选课总人数
     */
public static  final String SIGN_CHOOSESUM = "select count(*) as choosesum from student natural join takes where stu_grade=? and stu_class=? and course_id=?";

}
