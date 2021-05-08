package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.EtatOperationSociete;
import stage.sir.gestioncomptabilite.dao.EtatOperationSocieteDao;

import java.util.List;

@Service
public class EtatOperationSocieteService {
    public EtatOperationSociete findByRef(String ref) {
        return etatOperationSocieteDao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return etatOperationSocieteDao.deleteByRef(ref);
    }

    public List<EtatOperationSociete> findAll() {
        return etatOperationSocieteDao.findAll();
    }
    public int save(EtatOperationSociete etatOperationSociete){
        if(findByRef(etatOperationSociete.getRef())!=null){
            return -1;
        } else {
            etatOperationSocieteDao.save(etatOperationSociete);
            return 1;
        }
    }

    @Autowired
    EtatOperationSocieteDao etatOperationSocieteDao;
}
