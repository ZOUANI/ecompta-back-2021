package stage.sir.gestioncomptabilite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import stage.sir.gestioncomptabilite.bean.Societe;

@Repository
public interface SocieteDao extends JpaRepository<Societe, Long> {
    Societe findByIce(String ice);
    int deleteByIce(String ice);
    
  //  Societe findByLoginAndPassword(String login,String password);
	

}
