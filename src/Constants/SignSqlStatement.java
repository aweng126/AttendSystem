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
  public static  final  String SIGN_INSERT="insert into sign value(null,?,?,?)";

}
