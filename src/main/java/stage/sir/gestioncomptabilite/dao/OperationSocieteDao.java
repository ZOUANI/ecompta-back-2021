package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.OperationSociete;

import java.util.List;

@Repository
public interface OperationSocieteDao extends JpaRepository<OperationSociete,Long> {
    public OperationSociete findByRef(String ref);
    public int deleteByRef(String ref);
    public List<OperationSociete> findBySocieteIce(String ice);
    public List<OperationSociete> findBySocieteIceAndEtatOperationSocieteRef(String ice, String etat);
}
