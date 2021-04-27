package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.TauxIsConfig;

@Repository
public interface TauxISConfigDao extends JpaRepository<TauxIsConfig, Long> {
     TauxIsConfig findByRef(String ref);
}
