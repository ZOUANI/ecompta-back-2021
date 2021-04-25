package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.bean.TauxIS;
import stage.sir.gestioncomptabilite.dao.DeclarationISDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.DeclarationIsVo;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class DeclarationISService {

    @Autowired
    DeclarationISDao declarationISDao;
    @Autowired
    SocieteService societeService;
    @Autowired
    TauxISService tauxISService;
    @Autowired
    FactureService factureService;
    @Autowired
    EntityManager entityManager;

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

    public List<DeclarationIS> searchCriteria(DeclarationIsVo declarationIsVo){
        String query = "SELECT d FROM DeclarationIS d WHERE 1=1";
        if(StringUtil.isNotEmpty(declarationIsVo.getRef())) {
            query+= " AND d.ref LIKE '%"+ declarationIsVo.getRef()+ "%'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getAnnee())) {
            query+= " AND d.annee = '"+ declarationIsVo.getAnnee()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getAnneeMin())) {
            query+= " AND d.annee >= '"+ declarationIsVo.getAnneeMin()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getAnneeMax())) {
            query+= " AND d.annee <= '"+ declarationIsVo.getAnneeMax()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTGain())) {
            query+= " AND d.totalHTGain = '"+ declarationIsVo.getTotalHTGain()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTGainMin())) {
            query+= " AND d.totalHTGain >= '"+ declarationIsVo.getTotalHTGainMin()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTGainMax())) {
            query+= " AND d.totalHTGain <= '"+ declarationIsVo.getTotalHTGainMax()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTCharge())) {
            query+= " AND d.totalHTCharge = '"+ declarationIsVo.getTotalHTCharge()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTChargeMin())) {
            query+= " AND d.totalHTCharge >= '"+ declarationIsVo.getTotalHTChargeMin()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTChargeMax())) {
            query+= " AND d.totalHTCharge <= '"+ declarationIsVo.getTotalHTChargeMax()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTDiff())) {
            query+= " AND d.totalHTDiff = '"+ declarationIsVo.getTotalHTDiff()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTDiffMin())) {
            query+= " AND d.totalHTDiff >= '"+ declarationIsVo.getTotalHTDiffMin()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTotalHTDiffMax())) {
            query+= " AND d.totalHTDiff <= '"+ declarationIsVo.getTotalHTDiffMax()+ "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getMontantISCalcule())){
            query+= " AND d.montantISCalcule = '"+ declarationIsVo.getMontantISCalcule() + "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getMontantISCalculeMin())){
            query+= " AND d.montantISCalcule >= '"+ declarationIsVo.getMontantISCalculeMin() + "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getMontantISCalculeMax())){
            query+= " AND d.montantISCalcule <= '"+ declarationIsVo.getMontantISCalculeMax() + "'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getMontantISPaye())) {
            query+= " AND d.montantISPaye = '" + declarationIsVo.getMontantISPaye() +"'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getMontantISPayeMin())) {
            query+= " AND d.montantISPaye >= '" + declarationIsVo.getMontantISPayeMin() +"'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getMontantISPayeMax())) {
            query+= " AND d.montantISPaye <= '" + declarationIsVo.getMontantISPayeMax() +"'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getSociete())) {
            query+= " AND d.societe.ice LIKE '%" + declarationIsVo.getSociete() +"%'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTauxIS())) {
            query+= " AND d.tauxIS.ref LIKE '%" + declarationIsVo.getTauxIS() +"%'";
        }
        if(StringUtil.isNotEmpty(declarationIsVo.getTauxIsConfig())) {
            query+= " AND d.tauxISConfig.ref LIKE '%" + declarationIsVo.getTauxIsConfig() +"%'";
        }

        return entityManager.createQuery(query).getResultList();
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
             factureService.saveFacturesIS(declarationIS, declarationIS.getFacture());
             return 1;
         }
    }
}
