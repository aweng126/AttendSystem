package Constants;

/**
 * Created by kingwen on 2016/10/5.
 */
public class AcademicYearSqlStatement {

    /**
     * 建立学年信息表格
     */
    public static final String ACADEMICYEAR_CREATE="create table academicyear (" +
            "ecadyear_id  int auto_increment primary key ," +
            "ecadyear varchar(12) not null, " +
            "term varchar(2) not null )";

    /**
     * 搜索学年信息
     */
    public static  final String ACADEMICYEAR_SEARCHALL="select * from academicyear";
}
