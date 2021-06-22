package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.Cpc;
import stage.sir.gestioncomptabilite.bean.CpcFacture;
import stage.sir.gestioncomptabilite.dao.CpcFactureDao;

@Service
public class CpcFactureService {

    public int deleteCpcFacturesByCpcId(Long id) {

        return cpcFactureDao.deleteCpcFacturesByCpcId(id);
    }

    @Autowired
    CpcFactureDao cpcFactureDao;
    public int  save(CpcFacture cpcFacture) {
         cpcFactureDao.save(cpcFacture);
        return 1;
    }
    public int update(CpcFacture cpcFacture){
        cpcFactureDao.save(cpcFacture);
        return 1;
    }
}
