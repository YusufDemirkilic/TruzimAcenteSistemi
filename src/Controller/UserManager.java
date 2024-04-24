package Controller;

import Dao.UserDao;
import Entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private final UserDao userDao;
    public UserManager(){
        this.userDao=new UserDao();
    }
        public ArrayList<User> findAll(){
            return userDao.findAll();
        }

        public User findByLogin(String username,String password){
            return this.userDao.findByLogin(username,password);
        }
        public List<User> findByRole(String role){
        return this.userDao.findByRole(role);

        }
        public void updateUser(User user) throws SQLException {
             this.userDao.updateUser(user);
        }
        public void deleteUser(User user) throws SQLException {
        this.userDao.delete(user);
        }
        public void saveUser(User user) throws SQLException {
            this.userDao.saveUser(user);
        }
}
