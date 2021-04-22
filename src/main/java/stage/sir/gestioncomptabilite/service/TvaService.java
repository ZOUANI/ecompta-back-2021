package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Tva;
import stage.sir.gestioncomptabilite.dao.TvaDao;

import java.util.List;

@Service
public class TvaService {
    @Autowired
    private TvaDao tvaDao;

    public Tva findByRef(String ref) {
        return tvaDao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return tvaDao.deleteByRef(ref);
    }

    public List<Tva> findAll() {
        return tvaDao.findAll();
    }
    public int save(Tva tva){
        tvaDao.save(tva);
        return 1;
    }
}