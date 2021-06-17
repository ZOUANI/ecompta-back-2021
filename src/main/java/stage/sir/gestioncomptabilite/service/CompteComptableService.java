package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.CompteComptable;
import stage.sir.gestioncomptabilite.bean.SousClasseComptable;
import stage.sir.gestioncomptabilite.dao.CompteComptableDao;

import java.util.List;

@Service
public class CompteComptableService {
    @Autowired
    CompteComptableDao compteComptableDao;
    @Autowired
    SousClasseComptableService sousClasseComptableService;

    public List<CompteComptable> findBySousClasseComptableNumero(int numero) {
        return compteComptableDao.findBySousClasseComptableNumero(numero);
    }

    public List<CompteComptable> findAll() {
        return compteComptableDao.findAll();
    }

   public int  save(SousClasseComptable sousClasseComptable, List<CompteComptable> listeCompteComptable){
        for(CompteComptable compteComptable:listeCompteComptable){
            compteComptable.setSousClasseComptable(sousClasseComptable);
            compteComptableDao.save(compteComptable);

        }
        return  1;
    }
    @Transactional
    public int deleteByCode(String code) {
        return compteComptableDao.deleteByCode(code);
    }
    @Transactional
    public int deleteBySousClasseComptableNumero(int numero) {
        return compteComptableDao.deleteBySousClasseComptableNumero(numero);
    }

    public int saveAlone(CompteComptable compteComptable,int numero){
        SousClasseComptable sec = sousClasseComptableService.findByNumero(numero);
        compteComptable.setSousClasseComptable(sec);
        compteComptableDao.save(compteComptable);
        return 1;
    }
    public CompteComptable findByCode(String code) {
        return compteComptableDao.findByCode(code);
    }
    public int update(CompteComptable compteComptable){
        compteComptableDao.save(compteComptable);
        return 1;
    }
}
