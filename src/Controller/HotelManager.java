package Controller;

import Dao.HotelDao;
import Entity.Hotel;

import java.sql.SQLException;
import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelDao();
    }

    public ArrayList<Hotel> findAll() {
        return hotelDao.findAll();
    }

    public void save(Hotel hotel) throws SQLException {
        this.hotelDao.save(hotel);
    }
}
