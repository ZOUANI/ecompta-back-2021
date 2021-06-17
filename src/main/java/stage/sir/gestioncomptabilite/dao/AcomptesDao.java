package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Acomptes;

import java.util.List;

@Repository
public interface AcomptesDao extends JpaRepository<Acomptes, Long> {

    List<Acomptes> findByNumero(Integer numero);
    int deleteByNumero(Integer numero);
    Acomptes findBySocieteIceAndAnneeAndNumero(String ice, double annee, Integer numero);
    int deleteBySocieteIceAndAnneeAndNumero(String ice, double annee, Integer numero);
    List<Acomptes> findBySocieteIceAndAnnee(String ice, double annee);
    int deleteBySocieteIceAndAnnee(String ice, double annee);
}
