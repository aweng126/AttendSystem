package Constants;

/**
 * Created by kingwen on 2016/10/4.
 * 关于course 表格需要的sql语句
 */
public class CourseSqlStatement {
    /**
     * 建表语句
     */
    public static final String COURSE_CREATE="create table course(" +
            " course_id varchar(12) primary key," +
            " course_name varchar(24)  " +
            " course_credit int  ";

    /**
     * 插入语句
     */
 public static final String COURSE_INSERT="insert into course value(?,?,?)";


    /**
     * 选择课程号
     */
    public static  final String COURSE_SEARCHAll="select * from course";



}
