package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.SocieteDao;

import java.util.List;

@Service
public class SocieteService {

    @Autowired
    SocieteDao societeDao;

    public Societe findByIce(String ice) {
        return societeDao.findByIce(ice);
    }

    @Transactional
    public int deleteByIce(String ice) {
        return societeDao.deleteByIce(ice);
    }

    public List<Societe> findAll() {
        return societeDao.findAll();
    }

    public int save(Societe societe) {
        if(findByIce(societe.getIce()) != null){
            return -1;
        }
        else{
            societeDao.save(societe);
            return 1;
        }
    }
}