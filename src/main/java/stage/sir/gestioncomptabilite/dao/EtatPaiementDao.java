package stage.sir.gestioncomptabilite.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import stage.sir.gestioncomptabilite.bean.EtatPaiement;

@Repository
public interface EtatPaiementDao extends JpaRepository<EtatPaiement, Long> {
    public EtatPaiement findByCode(double code);
    public int deleteByCode(double code);
}
