package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Paiement2;

import java.util.List;

@Repository
public interface Paiement2Dao extends JpaRepository<Paiement2, Long> {
    Paiement2 findByRef(String ref);
    int deleteByRef(String ref);
    List<Paiement2> findByDeclarationTvaRef(String ref);
    List<Paiement2> findByDeclarationISRef(String ref);

}
