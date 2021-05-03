package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.ClasseComptable;

import java.util.List;

@Repository
public interface ClassComptableDao extends JpaRepository<ClasseComptable,Long > {
    List<ClasseComptable> findAll();
    ClasseComptable findByNumero(int numero);
    int deleteByNumero(int numero);

}
