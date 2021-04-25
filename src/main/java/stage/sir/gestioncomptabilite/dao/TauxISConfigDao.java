package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.TauxIsConfig;

import java.util.List;

@Repository
public interface TauxISConfigDao extends JpaRepository<TauxIsConfig, Long> {
    public List<TauxIsConfig> findByCotisationMinimale(Double cm);
    public int deleteByCotisationMinimale(Double cm);
}
