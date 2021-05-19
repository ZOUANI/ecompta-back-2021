package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.OperationSocieteJustif;

import java.util.Date;

@Repository
public interface OperationSocieteJustifDao extends JpaRepository<OperationSocieteJustif,Long> {
    OperationSocieteJustif findByRef(String ref);
    int deleteByRef(String ref);
    OperationSocieteJustif findByChemin(String chemin);
    int deleteByChemin(String chemin);
    OperationSocieteJustif findByDateJustif(Date dateJusti);
    int deleteByDateJustif (Date dateJustif);
}
