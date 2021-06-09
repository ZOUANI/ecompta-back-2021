package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.EtatDemande;

@Repository
public interface EtatDemandeDao extends JpaRepository<EtatDemande, Long> {
    EtatDemande findByRef(String ref);
    int deleteByRef(String ref);
    EtatDemande findByLibelle(String libelle);
}
