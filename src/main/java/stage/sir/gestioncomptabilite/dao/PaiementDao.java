package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.Paiement;

import java.util.List;

@Repository
public interface PaiementDao  extends JpaRepository<Paiement,Long> {
    public Paiement findByRef(String ref);
    public int deleteByRef(String ref);
    public List<Paiement> findByOperationSocieteRef(String ref);
    public int deleteByOperationSocieteRef(String ref);
}
