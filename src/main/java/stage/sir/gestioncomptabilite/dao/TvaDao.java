package stage.sir.gestioncomptabilite.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Tva;

@Repository
public interface TvaDao extends JpaRepository<Tva, Long> {
    public Tva findByRef(String ref);
    public  int deleteByRef(String ref);
}