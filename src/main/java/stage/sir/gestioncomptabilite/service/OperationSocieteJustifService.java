package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.OperationSociete;
import stage.sir.gestioncomptabilite.bean.OperationSocieteJustif;
import stage.sir.gestioncomptabilite.dao.OperationSocieteJustifDao;

import java.util.Date;
import java.util.List;

@Service
public class OperationSocieteJustifService {
    public OperationSocieteJustif findByRef(String ref) {
        return operationSocieteJustifDao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return operationSocieteJustifDao.deleteByRef(ref);
    }

    public OperationSocieteJustif findByChemin(String chemin) {
        return operationSocieteJustifDao.findByChemin(chemin);
    }
    @Transactional
    public int deleteByChemin(String chemin) {
        return operationSocieteJustifDao.deleteByChemin(chemin);
    }

    public OperationSocieteJustif findByDateJustif(Date dateJusti) {
        return operationSocieteJustifDao.findByDateJustif(dateJusti);
    }
    @Transactional
    public int deleteByDateJustif(Date dateJustif) {
        return operationSocieteJustifDao.deleteByDateJustif(dateJustif);
    }

    public List<OperationSocieteJustif> findAll() {
        return operationSocieteJustifDao.findAll();
    }

    public int save(OperationSocieteJustif operationSocieteJustif){
        if (findByRef(operationSocieteJustif.getRef())!=null){
            return -1;
        } else {
            operationSocieteJustifDao.save(operationSocieteJustif);
            return 1;
        }

    }
    public int save2(OperationSociete operationSociete,List<OperationSocieteJustif> operationSocieteJustifs){
        if (operationSociete == null) return -1;
        for (OperationSocieteJustif operationSocieteJustif:operationSocieteJustifs) {
            if (findByRef(operationSocieteJustif.getRef())!=null){
                return -2;
            }
            else {
                operationSocieteJustif.setOperationSociete(operationSociete);
                operationSocieteJustifDao.save(operationSocieteJustif);
            }
        }
        return 1;
    }
    @Autowired
    OperationSocieteJustifDao operationSocieteJustifDao;
}
