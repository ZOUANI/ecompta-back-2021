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
    @Autowired
    private TypeOperationService typeOperationService;

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

    public List<Etape> findByTypeOperationLibelle(String libelle) {
        return etapeDao.findByTypeOperationLibelle(libelle);
    }
    @Transactional
    public int deleteByTypeOperationLibelle(String libelle) {
        return etapeDao.deleteByTypeOperationLibelle(libelle);
    }

    public int save(TypeOperation typeOperation, List<Etape> etapes){
        if(typeOperation == null) return -1;
        for (Etape etape : etapes) {
            if (etapeDao.findByLibelle(etape.getLibelle()) != null) return -1;
            else {
                typeOperation.setFraixfixtotal(typeOperation.getFraixfixtotal() + etape.getMontantfix());
                typeOperation.setFraixcomptabletotal(typeOperation.getFraixcomptabletotal() + etape.getMontantcomptable());
                etape.setTypeOperation(typeOperation);
                etapeDao.save(etape);
            }
        }
            return 1;

    }
    public void update(Etape etape){

            etapeDao.save(etape);

    }
}
