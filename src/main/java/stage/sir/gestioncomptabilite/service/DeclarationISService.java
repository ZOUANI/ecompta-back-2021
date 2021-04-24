package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.bean.TauxIS;
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

    public void calculMontantIS(DeclarationIS decIS){
        List<TauxIS> tauxISList = tauxISService.findAll();
        Double montant =0.0;
        for (TauxIS taux: tauxISList) {
            if (decIS.getTotalHTDiff() >= taux.getResultatFiscalMin() && decIS.getTotalHTDiff() <= taux.getResultatFiscalMax()){
                if(tauxISList.indexOf(taux) == 0){
                    montant = (decIS.getTotalHTDiff() - taux.getResultatFiscalMin())* taux.getPourcentage()/100;
                }
                else{
                    montant = (decIS.getTotalHTDiff() - taux.getResultatFiscalMin())* taux.getPourcentage()/100;
                    for (int i = tauxISList.indexOf(taux)-1; i>=0; i--){
                        TauxIS t = tauxISList.get(i);
                        montant += (t.getResultatFiscalMax() - t.getResultatFiscalMin())* t.getPourcentage()/100;
                    }
                }
            }
        }
        decIS.setMontantISCalcule(montant);
    }

    public void affectMontantPaye(DeclarationIS declarationIS){
        Double diff = declarationIS.getTotalHTGain()-declarationIS.getTotalHTCharge();
        declarationIS.setTotalHTDiff(diff);
        calculMontantIS(declarationIS);
        if(declarationIS.getSociete().getAge() > 3){
            if (declarationIS.getMontantISCalcule() < 3000){
                declarationIS.setMontantISPaye(3000.0);
            }
            else{
                declarationIS.setMontantISPaye(declarationIS.getMontantISCalcule());
            }
        }
        else{
            declarationIS.setMontantISPaye(0.0);
        }
    }
    public int save(DeclarationIS declarationIS) {
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        TauxIS tauxIS = tauxISService.findByRef(declarationIS.getTauxIS().getRef());
        declarationIS.setTauxIS(tauxIS);
         if(findByRef(declarationIS.getRef()) != null){ return -1; }
         else if(societe == null){ return -2; }
         else if(tauxIS == null){ return -3; }
         else{
             calculMontantIS(declarationIS);
             affectMontantPaye(declarationIS);
             declarationISDao.save(declarationIS);
             return 1;
         }
    }
}
