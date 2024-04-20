package Dao;

import Core.Db;
import Entity.User;

import java.sql.*;
import java.util.ArrayList;


public class UserDao {
    private final Connection con;
    public UserDao(){
        this.con= Db.getInstance();
    }
    public ArrayList<User> findAll(){
        ArrayList<User> userList=new ArrayList<>();
        String sql="Select * From public.User";
        try{
            Statement statement=this.con.createStatement();
            ResultSet rs=statement.executeQuery(sql);
            while (rs.next()){
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return userList;
    }
    public User findByLogin(String username,String password,String role){
        User obj=null;
        String query="Select * From public.User Where username = ? And password = ? And role = ?";
        try{
            PreparedStatement pr=this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            pr.setString(3,role);
            ResultSet rs= pr.executeQuery();
            if(rs.next()){
                obj=this.match(rs);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return  obj;
    }
    public User match(ResultSet resultSet) throws SQLException{
        User obj=new User();
        obj.setId(resultSet.getInt("id"));
        obj.setUsername(resultSet.getString("username"));
        obj.setPassword(resultSet.getString("password"));
        obj.setRole(resultSet.getString("role"));
        return obj;
    }


}