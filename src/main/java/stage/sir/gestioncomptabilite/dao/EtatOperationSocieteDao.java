package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.EtatOperationSociete;

@Repository
public interface EtatOperationSocieteDao extends JpaRepository<EtatOperationSociete,Long> {
    EtatOperationSociete findByRef(String ref);
    int deleteByRef(String ref);
}
