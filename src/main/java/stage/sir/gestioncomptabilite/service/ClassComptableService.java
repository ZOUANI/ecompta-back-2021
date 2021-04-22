package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.ClassComptable;
import stage.sir.gestioncomptabilite.dao.ClassComptableDao;

import java.util.List;

@Service
public class ClassComptableService {

    @Autowired
    ClassComptableDao classComptableDao;

    public ClassComptable findByRef(String ref) {
        return classComptableDao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return classComptableDao.deleteByRef(ref);
    }


    public List<ClassComptable> findAll() {
        return classComptableDao.findAll();
    }
}
