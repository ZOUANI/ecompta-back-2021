package stage.sir.gestioncomptabilite.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import stage.sir.gestioncomptabilite.bean.SousClasseComptable;

import java.util.List;

public interface SousClasseComptableDao extends JpaRepository<SousClasseComptable,Long > {
    List<SousClasseComptable> findByClasseComptableNumero(int numero);
    SousClasseComptable findByNumero(int numero);
    List<SousClasseComptable> findAll();
    int deleteByClasseComptableNumero(int numero);
    int deleteByNumero(int numero);
}
