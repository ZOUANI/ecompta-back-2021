package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Connection;
import stage.sir.gestioncomptabilite.bean.Login;

import java.util.List;

@Repository

public interface ConnectionDao extends JpaRepository<Connection,Long> {
    List<Connection> findByType(String type);
    int deleteByType(String type);
    int deleteByUsername(String username);
    Connection findByUsernameAndPassword(String username,String password);
    List<Connection> findByUsername(String usename);
}
