package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.*;
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
    TauxISConfigService tauxISConfigService;
    @Autowired
    EntityManager entityManager;


    public DeclarationIS findByAnnee(double annee) {
        return declarationISDao.findByAnnee(annee);
    }

    public List<DeclarationIS> findBySocieteIce(String ice) {
        return declarationISDao.findBySocieteIce(ice);
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

    public double calculMontantIS(double rf){
        List<TauxIS> tauxISList = tauxISService.findAll();
        Double montantC =0.0;
        for (TauxIS taux: tauxISList) {
            if (rf >= taux.getResultatFiscalMin() && rf <= taux.getResultatFiscalMax()){
                if(tauxISList.indexOf(taux) == 0){
                    montantC = (rf - taux.getResultatFiscalMin())* taux.getPourcentage()/100;
                }
                else{
                    montantC = (rf - taux.getResultatFiscalMin())* taux.getPourcentage()/100;
                    for (int i = tauxISList.indexOf(taux)-1; i>=0; i--){
                        TauxIS t = tauxISList.get(i);
                        montantC += (t.getResultatFiscalMax() - t.getResultatFiscalMin())* t.getPourcentage()/100;
                    }
                }
            }
        }
        return montantC;
    }

    /*public double affectMontantPaye(DeclarationIS declarationIS){
        Double montantP =0.0;
        if(declarationIS.getSociete().getAge() > 3){
            if (declarationIS.getMontantISCalcule() < 3000){
                montantP = 3000.0;
            }
            else{
                montantP = declarationIS.getMontantISCalcule();
            }
        }
        else{
            montantP =0.0;
        }
        return montantP;
    }*/
    public int save(DeclarationIS declarationIS) {
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        if(findByAnnee(declarationIS.getAnnee()) != null){ return -1; }
        if(societe == null){ return -2; }
        else{
            double gain = 0;
            double charge = 0;
            List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
            for (Facture f: facturesC){
                    gain+= f.getMontantHorsTaxe();
            }
            List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
            for (Facture f: facturesD){
                    charge+= f.getMontantHorsTaxe();
            }
            declarationIS.setTotalHTGain(gain);
            declarationIS.setTotalHTCharge(charge);
            declarationIS.setTotalHTDiff(declarationIS.getTotalHTGain()-declarationIS.getTotalHTCharge());
            declarationIS.setMontantISCalcule(calculMontantIS(declarationIS.getTotalHTDiff()));
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
            declarationISDao.save(declarationIS);
            List<Facture> factures = factureService.findBySocieteSourceIceAndAnnee(declarationIS.getSociete().getIce(), declarationIS.getAnnee());
            for (Facture f: factures){
                f.setDeclarationIS(declarationIS);
                factureService.update(f);
            }
            factureService.saveFacturesIS(declarationIS, declarationIS.getFacture());
            return 1;
        }
    }

    /*public int save(DeclarationIS declarationIS) {
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        TauxIS tauxIS = tauxISService.findByRef(declarationIS.getTauxIS().getRef());
        declarationIS.setTauxIS(tauxIS);
        TauxIsConfig tauxIsConfig = tauxISConfigService.findByRef(declarationIS.getTauxIsConfig().getRef());
        declarationIS.setTauxIsConfig(tauxIsConfig);
         if(findByAnnee(declarationIS.getAnnee()) != null){ return -1; }
         else if(societe == null){ return -2; }
         else if(tauxIS == null){ return -3; }
         else if(tauxIsConfig == null){ return -4; }
         else{
             declarationIS.setTotalHTDiff(declarationIS.getTotalHTGain()-declarationIS.getTotalHTCharge());
             calculMontantIS(declarationIS);
             affectMontantPaye(declarationIS);
             declarationISDao.save(declarationIS);
             List<Facture> factures = factureService.findBySocieteSourceIceAndAnnee(declarationIS.getSociete().getIce(), declarationIS.getAnnee());
             for (Facture f: factures){
                 f.setDeclarationIS(declarationIS);
                 factureService.update(f);
             }
             factureService.saveFacturesIS(declarationIS, declarationIS.getFacture());
             return 1;
         }
    }
*/
}