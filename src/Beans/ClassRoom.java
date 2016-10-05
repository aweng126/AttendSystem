package Beans;

import Constants.SqlStatement;
import Utils.DB;
import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by kingwen on 2016/10/4.
 * 教室javabean 包含建筑名，房间号
 * */
@Data
public class ClassRoom {
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
   public void save(){
       Connection conn= null;
       PreparedStatement pStmt=null;
       try {
           conn= DB.getConnection();
           pStmt=DB.getPStmt(conn, SqlStatement.CLASSROOM_INSERT);
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
}
