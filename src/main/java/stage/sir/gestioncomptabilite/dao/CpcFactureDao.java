package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Cpc;
import stage.sir.gestioncomptabilite.bean.CpcFacture;
@Repository
public interface CpcFactureDao extends JpaRepository<CpcFacture,Long> {
    int deleteCpcFacturesByCpcId(Long id);
}
