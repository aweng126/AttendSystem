package Constants;

/**
 * Created by kingwen on 2016/10/5.
 * 分配课程表格，teaches
 */
public class TeachesSqlStatement {

    /**
     * teaches 建立表格语句
     */
    public static final String TEACHES_CREATE="create table teaches (teacher_id varchar(12),course_id varchar(12),room_id int ,time_id int , acadyear_id int " +
            "primary key(teacher_id,course_id)" +
            "foreign key (teacher_id) references teacher(teacher_id)" +
            "foreign key (course_id) references course(course_id)" +
            "foreign key (room_id) references classroom(room_id)" +
            "foreign key (time_id) references  couresetime(time_id)" +
            "foreign key (acadyear_id) references academicyear(acadyear_id)" +
            ")";


    /**
     * 插入表格
     */
     public static final String TEACHES_INSERT="insert into teaches value(?,?,?,?,?)";


    /**
     * 查询所有教课信息
     */

     public static final String TEACHES_SEARCHALL="select * from teaches natural join teacher natural join course natural join classroom ";

}
