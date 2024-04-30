package Controller;

import Dao.ReservasionDao;
import Entity.Reservasion;

import java.sql.SQLException;
import java.util.List;

public class ReservasionManager {

    private final ReservasionDao reservasionDao;

    public ReservasionManager() {
        this.reservasionDao = new ReservasionDao();
    }

    public void save(Reservasion reservasion) throws SQLException {
        reservasionDao.save(reservasion);
    }

    public List<Reservasion> findAll() {
        return reservasionDao.findAll();
    }

    public void delete(Reservasion reservasion) throws SQLException {
        reservasionDao.delete(reservasion);
    }

    public void update(Reservasion reservasion) throws SQLException {
        reservasionDao.update(reservasion);
    }

}
