package stage.sir.gestioncomptabilite.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.bean.CategorieService;

@Repository
public interface CategorieServiceDao extends JpaRepository<CategorieService, Long> {

	CategorieService findByTitre(String titre);
	
	Optional<CategorieService> findById(Long id);
	int deleteByTitre(String titre);
}
