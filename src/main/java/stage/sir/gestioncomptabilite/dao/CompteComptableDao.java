package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.CompteComptable;

import java.util.List;

@Repository
public interface CompteComptableDao extends JpaRepository<CompteComptable,Long > {
    List<CompteComptable> findAll();
    List<CompteComptable> findBySousClasseComptableNumero(int numero);
    int deleteByCode(String code);
    int deleteBySousClasseComptableNumero(int numero);
    CompteComptable findByCode(String code);

}
