package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.TauxIS;
import stage.sir.gestioncomptabilite.dao.TauxISDao;

import java.util.List;

@Service
public class TauxISService {

    @Autowired
    TauxISDao tauxISDao;

    public TauxIS findByRef(String ref) {
        return tauxISDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return tauxISDao.deleteByRef(ref);
    }


    public List<TauxIS> findAll() {
        return tauxISDao.findAll();
    }

    public int save(TauxIS tauxIS) {
        if (findByRef(tauxIS.getRef()) != null){
            return -1;
        }else {
            tauxISDao.save(tauxIS);
            return 1;
        }
    }
    public Double findTauxIs(Double benefice){
        List<TauxIS> tauxISList = tauxISDao.findAll();
        Double pourc  = 1.0;
        for (TauxIS t:tauxISList) {
            if (benefice >= t.getResultatFiscalMin() && benefice<=t.getResultatFiscalMax()){
                pourc = t.getPourcentage();
            }
        }
        return pourc;
    }
}