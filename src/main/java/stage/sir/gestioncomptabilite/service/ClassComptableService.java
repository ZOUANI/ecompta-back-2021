package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.ClasseComptable;
import stage.sir.gestioncomptabilite.dao.ClassComptableDao;

import java.util.List;

@Service
public class ClassComptableService {

    @Autowired
    ClassComptableDao classComptableDao;
    @Autowired
    SousClasseComptableService sousClasseComptableService;

    public ClasseComptable findByNumero(int numero) {
        return classComptableDao.findByNumero(numero);
    }
    @Transactional
    public int deleteByNumero(int numero) {
        return sousClasseComptableService.deleteByClasseComptableNumero(numero) + classComptableDao.deleteByNumero(numero) ;
    }

    public List<ClasseComptable> findAll() {
        return classComptableDao.findAll();
    }

    public int save(ClasseComptable classeComptable) {
        if (this.findByNumero(classeComptable.getNumero())!=null){
            return -1;
        }
        if (classeComptable.getListeSousClasseComptables()==null){return -2;}
        classComptableDao.save(classeComptable);
        sousClasseComptableService.save(classeComptable, classeComptable.getListeSousClasseComptables());
        return 1;
    }
    public int saveAlone(ClasseComptable classeComptable){
        if (this.findByNumero(classeComptable.getNumero())!=null){
            return -1;
        }
        classComptableDao.save(classeComptable);
        return 1;
    }
    public int update(ClasseComptable classeComptable){
        classComptableDao.save(classeComptable);
        return 1;
    }
}
