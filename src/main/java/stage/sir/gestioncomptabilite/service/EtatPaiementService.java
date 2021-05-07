package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.EtatPaiement;
import stage.sir.gestioncomptabilite.dao.EtatPaiementDao;

import java.util.List;

@Service
public class EtatPaiementService {
    @Autowired
    private EtatPaiementDao etatPaiementDao;

    public EtatPaiement findByCode(double code) {
        return etatPaiementDao.findByCode(code);
    }
    @Transactional
    public int deleteByCode(double code) {
        return etatPaiementDao.deleteByCode(code);
    }

    public List<EtatPaiement> findAll() {
        return etatPaiementDao.findAll();
    }
    public int save(EtatPaiement etatPaiement){
        if(etatPaiementDao.findByCode(etatPaiement.getCode()) != null) return -1;
        else {
            etatPaiementDao.save(etatPaiement);
            return 1;
        }
    }
}
