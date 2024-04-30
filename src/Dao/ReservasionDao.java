package Dao;

import Core.Db;
import Entity.Reservasion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservasionDao {
    private final Connection con;

    public ReservasionDao() {
        this.con = Db.getInstance();
    }

    public List<Reservasion> findAll() {
        List<Reservasion> reservasionList = new ArrayList<>();
        String sql = "Select * From public.reservation";
        try {
            Statement statement = this.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                reservasionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservasionList;
    }

    public void delete(Reservasion reservasion) throws SQLException {
        String query = "DELETE FROM public.reservation\n" +
                "\t WHERE id = ?;";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setInt(1, reservasion.getId());
        pr.execute();
    }

    public void update(Reservasion reservasion) throws SQLException {
        String sql = "UPDATE public.reservation SET city = ?, season_choice = ?, room_type = ?, name_surname = ?, " +
                "phone_num = ?, mail = ?, hotel_name = ?, entry_date = ?, exit_date = ?, people_number = ?, " +
                "child_number = ?, price = ? WHERE id = ?;";
        PreparedStatement pr = this.con.prepareStatement(sql);
        pr.setString(1, reservasion.getCity());
        pr.setString(2, reservasion.getSeasonChoice());
        pr.setString(3, reservasion.getRoomType());
        pr.setString(4, reservasion.getNameSurname());
        pr.setString(5, reservasion.getPhoneNum());
        pr.setString(6, reservasion.getMail());
        pr.setString(7, reservasion.getHotelName());
        pr.setString(8, reservasion.getEntryDate());
        pr.setString(9, reservasion.getExitDate());
        pr.setInt(10, reservasion.getPeopleNumber());
        pr.setInt(11, reservasion.getChildNumber());
        pr.setString(12, reservasion.getPrice());
        pr.setInt(13, reservasion.getId());
        pr.execute();
    }

    public void save(Reservasion reservasion) throws SQLException {
        String query = "INSERT INTO public.reservation(\n" +
                "\t city, room_id, season_choice, room_type, name_surname, phone_num, mail, hotel_name, entry_date, exit_date, people_number, child_number, price)\n" +
                "\t VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement pr = con.prepareStatement(query);
        pr.setString(1, reservasion.getCity());
        pr.setInt(2, reservasion.getRoomId());
        pr.setString(3, reservasion.getSeasonChoice());
        pr.setString(4, reservasion.getRoomType());
        pr.setString(5, reservasion.getNameSurname());
        pr.setString(6, reservasion.getPhoneNum());
        pr.setString(7, reservasion.getMail());
        pr.setString(8, reservasion.getHotelName());
        pr.setString(9, reservasion.getEntryDate());
        pr.setString(10, reservasion.getExitDate());
        pr.setInt(11, reservasion.getPeopleNumber());
        pr.setInt(12, reservasion.getChildNumber());
        pr.setString(13, reservasion.getPrice());
        pr.executeUpdate();
    }

    public static Reservasion match(ResultSet resultSet) throws SQLException {// database ile Reservasion class nesneleri birleştirme
        // Reservasion nesnesi oluşturma
        Reservasion reservasion = new Reservasion();

        // ResultSet'ten verileri alarak Reservasion nesnesine atama
        reservasion.setId(resultSet.getInt("id"));
        reservasion.setCity(resultSet.getString("city"));
        reservasion.setSeasonChoice(resultSet.getString("season_choice"));
        reservasion.setRoomType(resultSet.getString("room_type"));
        reservasion.setNameSurname(resultSet.getString("name_surname"));
        reservasion.setPhoneNum(resultSet.getString("phone_num"));
        reservasion.setMail(resultSet.getString("mail"));
        reservasion.setHotelName(resultSet.getString("hotel_name"));
        reservasion.setEntryDate(resultSet.getString("entry_date"));
        reservasion.setExitDate(resultSet.getString("exit_date"));
        reservasion.setPeopleNumber(resultSet.getInt("people_number"));
        reservasion.setChildNumber(resultSet.getInt("child_number"));
        reservasion.setPrice(resultSet.getString("price"));

        // Reservasion nesnesini döndürme
        return reservasion;
    }
}
