package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.ClasseComptable;
import stage.sir.gestioncomptabilite.bean.SousClasseComptable;
import stage.sir.gestioncomptabilite.dao.SousClasseComptableDao;

import java.util.List;

@Service
public class SousClasseComptableService {
    @Autowired
    SousClasseComptableDao sousClasseComptableDao;
    @Autowired
    CompteComptableService  compteComptableService;
    @Autowired
    ClassComptableService classComptableService;

    public List<SousClasseComptable> findByClasseComptableNumero(int numero) {
        return sousClasseComptableDao.findByClasseComptableNumero(numero);
    }

    public SousClasseComptable findByNumero(int numero) {
        return sousClasseComptableDao.findByNumero(numero);
    }

    public List<SousClasseComptable> findAll() {
        return sousClasseComptableDao.findAll();
    }

   public int  save(ClasseComptable classeComptable, List<SousClasseComptable> listsection){
       for (SousClasseComptable sousClasseComptable : listsection){
           if (sousClasseComptable.getListeCompteComptable()==null){return -3;}
           sousClasseComptable.setClasseComptable(classeComptable);
           sousClasseComptableDao.save(sousClasseComptable);
           compteComptableService.save(sousClasseComptable, sousClasseComptable.getListeCompteComptable());
       }
        return 1;
    }

    @Transactional
    public int deleteByClasseComptableNumero(int numero) {
        List<SousClasseComptable> sousClasseComptables = sousClasseComptableDao.findByClasseComptableNumero(numero);
        for (SousClasseComptable sousClasseComptable : sousClasseComptables) {
            compteComptableService.deleteBySousClasseComptableNumero(sousClasseComptable.getNumero());
        }
        return sousClasseComptableDao.deleteByClasseComptableNumero(numero);
    }
    @Transactional
    public int deleteByNumero(int numero) {
        return sousClasseComptableDao.deleteByNumero(numero) + compteComptableService.deleteBySousClasseComptableNumero(numero);
    }
    public int saveAlone(SousClasseComptable sousClasseComptable, int numero){
        ClasseComptable cls = classComptableService.findByNumero(numero);
        sousClasseComptable.setClasseComptable(cls);
        sousClasseComptableDao.save(sousClasseComptable);
        return 1;
    }
    public int update(SousClasseComptable sousClasseComptable){
        sousClasseComptableDao.save(sousClasseComptable);
        return 1;
    }
}
