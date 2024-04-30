package Dao;

import Core.Db;
import Entity.Hotel;

import java.sql.*;
import java.util.ArrayList;

public class HotelDao {
    private final Connection con;

    public HotelDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Hotel> findAll() {// Database bağlantısındaki tablodaki tüm verileri listeleme
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "Select * From public.hotel";
        try {
            Statement statement = this.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }

    public static Hotel match(ResultSet resultSet) throws SQLException {// database ile hotel class nesneleri birleştirme
        // Hotel nesnesi oluşturma
        Hotel htl = new Hotel();

        // ResultSet'ten verileri alarak Hotel nesnesine atama
        htl.setId(resultSet.getInt("id"));
        htl.setHotelName(resultSet.getString("hotel_name"));
        htl.setAddress(resultSet.getString("address"));
        htl.setEmail(resultSet.getString("email"));
        htl.setPhone(resultSet.getString("phone"));
        htl.setStar(resultSet.getInt("star"));
        htl.setPansiyon(resultSet.getString("pansiyon"));
        htl.setTesis(resultSet.getString("tesis"));
        htl.setSeson(resultSet.getString("sezon"));

        // Hotel nesnesini döndürme
        return htl;
    }

    public void save(Hotel hotel) {// database dışardan alınana parametredeki verileri ekleme, kayıt etme
        String query = "INSERT INTO public.hotel (hotel_name, address, email, phone, star, pansiyon, tesis, sezon)"
                + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setString(1, hotel.getHotelName());
            pr.setString(2, hotel.getAddress());
            pr.setString(3, hotel.getEmail());
            pr.setString(4, hotel.getPhone());
            pr.setInt(5, hotel.getStar());
            pr.setString(6, hotel.getPansiyon());
            pr.setString(7, hotel.getTesis());
            pr.setString(8, hotel.getSeson());
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
