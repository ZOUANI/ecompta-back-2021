package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Paiement;
import stage.sir.gestioncomptabilite.bean.PaiementIS;

@Repository
public interface PaiementISDao extends JpaRepository<PaiementIS,Long> {
    PaiementIS findByRef(String ref);
    int deleteByRef(String ref);
}
