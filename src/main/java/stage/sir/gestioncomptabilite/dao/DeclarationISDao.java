package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import java.util.List;

@Repository
public interface DeclarationISDao extends JpaRepository<DeclarationIS, Long> {
    DeclarationIS findByRef(String ref);
    DeclarationIS findByAnnee(double annee);
    int deleteByAnnee(double annee);
    List<DeclarationIS> findBySocieteIce(String ice);
    DeclarationIS findBySocieteIceAndAnnee(String ice, double annee);
    int deleteBySocieteIceAndAnnee(String ice, double annee);
    List<DeclarationIS> findByEtatDeclarationLibelle(String libelle);
}