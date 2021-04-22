package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.bean.Employe;

@Repository
public interface EmployeDao extends JpaRepository<Employe,Long> {
	
	Employe findByCin(String cin);
	
	int deleteByCin(String cin);
	
	

}
