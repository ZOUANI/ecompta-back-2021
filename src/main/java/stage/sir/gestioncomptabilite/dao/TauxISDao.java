package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.TauxIS;

@Repository
public interface TauxISDao extends JpaRepository<TauxIS, Long> {
    TauxIS findByRef(String ref);
    int deleteByRef(String ref);
}
