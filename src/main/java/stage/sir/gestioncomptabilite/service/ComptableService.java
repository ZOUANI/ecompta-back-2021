package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Comptable;
import stage.sir.gestioncomptabilite.dao.ComptableDao;

import java.util.List;

@Service
public class ComptableService {
    @Autowired
    private ComptableDao comptableDao;

    public Comptable findByCode(String code) {
        return comptableDao.findByCode(code);
    }
    @Transactional
    public int deleteByCode(String code) {
        return comptableDao.deleteByCode(code);
    }

    public List<Comptable> findAll() {
        return comptableDao.findAll();
    }
    public int save(Comptable comptable){
        if(comptableDao.findByCode(comptable.getCode()) != null) return -1;
        else{
            comptableDao.save(comptable);
            return 1;
        }
    }
}
