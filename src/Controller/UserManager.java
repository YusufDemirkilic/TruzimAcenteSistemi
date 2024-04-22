package Controller;

import Dao.UserDao;
import Entity.User;

public class UserManager {
    private final UserDao userDao;
    public UserManager(){
        this.userDao=new UserDao();
    }
        public User findByLogin(String username,String password){
            return this.userDao.findByLogin(username,password);
        }
        public User findByRole(String role){
        return this.userDao.findByRole(role);

        }
}
