package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.EtatFacture;

@Repository
public interface EtatFactureDao extends JpaRepository<EtatFacture, Long> {
    public EtatFacture findByCode(double code);
    public int deleteByCode(double code);
}
