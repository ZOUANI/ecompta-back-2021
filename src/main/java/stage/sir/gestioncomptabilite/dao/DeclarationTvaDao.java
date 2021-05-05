package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.DeclarationTva;

import java.util.List;

@Repository
public interface DeclarationTvaDao extends JpaRepository<DeclarationTva, Long> {
    DeclarationTva findByRef(String ref);
    int deleteByRef(String ref);
    List<DeclarationTva> findByAnneeAndMois(double annee,double mois);
    List<DeclarationTva> findByAnneeAndTrim(double annee,double trim);
    List<DeclarationTva> findBySocieteIce(String ice);
    int deleteBySocieteIce(String ice);
    List<DeclarationTva> findByTypeDeclarationTvaRef(String ref);
    int deleteByTypeDeclarationTvaRef(String ref);
    DeclarationTva findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(String refsociete,String refetatdeclaration,double annee,double trim);
    DeclarationTva findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(String refsociete,String refetatdeclaration,double annee,double mois);

}
