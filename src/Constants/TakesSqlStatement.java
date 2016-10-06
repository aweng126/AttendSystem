package Constants;

/**
 * Created by kingwen on 2016/10/6.
 * 学生上课takes 关系表格
 */
public class TakesSqlStatement {

    /**
     * 建立表格
     */
    public static final String TAKES_CREATE="create table takes (stu_id varchar(12),course_id varchar(12),room_id int ,time_id int , acadyear_id int ,score int," +
            "primary key(stu_id,course_id)," +
            "foreign key (stu_id) references student(stu_id)," +
            "foreign key (course_id) references course(course_id)," +
            "foreign key (room_id) references classroom(room_id)," +
            "foreign key (time_id) references  coursetime(time_id)," +
            "foreign key (acadyear_id) references academicyear(acadyear_id)" +
            ")";

    /**
     * 删除非正常选课的同学
     */
    public static final String TAKES_DELETE="delete from takes where stu_id=? and course_id=?";

    /**
     * 插入一条数据
     */
   public static  final String TAKES_INSERT="insert into takes values(?,?,?,?,?,null)";

    /**
     * 更新成绩
     */

    public static final String TAKES_ADDSCORE="update takes set score = ? where stu_id=? and course_id=? ";


}
