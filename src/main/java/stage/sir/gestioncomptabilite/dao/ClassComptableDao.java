package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.ClassComptable;

import java.util.List;
@Repository
public interface ClassComptableDao extends JpaRepository<ClassComptable,Long > {
    List<ClassComptable> findAll();
    ClassComptable findByRef(String ref);
    int deleteByRef(String ref);
}
