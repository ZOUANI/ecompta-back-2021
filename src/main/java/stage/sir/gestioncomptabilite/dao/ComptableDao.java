package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Comptable;

@Repository
public interface ComptableDao extends JpaRepository<Comptable,Long> {
    public Comptable findByCode(String code);
    public int deleteByCode(String code);
}
