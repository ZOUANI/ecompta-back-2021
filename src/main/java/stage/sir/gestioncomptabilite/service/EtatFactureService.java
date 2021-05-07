package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.EtatFacture;
import stage.sir.gestioncomptabilite.dao.EtatFactureDao;

import java.util.List;

@Service
public class EtatFactureService {
    @Autowired
    private EtatFactureDao etatFactureDao;

    public EtatFacture findByCode(double code) {
        return etatFactureDao.findByCode(code);
    }
    @Transactional
    public int deleteByCode(double code) {
        return etatFactureDao.deleteByCode(code);
    }

    public List<EtatFacture> findAll() {
        return etatFactureDao.findAll();
    }
    public int save(EtatFacture etatFacture){
        if(etatFactureDao.findByCode(etatFacture.getCode()) != null) return -1;
        else {
            etatFactureDao.save(etatFacture);
            return 1;
        }
    }
}
