package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Etape;
import stage.sir.gestioncomptabilite.bean.TypeOperation;
import stage.sir.gestioncomptabilite.dao.EtapeDao;

import java.util.List;

@Service
public class EtapeService {
    @Autowired
    private EtapeDao etapeDao;

    public Etape findByLibelle(String libelle) {
        return etapeDao.findByLibelle(libelle);
    }
    @Transactional
    public int deleteByLibelle(String libelle) {
        return etapeDao.deleteByLibelle(libelle);
    }

    public List<Etape> findAll() {
        return etapeDao.findAll();
    }

    public int save(TypeOperation typeOperation, List<Etape> etapes){
        if(typeOperation == null) return -1;
        for (Etape etape : etapes) {
            if (etapeDao.findByLibelle(etape.getLibelle()) != null) return -1;
            else {
                typeOperation.setFraixFixTotal(typeOperation.getFraixFixTotal() + etape.getMontantFix());
                typeOperation.setFraixComptableTotal(typeOperation.getFraixComptableTotal() + etape.getMontantComptable());
                etape.setTypeOperation(typeOperation);
                etapeDao.save(etape);
            }
        }
            return 1;

    }
}
