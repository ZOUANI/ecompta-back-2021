package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.OperationSociete;
import stage.sir.gestioncomptabilite.bean.Paiement;
import stage.sir.gestioncomptabilite.dao.PaiementDao;

import java.util.List;

@Service
public class PaiementService {
    @Autowired
    private PaiementDao paiementDao;
    @Autowired
    private OperationSocieteService operationSocieteService ;

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
        OperationSociete operationSociete = operationSocieteService.findByRef(paiement.getOperationSociete().getRef());
        paiement.setOperationSociete(operationSociete);
        if(paiementDao.findByRef(paiement.getRef()) != null) return -1;
        else if (operationSociete == null) return -2;
        else {
            paiementDao.save(paiement);
            return 1;
        }
    }

    public int save2(OperationSociete operationSociete, List<Paiement> paiements){
        if (operationSociete == null) return -1;
        for (Paiement paiement:paiements) {
            if(findByRef(paiement.getRef())!= null) return -2;
            else {
                paiement.setOperationSociete(operationSociete);
                paiementDao.save(paiement);
            }
        }
        return 1;
    }
}
