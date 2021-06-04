package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Acomptes;

import java.util.List;

@Repository
public interface AcomptesDao extends JpaRepository<Acomptes, Long> {

    List<Acomptes> findByNumero(Integer numero);
    int deleteByNumero(Integer numero);
    int deleteBySocieteIceAndAnneePaye(String ice, double annee);
}
