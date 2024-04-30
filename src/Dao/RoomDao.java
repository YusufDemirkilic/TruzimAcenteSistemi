package Dao;

import Core.Db;
import Entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomDao {
    private final Connection con;

    public RoomDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Room> findAll() {// Database bağlantısındaki tablodaki tüm verileri listeleme
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "Select * From public.room";
        try {
            Statement statement = this.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    public void saveRoom(Room room) throws SQLException {
        String query = "INSERT INTO room (hotel_id, room_size, television, mini_bar, game_console, safe, projection, bed_number, room_type, price, availability, stock)\n" +
                " VALUES\n" +
                " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setInt(1, room.getHotelId());
        pr.setString(2, room.getRoomSize());
        pr.setBoolean(3, room.isTelevision());
        pr.setBoolean(4, room.isMiniBar());
        pr.setBoolean(5, room.isGameConsole());
        pr.setBoolean(6, room.isSafe());
        pr.setBoolean(7, room.isProjection());
        pr.setInt(8, room.getBedNumber());
        pr.setString(9, room.getRoomType());
        pr.setString(10, room.getPrice());
        pr.setBoolean(11, room.isAvailability());
        pr.setInt(12, room.getStock());
        pr.execute();
    }

    public void update(Room room) throws SQLException {
        String query = "UPDATE public.room\n" +
                "\t SET stock=?\n" +
                "\t WHERE hotel_id = ? and room_type = ?;";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setInt(1, room.getStock());
        pr.setInt(2, room.getHotelId());
        pr.setString(3, room.getRoomType());
        pr.execute();
    }

    public static Room match(ResultSet resultSet) throws SQLException {// database ile Room class nesneleri birleştirme
        // Room nesnesi oluşturma
        Room room = new Room();

        // ResultSet'ten verileri alarak Hotel nesnesine atama
        room.setId(resultSet.getInt("id"));
        room.setHotelId(resultSet.getInt("hotel_id"));
        room.setRoomSize(resultSet.getString("room_size"));
        room.setTelevision(resultSet.getBoolean("television"));
        room.setMiniBar(resultSet.getBoolean("mini_bar"));
        room.setGameConsole(resultSet.getBoolean("game_console"));
        room.setSafe(resultSet.getBoolean("safe"));
        room.setProjection(resultSet.getBoolean("projection"));
        room.setBedNumber(resultSet.getInt("bed_number"));
        room.setRoomType(resultSet.getString("room_type"));
        room.setPrice(resultSet.getString("price"));
        room.setAvailability(resultSet.getBoolean("availability"));
        room.setStock(resultSet.getInt("stock"));

        // room nesnesini döndürme
        return room;
    }
}
