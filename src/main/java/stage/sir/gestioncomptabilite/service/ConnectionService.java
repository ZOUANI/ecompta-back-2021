package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.Connection;
import stage.sir.gestioncomptabilite.bean.Login;
import stage.sir.gestioncomptabilite.dao.ConnectionDao;

import java.util.List;

@Service

public class ConnectionService {
    @Autowired
    private ConnectionDao connectionDao;

    public List<Login> findByType(String type) {
        return connectionDao.findByType(type);
    }

    public int deleteByType(String type) {
        return connectionDao.deleteByType(type);
    }

    public int deleteByUsername(String username) {
        return connectionDao.deleteByUsername(username);
    }

    public Login findByUsernameAndPassword(String username, String password) {
        return connectionDao.findByUsernameAndPassword(username, password);
    }

    public List<Login> findByUsername(String usename) {
        return connectionDao.findByUsername(usename);
    }

    public List<Connection> findAll() {
        return connectionDao.findAll();
    }

    public int save(Connection connection) {
        if (connectionDao.findByUsernameAndPassword(connection.getUsername(),connection.getPassword()) !=null) {
            return -1;
        }else {
            connection.setEtat("en cour");
            connectionDao.save(connection);
            return 1;
        }

    }
}
