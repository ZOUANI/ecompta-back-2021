package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Paiement;
import stage.sir.gestioncomptabilite.dao.PaiementDao;

import java.util.List;

@Service
public class PaiementService {
    @Autowired
    private PaiementDao paiementDao;

    public Paiement findByRef(String ref) {
        return paiementDao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return paiementDao.deleteByRef(ref);
    }

    public List<Paiement> findAll() {
        return paiementDao.findAll();
    }
    public int save(Paiement paiement){
        if(paiementDao.findByRef(paiement.getRef()) != null) return -1;
        else {
            return 1;
        }
    }
}
