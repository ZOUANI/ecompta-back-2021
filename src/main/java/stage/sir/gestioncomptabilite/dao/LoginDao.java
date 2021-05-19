package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.bean.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, Long> {
	Login findByType(String type);
	int deleteByType(String type);
	Login findByUsernameAndPassword(String username,String password);
	Login findByUsername(String usename);

}
