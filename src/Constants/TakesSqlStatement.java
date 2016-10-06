package Constants;

/**
 * Created by kingwen on 2016/10/6.
 * 学生上课takes 关系表格
 */
public class TakesSqlStatement {

    /**
     * 建立表格
     */
    public static final String TEACHES_CREATE="create table takes (stu_id varchar(12),course_id varchar(12),room_id int ,time_id int , acadyear_id int ,score int," +
            "primary key(stu_id,course_id)," +
            "foreign key (stu_id) references student(stu_id)," +
            "foreign key (course_id) references course(course_id)," +
            "foreign key (room_id) references classroom(room_id)," +
            "foreign key (time_id) references  coursetime(time_id)," +
            "foreign key (acadyear_id) references academicyear(acadyear_id)" +
            ")";

    /**
     *
     */



}
