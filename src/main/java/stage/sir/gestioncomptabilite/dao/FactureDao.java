package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Facture;

import java.util.List;

@Repository

public interface FactureDao extends JpaRepository<Facture,Long> {
    public Facture findByRef(String ref);
    public int deleteByRef(String ref);
    public List<Facture> findByDeclarationISRef(String ref);
    public int deleteByDeclarationISRef(String ref);
    List<Facture> findByTypeOperationAndDeclarationISRef(String type, String ref);
    List<Facture> findBySocieteSourceIceAndAnneeAndTrim(String ice, double annee,double trim);
    List<Facture> findBySocieteSourceIceAndAnneeAndMois(String ice, double annee, double mois);
    List<Facture> findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(String ice, double annee,double trim,String typeoperation);
    List<Facture> findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(String ice, double annee,double mois,String typeoperation);

}