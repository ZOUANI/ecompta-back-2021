package stage.sir.gestioncomptabilite.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.bean.Details;

@Repository
public interface DetailsDao extends JpaRepository<Details,Long> {
	
	
	@Override
	Optional<Details> findById(Long id);
	
	//Details findByTrancheRevenu(String trancheRevenu );

	

}
