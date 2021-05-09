package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.*;
import stage.sir.gestioncomptabilite.dao.DeclarationISDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.DeclarationIsObject;
import stage.sir.gestioncomptabilite.vo.DeclarationIsVo;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeclarationISService{

    public DeclarationIS findByRef(String ref) { return declarationISDao.findByRef(ref); }

    @Transactional
    public int deleteByRef(String ref) { return declarationISDao.deleteByRef(ref); }

    public DeclarationIS findByAnnee(double annee) { return declarationISDao.findByAnnee(annee); }

    public List<DeclarationIS> findBySocieteIce(String ice) { return declarationISDao.findBySocieteIce(ice); }

    public List<DeclarationIS> findByEtatDeclarationLibelle(String libelle) { return declarationISDao.findByEtatDeclarationLibelle(libelle); }

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

    public List<DeclarationIS> findAll() { return declarationISDao.findAll(); }

    public double calculMontantIS(double rf){
        List<TauxIS> tauxISList = new ArrayList<TauxIS>();
        tauxISList = tauxISService.findAll();
        Double montantC =0.0;
        for (TauxIS taux: tauxISList) {
            if (rf >= taux.getResultatFiscalMin() && rf <= taux.getResultatFiscalMax()){
                if(tauxISList.indexOf(taux) == 0){ montantC = (rf - taux.getResultatFiscalMin())* taux.getPourcentage()/100; }
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
        declarationIS.setRef(System.currentTimeMillis()+"");
        EtatDeclaration etatDeclaration = etatDeclarationService.findByLibelle("valider");
        declarationIS.setEtatDeclaration(etatDeclaration);
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        if (findByRef(declarationIS.getRef()) != null){ return -1; }
        else if(findByAnnee(declarationIS.getAnnee()) != null){ return -2; }
        else if(societe == null){ return -3; }
        else{
            double gain = 0.0, charge= 0.0, rf = 0.0;
            List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
            List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
            for (Facture fc: facturesC){ gain+= fc.getMontantHorsTaxe(); }
            for (Facture fd: facturesD){ charge+= fd.getMontantHorsTaxe(); }
            declarationIS.setTotalHTGain(gain);
            declarationIS.setTotalHTCharge(charge);
            declarationIS.setTotalHTDiff(gain - charge);
            rf = calculMontantIS(declarationIS.getTotalHTDiff());
            declarationIS.setMontantISCalcule(rf);
            List<TauxIsConfig> tauxIsConfigs = tauxISConfigService.findAll();
            for (TauxIsConfig cm: tauxIsConfigs){
                if (declarationIS.getAnnee() >= cm.getAnneeMin() && declarationIS.getAnnee() <= cm.getAnneeMax()){
                    declarationIS.setTauxIsConfig(cm); }
            }
            if(societe.getAge() > 3){
                if (declarationIS.getMontantISCalcule() < declarationIS.getTauxIsConfig().getCotisationMinimale()){
                    declarationIS.setMontantISPaye(declarationIS.getTauxIsConfig().getCotisationMinimale()); }
                else{ declarationIS.setMontantISPaye(declarationIS.getMontantISCalcule()); }
            }
            else{ declarationIS.setMontantISPaye(0.0); }
            List<TauxIS> tauxIS = tauxISService.findAll();
            for (TauxIS t: tauxIS){
                if (declarationIS.getTotalHTDiff() >= t.getResultatFiscalMin() && declarationIS.getTotalHTDiff() <= t.getResultatFiscalMax()){
                    declarationIS.setTauxIS(t); }
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
    public int saveBrouillon(DeclarationIS declarationIS) {
        declarationIS.setRef(System.currentTimeMillis()+"");
        EtatDeclaration etatDeclaration = etatDeclarationService.findByLibelle("brouillon");
        declarationIS.setEtatDeclaration(etatDeclaration);
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        if (findByRef(declarationIS.getRef()) != null){ return -1; }
        else if(findByAnnee(declarationIS.getAnnee()) != null){ return -2; }
        else if(societe == null){ return -3; }
        else{
            double gain = 0.0, charge= 0.0, rf = 0.0;
            List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
            List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
            for (Facture fc: facturesC){ gain+= fc.getMontantHorsTaxe(); }
            for (Facture fd: facturesD){ charge+= fd.getMontantHorsTaxe(); }
            declarationIS.setTotalHTGain(gain);
            declarationIS.setTotalHTCharge(charge);
            declarationIS.setTotalHTDiff(gain - charge);
            rf = calculMontantIS(declarationIS.getTotalHTDiff());
            declarationIS.setMontantISCalcule(rf);
            List<TauxIsConfig> tauxIsConfigs = tauxISConfigService.findAll();
            for (TauxIsConfig cm: tauxIsConfigs){
                if (declarationIS.getAnnee() >= cm.getAnneeMin() && declarationIS.getAnnee() <= cm.getAnneeMax()){
                    declarationIS.setTauxIsConfig(cm); }
            }
            if(societe.getAge() > 3){
                if (declarationIS.getMontantISCalcule() < declarationIS.getTauxIsConfig().getCotisationMinimale()){
                    declarationIS.setMontantISPaye(declarationIS.getTauxIsConfig().getCotisationMinimale()); }
                else{ declarationIS.setMontantISPaye(declarationIS.getMontantISCalcule()); }
            }
            else{ declarationIS.setMontantISPaye(0.0); }
            List<TauxIS> tauxIS = tauxISService.findAll();
            for (TauxIS t: tauxIS){
                if (declarationIS.getTotalHTDiff() >= t.getResultatFiscalMin() && declarationIS.getTotalHTDiff() <= t.getResultatFiscalMax()){
                    declarationIS.setTauxIS(t); }
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

    public int update(DeclarationIS declarationIS){
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);

        if(findByAnnee(declarationIS.getAnnee()) != null){ return -1; }
        else if(societe == null){ return -2; }
        else{
            double gain = 0.0, charge= 0.0, rf = 0.0;
            List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
            List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
            for (Facture fc: facturesC){ gain+= fc.getMontantHorsTaxe(); }
            for (Facture fd: facturesD){ charge+= fd.getMontantHorsTaxe(); }
            declarationIS.setTotalHTGain(gain);
            declarationIS.setTotalHTCharge(charge);
            declarationIS.setTotalHTDiff(gain - charge);
            rf = calculMontantIS(declarationIS.getTotalHTDiff());
            declarationIS.setMontantISCalcule(rf);
            List<TauxIsConfig> tauxIsConfigs = tauxISConfigService.findAll();
            for (TauxIsConfig cm: tauxIsConfigs){
                if (declarationIS.getAnnee() >= cm.getAnneeMin() && declarationIS.getAnnee() <= cm.getAnneeMax()){
                    declarationIS.setTauxIsConfig(cm); }
            }
            if(societe.getAge() > 3){
                if (declarationIS.getMontantISCalcule() < declarationIS.getTauxIsConfig().getCotisationMinimale()){
                    declarationIS.setMontantISPaye(declarationIS.getTauxIsConfig().getCotisationMinimale()); }
                else{ declarationIS.setMontantISPaye(declarationIS.getMontantISCalcule()); }
            }
            else{ declarationIS.setMontantISPaye(0.0); }
            List<TauxIS> tauxIS = tauxISService.findAll();
            for (TauxIS t: tauxIS){
                if (declarationIS.getTotalHTDiff() >= t.getResultatFiscalMin() && declarationIS.getTotalHTDiff() <= t.getResultatFiscalMax()){
                    declarationIS.setTauxIS(t); }
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
        double gain = 0.0, charge= 0.0, rf = 0.0;
        List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(decIsOb.getIceSociete(), decIsOb.getAnnee(), "credit");
        List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(decIsOb.getIceSociete(), decIsOb.getAnnee(), "debit");
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
        List<TauxIsConfig> tauxIsConfigs = tauxISConfigService.findAll();
        for (TauxIsConfig cm: tauxIsConfigs){
            if (decIsOb.getAnnee() >= cm.getAnneeMin() && decIsOb.getAnnee() <= cm.getAnneeMax()){
                decIsOb.setTauxIsConfig(cm);
            }
        }
        Societe societe = societeService.findByIce(decIsOb.getIceSociete());
        if(societe.getAge() > 3){
            if (decIsOb.getMontantISCalcule() < decIsOb.getTauxIsConfig().getCotisationMinimale()){
                decIsOb.setMontantISPaye(decIsOb.getTauxIsConfig().getCotisationMinimale());
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

    public int validerBrouillon(DeclarationIS declarationIS){
        EtatDeclaration etatDeclaration = etatDeclarationService.findByLibelle("valider");
        declarationIS.setEtatDeclaration(etatDeclaration);
        update(declarationIS);
        return 1;
    }

    public Double findTauxIS(double benefice){
        List<TauxIS> tauxISList = tauxISService.findAll();
        Double pourc = 0.0;
        for (TauxIS t: tauxISList) {
            if (benefice >= t.getResultatFiscalMin() && benefice<=t.getResultatFiscalMax()){
                pourc = t.getPourcentage();
            }
        }
        return pourc;
    }

    public void declarationIsXML(DeclarationIS declarationIS){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationIS.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File fileDecIs = new File("C://Users/hp/Downloads/DecIS-"+declarationIS.getAnnee()+".xml");
            marshaller.marshal(declarationIS, fileDecIs);
            marshaller.marshal(declarationIS, System.out);

        } catch (PropertyException e) { e.printStackTrace(); }
        catch (JAXBException e) { e.printStackTrace(); }

    }

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
    EtatDeclarationService etatDeclarationService;
    @Autowired
    EntityManager entityManager;
}
