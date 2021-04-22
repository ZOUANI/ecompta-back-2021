package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.TauxIS;
import stage.sir.gestioncomptabilite.dao.TauxISDao;

import java.util.List;

@Service
public class TauxISService {

    @Autowired
    TauxISDao tauxISDao;

    public List<TauxIS> findAll() {
        return tauxISDao.findAll();
    }

    public void save(TauxIS tauxIS) {
         tauxISDao.save(tauxIS);
    }
}