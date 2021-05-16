package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.*;
import stage.sir.gestioncomptabilite.dao.DeclarationISDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.*;

import javax.persistence.EntityManager;
import javax.xml.bind.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeclarationISService{

    public DeclarationIS findByRef(String ref) { return declarationISDao.findByRef(ref); }

    @Transactional
    public int deleteByAnnee(double annee) {
    /*    List<Facture> factures = factureService.findByAnnee(annee);
        for (Facture f: factures){
            f.setDeclarationIS(null);
            factureService.update(f);
        }    */
        return declarationISDao.deleteByAnnee(annee);
    }

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

    public double montantPaye(DeclarationIS declarationIS){
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        double montantPaye = 0.0;
        if(societe.getAge() > 3){
            if (declarationIS.getMontantISCalcule() < declarationIS.getTauxIsConfig().getCotisationMinimale()){
                montantPaye = declarationIS.getTauxIsConfig().getCotisationMinimale();
            }
            else{ montantPaye = declarationIS.getMontantISCalcule(); }
        }
        else{ declarationIS.setMontantISPaye(0.0); }
        return montantPaye;
    }
    public TauxIsConfig tauxIsConfig(DeclarationIS declarationIS){
        List<TauxIsConfig> tauxIsConfigs = tauxISConfigService.findAll();
        TauxIsConfig tauxIsConfig = new TauxIsConfig();
        for (TauxIsConfig cm: tauxIsConfigs){
            if (declarationIS.getAnnee() >= cm.getAnneeMin() && declarationIS.getAnnee() <= cm.getAnneeMax()){
                tauxIsConfig = cm; }
        }
        return tauxIsConfig;
    }
    public TauxIS tauxIS(DeclarationIS declarationIS){
        List<TauxIS> tauxIS = tauxISService.findAll();
        TauxIS tauxIs = new TauxIS();
        for (TauxIS t: tauxIS){
            if (declarationIS.getTotalHTDiff() >= t.getResultatFiscalMin() && declarationIS.getTotalHTDiff() <= t.getResultatFiscalMax()){
                tauxIs = t; }
        }
        return tauxIs;
    }

    public int update(DeclarationIS declarationIS){
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        EtatDeclaration etatDeclaration = etatDeclarationService.findByLibelle(declarationIS.getEtatDeclaration().getLibelle());
        declarationIS.setEtatDeclaration(etatDeclaration);
        if(societe == null){ return -2; }
        else if(etatDeclaration == null){ return -3; }
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

    public DeclarationIsObject afficheObject(String ice, double annee){
        DeclarationIsObject declarationIsObject = new DeclarationIsObject();
        declarationIsObject.setAnnee(annee);
        declarationIsObject.setIceSociete(ice);
        double gain = 0.0, charge= 0.0, rf = 0.0;
        List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(ice, annee, "credit");
        List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(ice, annee, "debit");
        for (Facture fc: facturesC){
            gain+= fc.getMontantHorsTaxe();
        }
        for (Facture fd: facturesD){
            charge+= fd.getMontantHorsTaxe();
        }
        declarationIsObject.setFactureC(facturesC);
        declarationIsObject.setFactureD(facturesD);
        declarationIsObject.setTotalHTGain(gain);
        declarationIsObject.setTotalHTCharge(charge);
        declarationIsObject.setTotalHTDiff(gain - charge);
        rf = calculMontantIS(declarationIsObject.getTotalHTDiff());
        declarationIsObject.setMontantISCalcule(rf);
        List<TauxIsConfig> tauxIsConfigs = tauxISConfigService.findAll();
        for (TauxIsConfig cm: tauxIsConfigs){
            if (declarationIsObject.getAnnee() >= cm.getAnneeMin() && declarationIsObject.getAnnee() <= cm.getAnneeMax()){
                declarationIsObject.setTauxIsConfig(cm);
            }
        }
        Societe societe = societeService.findByIce(ice);
        if(societe.getAge() > 3){
            if (declarationIsObject.getMontantISCalcule() < declarationIsObject.getTauxIsConfig().getCotisationMinimale()){
                declarationIsObject.setMontantISPaye(declarationIsObject.getTauxIsConfig().getCotisationMinimale());
            }
            else{
                declarationIsObject.setMontantISPaye(declarationIsObject.getMontantISCalcule());
            }
        }
        else{
            declarationIsObject.setMontantISPaye(0.0);
        }
        List<TauxIS> tauxIS = tauxISService.findAll();
        for (TauxIS t: tauxIS){
            if (declarationIsObject.getTotalHTDiff() >= t.getResultatFiscalMin() && declarationIsObject.getTotalHTDiff() <= t.getResultatFiscalMax()){
                declarationIsObject.setTauxIS(t); }
        }
        return declarationIsObject;
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

    public void declarationIsToXML(DeclarationIS declarationIS){
        DeclarationIsXml decXml = convertToDecIsXml(declarationIS);
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationIsXml.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File fileDecIs = new File("C://Users/hp/Desktop/DecIS_XML/DecIS-"+declarationIS.getAnnee()+".xml");
            marshaller.marshal(decXml, fileDecIs);
            marshaller.marshal(decXml, System.out);

        } catch (PropertyException e) { e.printStackTrace(); }
        catch (JAXBException e) { e.printStackTrace(); }
    }

    public DeclarationIsXml XmlToDecIS(String fileName){
        DeclarationIsXml declarationIsXml = new DeclarationIsXml();
        //DeclarationIS declarationIS = new DeclarationIS();
        try {
            File file = new File("C://Users/hp/Desktop/DecIS_XML/" + fileName);
            JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationIsXml.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            declarationIsXml = (DeclarationIsXml) unmarshaller.unmarshal(file);
            //declarationIS = convertToDecIs(declarationIsXml);
            declarationIsXml.toString(); System.out.println(declarationIsXml);
        } catch (PropertyException e) { e.printStackTrace(); }
        catch (JAXBException e) { e.printStackTrace(); }

        return declarationIsXml;
    }

    public DeclarationIsXml convertToDecIsXml(DeclarationIS declarationIS){
        DeclarationIsXml decIsXml = new DeclarationIsXml();
        //List<Facture> facturesD, facturesC = new ArrayList<Facture>();
        List<Facture> factures = new ArrayList<Facture>();
        decIsXml.setId(declarationIS.getId());
        decIsXml.setRef(declarationIS.getRef());
        decIsXml.setAnnee(declarationIS.getAnnee());
        decIsXml.setTotalHTGain(declarationIS.getTotalHTGain());
        decIsXml.setTotalHTCharge(declarationIS.getTotalHTCharge());
        decIsXml.setTotalHTDiff(declarationIS.getTotalHTDiff());
        decIsXml.setMontantISCalcule(declarationIS.getMontantISCalcule());
        decIsXml.setMontantISPaye(declarationIS.getMontantISPaye());
        decIsXml.setTauxIS(declarationIS.getTauxIS());
        decIsXml.setSociete(declarationIS.getSociete());
        decIsXml.setTauxIsConfig(declarationIS.getTauxIsConfig());
        decIsXml.setEtatDeclaration(declarationIS.getEtatDeclaration());
        /* facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
        facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
        decIsXml.setFacturesD(facturesD);
        decIsXml.setFacturesC(facturesC); */
        factures = factureService.findBySocieteSourceIceAndAnnee(declarationIS.getSociete().getIce(), declarationIS.getAnnee());
        decIsXml.setFactures(factures);
        return decIsXml;
    }
    public DeclarationIS convertToDecIs(DeclarationIsXml dexIsXml){
        DeclarationIS declarationIS = new DeclarationIS();
        declarationIS.setId(dexIsXml.getId());
        declarationIS.setRef(dexIsXml.getRef());
        declarationIS.setAnnee(dexIsXml.getAnnee());
        declarationIS.setTotalHTGain(dexIsXml.getTotalHTGain());
        declarationIS.setTotalHTCharge(dexIsXml.getTotalHTCharge());
        declarationIS.setTotalHTDiff(dexIsXml.getTotalHTDiff());
        declarationIS.setMontantISCalcule(dexIsXml.getMontantISCalcule());
        declarationIS.setMontantISPaye(dexIsXml.getMontantISPaye());
        declarationIS.setTauxIS(dexIsXml.getTauxIS());
        declarationIS.setSociete(dexIsXml.getSociete());
        declarationIS.setTauxIsConfig(dexIsXml.getTauxIsConfig());
        declarationIS.setEtatDeclaration(dexIsXml.getEtatDeclaration());
        return declarationIS;
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

    public int save22(String ice, double annee, String etat) {
        DeclarationIS declarationIS = new DeclarationIS();
        declarationIS.setAnnee(annee);
        Societe societe = societeService.findByIce(ice);
        declarationIS.setSociete(societe);
        declarationIS.setRef(System.currentTimeMillis()+"");
        EtatDeclaration etatDeclaration = etatDeclarationService.findByLibelle(etat);
        declarationIS.setEtatDeclaration(etatDeclaration);
        if (findByRef(declarationIS.getRef()) != null){ return -1; }
        else if(findByAnnee(declarationIS.getAnnee()) != null){ return -2; }
        else if(societe == null){ return -3; }
        else if(etatDeclaration == null){ return -4; }
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
            declarationIS.setTauxIsConfig(tauxIsConfig(declarationIS));
            declarationIS.setMontantISPaye(montantPaye(declarationIS));
            declarationIS.setTauxIS(tauxIS(declarationIS));
            declarationISDao.save(declarationIS);
            List<Facture> factures = factureService.findBySocieteSourceIceAndAnnee(declarationIS.getSociete().getIce(), declarationIS.getAnnee());
            for (Facture f: factures){
                f.setDeclarationIS(declarationIS);
                factureService.update(f);
            }
            return 1;
        }
    }

}
