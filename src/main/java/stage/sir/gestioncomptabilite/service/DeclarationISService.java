package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DeclarationISDao;


import java.util.List;

@Service
public class DeclarationISService {

    @Autowired
    DeclarationISDao declarationISDao;
    @Autowired
    SocieteService societeService;
    @Autowired
    TauxISService tauxISService;

    public DeclarationIS findByAnnee(Integer annee) {
        return declarationISDao.findByAnnee(annee);
    }

    public DeclarationIS findByRef(String ref) {
        return declarationISDao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return declarationISDao.deleteByRef(ref);
    }

    @Transactional
    public int deleteByAnnee(Integer annee) {
        return declarationISDao.deleteByAnnee(annee);
    }

    public List<DeclarationIS> findBySocieteIce(String ice) {
        return declarationISDao.findBySocieteIce(ice);
    }

    @Transactional
    public int deleteBySocieteIce(String ice) {
        return declarationISDao.deleteBySocieteIce(ice);
    }

    public List<DeclarationIS> findAll() {
        return declarationISDao.findAll();
    }

    public int save(DeclarationIS declarationIS) {
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);

         if(findByAnnee(declarationIS.getAnnee()) != null){
             return -1;
         }
         else if(societe == null){
             return -2;
         }
         else{
             Double diff = declarationIS.getTotalHTGain()-declarationIS.getTotalHTCharge();
             declarationIS.setTotalHTDiff(diff);
             Double montantIsCalculed = declarationIS.getTotalHTDiff() * declarationIS.getTauxIS().getPourcentage();
             if(declarationIS.getSociete().getAge() > 3){
                 if (montantIsCalculed < declarationIS.getTauxIsConfig().getCotisationMinimale()){
                     declarationIS.setMontantISPaye(declarationIS.getTauxIsConfig().getCotisationMinimale());
                 }
                 else{
                     declarationIS.setMontantISPaye(declarationIS.getMontantISCalcule());
                 }
             }
             else{
                 declarationIS.setMontantISPaye((double) 0);
             }
             declarationISDao.save(declarationIS);
             return 1;
         }
    }
}
