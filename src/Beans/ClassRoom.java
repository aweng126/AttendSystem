package Beans;

import Constants.ClassRoomSqlStatement;
import Constants.SqlStatement;
import Utils.DB;
import lombok.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kingwen on 2016/10/4.
 * 教室javabean 包含建筑名，房间号
 * */
public class ClassRoom {

    private String roomid =null;

    private String building;
    private String roomnum;


    public ClassRoom(){}
    public ClassRoom(String building, String roomnum) {
        this.building = building;
        this.roomnum = roomnum;
    }

     public String getBuilding() {
                        return building;
                    }

     public void setBuilding(String building) {
                        this.building = building;
                    }
     public String getRoomnum() {
                        return roomnum;
                    }

       public void setRoomnum(String roomnum) {
                        this.roomnum = roomnum;
                    }
      @Override 
         public String toString() {
              return "ClassRoom{" +
                   "building='" + building + '\'' +
                   ", roomnum='" + roomnum + '\'' +
                   '}';
                    }

    public static List<ClassRoom> getAllClassRooms(){
        List<ClassRoom> list=new ArrayList<ClassRoom>();
        Connection conn=null;
        ResultSet rs=null;
        try {
            conn=DB.getConnection();
            rs=DB.execteQuery(conn, ClassRoomSqlStatement.CLASSROOM_SEARCHALL);
            while(rs.next()){
                ClassRoom classroom=new ClassRoom();

                classroom.setRoomid(rs.getString("room_id"));
                classroom.setBuilding(rs.getString("building"));
                classroom.setRoomnum(rs.getString("roomnum"));

                list.add(classroom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            DB.closeRS(rs);
            DB.closeConn(conn);
        }
        return list;
    }



    public void save(){
       Connection conn= null;
       PreparedStatement pStmt=null;
       try {
           conn= DB.getConnection();
           pStmt=DB.getPStmt(conn, ClassRoomSqlStatement.CLASSROOM_INSERT);
           pStmt.setString(1,building);
           pStmt.setString(2,roomnum);
           pStmt.executeUpdate();
       } catch (SQLException e) {
           e.printStackTrace();
       }finally {
           DB.closeStmt(pStmt);
           DB.closeConn(conn);
       }
   }

    public String getRoomid() {
        return roomid;
    }

    public void setRoomid(String roomid) {
        this.roomid = roomid;
    }
}
