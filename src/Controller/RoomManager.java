package Controller;

import Dao.RoomDao;
import Entity.Room;

import java.sql.SQLException;
import java.util.List;

public class RoomManager {
    private final RoomDao roomDao;

    public RoomManager() {
        this.roomDao = new RoomDao();
    }

    public List<Room> findAll(){
        return roomDao.findAll();
    }

    public void saveRoom(Room room) throws SQLException {
        roomDao.saveRoom(room);
    }
}
