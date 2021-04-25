package stage.sir.gestioncomptabilite.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;

@Repository
public interface DeclarationIREmployeDao extends JpaRepository<DeclarationIREmploye,Long> {

	
	DeclarationIREmploye findByRefEmp(String refEmp);
	DeclarationIREmploye findByEmployeCin(String cin);
	List<DeclarationIREmploye> findByDeclarationIRRef(String ref);
	
	int deleteByEmployeCin(String cin);
	
	
	
	
}
