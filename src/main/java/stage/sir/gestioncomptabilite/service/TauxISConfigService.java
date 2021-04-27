package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.TauxIsConfig;
import stage.sir.gestioncomptabilite.dao.TauxISConfigDao;

import java.util.List;

@Service
public class TauxISConfigService {
    @Autowired
    TauxISConfigDao tauxISConfigDao;

    public TauxIsConfig findByRef(String ref) {
        return tauxISConfigDao.findByRef(ref);
    }

    public List<TauxIsConfig> findAll() {
        return tauxISConfigDao.findAll();
    }

    public int save(TauxIsConfig tauxIsConfig) {
        if (findByRef(tauxIsConfig.getRef()) != null){
            return -1;
        }
        else {
            tauxISConfigDao.save(tauxIsConfig);
            return 1;
        }
    }
}
