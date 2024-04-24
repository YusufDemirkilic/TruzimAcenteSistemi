package Dao;

import Core.Db;
import Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDao {
    private final Connection con;

    public UserDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<User> findAll() {// Data base bağlantısındaki tablodaki tüm verileri listeleme
        ArrayList<User> userList = new ArrayList<>();
        String sql = "Select * From public.user";
        try {
            Statement statement = this.con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public User findByLogin(String username, String password) {// kullanıcı girişi için veri kontrolü
        User obj = null;
        String query = "Select * From public.user Where username= ? And password = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    public List<User>  findByRole(String role) {// Kullnıcı girişi için rol sorgusu
        List<User> userList = new ArrayList<>();
        String query = "Select * From public.user Where role= ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, role);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                userList.add(this.match(rs));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }
    public void updateUser(User user) throws SQLException { // databasdeki parametrelerce atnanan verileri güncelleme
        String query = "UPDATE public.user SET username = ?, role = ? WHERE id = ?";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setString(1, user.getUsername());
        pr.setString(2, user.getRole());
        pr.setInt(3, user.getId());
        pr.execute();
    }
    public void delete(User user) throws SQLException {     // databasedeki veriyi parametreden verilen id ile silme
        String query = "DELETE FROM public.user WHERE id = ?";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setInt(1, user.getId());
        pr.execute();
    }
    public void saveUser(User user) throws SQLException {// database parametrelere atanan verileri ekleme veya kaydetme
        String query = "INSERT INTO public.user(username, password, role) VALUES(?,?,?)";
        PreparedStatement pr = this.con.prepareStatement(query);
        pr.setString(1, user.getUsername());
        pr.setString(2, user.getPassword());
        pr.setString(3, user.getRole());
        pr.execute();
    }

    public User match(ResultSet resultSet) throws SQLException {// database ile user class nesneleri birleştirme
        User obj = new User();
        obj.setId(resultSet.getInt("id"));
        obj.setUsername(resultSet.getString("username"));
        obj.setPassword(resultSet.getString("password"));
        obj.setRole(resultSet.getString("role"));
        return obj;
    }


}
