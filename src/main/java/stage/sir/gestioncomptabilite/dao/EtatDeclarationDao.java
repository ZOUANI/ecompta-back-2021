package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.EtatDeclaration;

@Repository
public interface EtatDeclarationDao extends JpaRepository<EtatDeclaration,Long> {
    EtatDeclaration findByRef(String ref);
    int deleteByRef(String ref);

    EtatDeclaration findByLibelle(String libelle);
}
