package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.bean.TauxIS;
import stage.sir.gestioncomptabilite.dao.DeclarationISDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.DeclarationIsObject;
import stage.sir.gestioncomptabilite.vo.DeclarationIsVo;

import javax.persistence.EntityManager;
import java.util.ArrayList;
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
        return entityManager.createQuery(query).getResultList();
    }

    public List<DeclarationIS> findAll() {
        return declarationISDao.findAll();
    }

    public double calculMontantIS(double rf){
        List<TauxIS> tauxISList = new ArrayList<TauxIS>();
        tauxISList = tauxISService.findAll();
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

    public int save(DeclarationIS declarationIS) {
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        if(findByAnnee(declarationIS.getAnnee()) != null){ return -1; }
        else if(societe == null){ return -2; }
        else{
            List<Facture> facturesD = new ArrayList<Facture>();
            List<Facture> facturesC = new ArrayList<Facture>();
            List<TauxIS> tauxIS = new ArrayList<TauxIS>();
            double gain = 0.0, charge= 0.0, rf = 0.0;
            facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
            facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
            for (Facture fc: facturesC){
                gain+= fc.getMontantHorsTaxe();
            }
            for (Facture fd: facturesD){
                charge+= fd.getMontantHorsTaxe();
            }

            declarationIS.setTotalHTGain(gain);
            declarationIS.setTotalHTCharge(charge);
            declarationIS.setTotalHTDiff(gain - charge);
            rf = calculMontantIS(declarationIS.getTotalHTDiff());
            declarationIS.setMontantISCalcule(rf);
            if(societe.getAge() > 3){
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
            tauxIS = tauxISService.findAll();
            for (TauxIS t: tauxIS){
                if (declarationIS.getTotalHTDiff() >= t.getResultatFiscalMin() && declarationIS.getTotalHTDiff() <= t.getResultatFiscalMax()){
                    declarationIS.setTauxIS(t);
                }
            }
            declarationISDao.save(declarationIS);
            List<Facture> factures = factureService.findBySocieteSourceIceAndAnnee(declarationIS.getSociete().getIce(), declarationIS.getAnnee());
            for (Facture f: factures){
                f.setDeclarationIS(declarationIS);
                factureService.update(f);
            }
            return 1;
        }
    }

    public DeclarationIsObject afficheDecIS(DeclarationIsObject decIsOb){
        List<Facture> facturesD = new ArrayList<Facture>();
        List<Facture> facturesC = new ArrayList<Facture>();
        Societe societe = new Societe();
        double gain = 0.0, charge= 0.0, rf = 0.0;
        facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(decIsOb.getIceSociete(), decIsOb.getAnnee(), "credit");
        facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(decIsOb.getIceSociete(), decIsOb.getAnnee(), "debit");
        for (Facture fc: facturesC){
            gain+= fc.getMontantHorsTaxe();
        }
        for (Facture fd: facturesD){
            charge+= fd.getMontantHorsTaxe();
        }
        decIsOb.setFactureC(facturesC);
        decIsOb.setFactureD(facturesD);
        decIsOb.setTotalHTGain(gain);
        decIsOb.setTotalHTCharge(charge);
        decIsOb.setTotalHTDiff(gain - charge);
        rf = calculMontantIS(decIsOb.getTotalHTDiff());
        decIsOb.setMontantISCalcule(rf);
        societe = societeService.findByIce(decIsOb.getIceSociete());
        if(societe.getAge() > 3){
            if (decIsOb.getMontantISCalcule() < 3000){
                decIsOb.setMontantISPaye(3000.0);
            }
            else{
                decIsOb.setMontantISPaye(decIsOb.getMontantISCalcule());
            }
        }
        else{
            decIsOb.setMontantISPaye(0.0);
        }
        return decIsOb;
    }
}