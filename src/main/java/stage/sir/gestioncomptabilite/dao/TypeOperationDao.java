package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.TypeOperation;
@Repository
public interface TypeOperationDao extends JpaRepository<TypeOperation,Long> {
    public TypeOperation findByLibelle(String libelle);
    public int deleteByLibelle(String libelle);
}
