package stage.sir.gestioncomptabilite.dao;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.bean.TauxIr;

@Repository
public interface TauxIrDao extends JpaRepository<TauxIr,Long> {
	
	
	@Override
	 Optional<TauxIr> findById(Long id);
	@Override
	void deleteById(Long id);
	TauxIr findByPourcentage(Double pourcentage);
	
	

}
