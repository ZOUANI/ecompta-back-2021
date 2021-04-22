package stage.sir.gestioncomptabilite.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.bean.DeclarationIR;



@Repository
public interface DeclarationIRDao extends JpaRepository<DeclarationIR,Long>  {
	DeclarationIR findByMois(Integer mois);
	DeclarationIR findByAnnee(Integer annee);
	Optional<DeclarationIR> findById(Long id);
	DeclarationIR findByRef(String ref);
	int deleteByMois(Integer mois);
	

}
