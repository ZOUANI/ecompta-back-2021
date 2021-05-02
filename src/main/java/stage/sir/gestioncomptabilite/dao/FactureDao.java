package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Facture;

import java.util.List;

@Repository

public interface FactureDao extends JpaRepository<Facture,Long> {
    public Facture findByRef(String ref);
    public int deleteByRef(String ref);
    List<Facture> findBySocieteSourceIceAndAnnee(String ice, double annee);
    List<Facture> findBySocieteSourceIceAndAnneeAndTrim(String ice, double annee,double trim);
    List<Facture> findBySocieteSourceIceAndAnneeAndMois(String ice, double annee, double mois);
    List<Facture> findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(String ice, double annee,double trim,String typeoperation);
    List<Facture> findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(String ice, double annee,double mois,String typeoperation);
    List<Facture> findBySocieteSourceIceAndTypeOperation(String ice,String typeoperation);

    List<Facture> findBySocieteSourceIceAndAnneeAndTypeOperation(String ice, double annee,String typeoperation);

}