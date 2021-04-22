package stage.sir.gestioncomptabilite.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;

@Repository
public interface DeclarationIREmployeDao extends JpaRepository<DeclarationIREmploye,Long> {

	
	DeclarationIREmploye findByRefEmp(String refEmp);
	List<DeclarationIREmploye> findByEmployeCin(String cin);
	
	
	int deleteByEmployeCin(String cin);
	
	
	
	
}
