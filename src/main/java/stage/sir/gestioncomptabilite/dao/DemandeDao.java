package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Demande;

import java.util.List;

@Repository
public interface DemandeDao extends JpaRepository<Demande, Long> {
    Demande findByRef(String ref);
    int deleteByRef(String ref);
    List<Demande> findByOperation(String operation);
    Demande findByUserUsername(String username);
}
