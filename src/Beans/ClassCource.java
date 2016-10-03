package Beans;

/**
 * Created by kingwen on 2016/9/16.
 */
public class ClassCource {
    private String class_id;
    private String class_name;
    private String class_credit;
    private String class_beginweek;
    private String class_endweek;
    private String class_time;
    private String class_pweek;
    private String teacher_id;

    public ClassCource(){}

    public ClassCource(String classid,String classname,String classcredit,
                       String beginweek,String endweek, String classtime,String classpweek,String teacher_id){
        class_id=classid;

    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getClass_credit() {
        return class_credit;
    }

    public void setClass_credit(String class_credit) {
        this.class_credit = class_credit;
    }

    public String getClass_beginweek() {
        return class_beginweek;
    }

    public void setClass_beginweek(String class_beginweek) {
        this.class_beginweek = class_beginweek;
    }

    public String getClass_endweek() {
        return class_endweek;
    }

    public void setClass_endweek(String class_endweek) {
        this.class_endweek = class_endweek;
    }

    public String getClass_time() {
        return class_time;
    }

    public void setClass_time(String class_time) {
        this.class_time = class_time;
    }

    public String getClass_pweek() {
        return class_pweek;
    }

    public void setClass_pweek(String class_pweek) {
        this.class_pweek = class_pweek;
    }

    public String getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(String teacher_id) {
        this.teacher_id = teacher_id;
    }
}
