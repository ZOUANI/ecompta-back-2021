package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Connection;
import stage.sir.gestioncomptabilite.bean.Login;

import java.util.List;

@Repository

public interface ConnectionDao extends JpaRepository<Connection,Long> {
    List<Login> findByType(String type);
    int deleteByType(String type);
    int deleteByUsername(String username);
    Login findByUsernameAndPassword(String username,String password);
    List<Login> findByUsername(String usename);
}
