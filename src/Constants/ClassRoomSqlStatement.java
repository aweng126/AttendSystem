package Constants;

/**
 * Created by kingwen on 2016/10/5.
 */
public class ClassRoomSqlStatement {

    /**
     * 教室表格
     */
    public static final String CLASSROOM_CREATE="create table classroom ( " +
            "room_id int auto_increment primary key " +
            "building varchar（12）not null " +
            "roomnum varchar(6) not null" +
            ")";

    /**
     *添加教室
     */

    public static final String CLASSROOM_INSERT="insert into classroom value(" +
            "null,?,?)";


    /**
     * 选择教室
     */
    public static final String CLASSROOM_SEARCHALL="select * from classroom";
}
