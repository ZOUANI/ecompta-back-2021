package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Etape;

import java.util.List;

@Repository
public interface EtapeDao  extends JpaRepository<Etape,Long> {
    public Etape findByLibelle(String libelle);
    public int deleteByLibelle(String libelle);
    public List<Etape> findByTypeOperationLibelle(String libelle);
    public int deleteByTypeOperationLibelle(String libelle);
}
