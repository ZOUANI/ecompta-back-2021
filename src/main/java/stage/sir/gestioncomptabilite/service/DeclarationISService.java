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

    public DeclarationIS findBySocieteIceAndAnnee(String ice, double annee) { return declarationISDao.findBySocieteIceAndAnnee(ice, annee); }

    @Transactional
    public int deleteBySocieteIceAndAnnee(String ice, double annee) {
        List<Facture> factures = factureService.findBySocieteSourceIceAndAnnee(ice, annee);
        for (Facture f: factures){
            f.setDeclarationIS(null);
            factureService.update(f);
        }
        int acomptesDeleted = acomptesService.deleteBySocieteIceAndAnneePaye(ice, annee + 1);
        return declarationISDao.deleteBySocieteIceAndAnnee(ice, annee);
    }

    @Transactional
    public int deleteMultipleBySocieteIceAndAnnee(List<DeclarationIS> declarations) {
        int res = 0;
        for (int i = 0; i < declarations.size(); i++) {
            res += deleteBySocieteIceAndAnnee(declarations.get(i).getSociete().getIce(), declarations.get(i).getAnnee());
        }
        return res;
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

    public Double calculTotalHT(List<Facture> factures){
        Double total = 0.0;
        for (Facture f: factures){ total+= f.getMontantHorsTaxe(); }
        return total;
    }

    public Double calculMontantIS(Double rf){
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

    public Double montantPaye(Double age, Double cm, Double montantCalcule){
        Double montantPaye = 0.0;
        if(age > 3){
            if (montantCalcule < cm){
                montantPaye = cm;
            }
            else{ montantPaye = montantCalcule; }
        }
        else{ montantPaye = 0.0; }
        return montantPaye;
    }

    public TauxIsConfig findTauxIsConfig(double annee){
        List<TauxIsConfig> tauxIsConfigs = tauxISConfigService.findAll();
        TauxIsConfig tauxIsConfig = new TauxIsConfig();
        for (TauxIsConfig cm: tauxIsConfigs){
            if (annee >= cm.getAnneeMin() && annee <= cm.getAnneeMax()){
                tauxIsConfig = cm; }
        }
        return tauxIsConfig;
    }
    public TauxIS findTauxIS(Double totalHTDiff){
        List<TauxIS> tauxIS = tauxISService.findAll();
        TauxIS tauxIs = new TauxIS();
        for (TauxIS t: tauxIS){
            if (totalHTDiff >= t.getResultatFiscalMin() && totalHTDiff <= t.getResultatFiscalMax()){
                tauxIs = t; }
        }
        return tauxIs;
    }

    public void setFactureDeclarationIS(DeclarationIS declarationIS){
        List<Facture> factures = factureService.findBySocieteSourceIceAndAnnee(declarationIS.getSociete().getIce(), declarationIS.getAnnee());
        for (Facture f: factures){
            f.setDeclarationIS(declarationIS);
            factureService.update(f);
        }
    }

    public int update(DeclarationIS declarationIS){
        Societe societe = societeService.findByIce(declarationIS.getSociete().getIce());
        declarationIS.setSociete(societe);
        EtatDeclaration etatDeclaration = etatDeclarationService.findByLibelle(declarationIS.getEtatDeclaration().getLibelle());
        declarationIS.setEtatDeclaration(etatDeclaration);
        if(societe == null){ return -2; }
        else if(etatDeclaration == null){ return -3; }
        else{
            List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
            List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
            declarationIS.setTotalHTGain(calculTotalHT(facturesC));
            declarationIS.setTotalHTCharge(calculTotalHT(facturesD));
            declarationIS.setTotalHTDiff(declarationIS.getTotalHTGain() - declarationIS.getTotalHTCharge());
            declarationIS.setMontantISCalcule(calculMontantIS(declarationIS.getTotalHTDiff()));
            declarationIS.setTauxIsConfig(findTauxIsConfig(declarationIS.getAnnee()));
            declarationIS.setMontantISPaye(montantPaye(declarationIS.getSociete().getAge(), declarationIS.getTauxIsConfig().getCotisationMinimale(), declarationIS.getMontantISCalcule()));
            declarationIS.setTauxIS(findTauxIS(declarationIS.getTotalHTDiff()));
            declarationISDao.save(declarationIS);
            setFactureDeclarationIS(declarationIS);
            return 1;
        }
    }

    public DeclarationIsObject afficheObject(String ice, double annee){
        DeclarationIsObject declarationIsObject = new DeclarationIsObject();
        declarationIsObject.setAnnee(annee);
        declarationIsObject.setSociete(societeService.findByIce(ice));
        List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(ice, annee, "credit");
        List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(ice, annee, "debit");
        declarationIsObject.setFactureC(facturesC);
        declarationIsObject.setFactureD(facturesD);
        declarationIsObject.setTotalHTGain(calculTotalHT(facturesC));
        declarationIsObject.setTotalHTCharge(calculTotalHT(facturesD));
        declarationIsObject.setTotalHTDiff(declarationIsObject.getTotalHTGain() - declarationIsObject.getTotalHTCharge());
        declarationIsObject.setMontantISCalcule(calculMontantIS(declarationIsObject.getTotalHTDiff()));
        declarationIsObject.setTauxIsConfig(findTauxIsConfig(declarationIsObject.getAnnee()));
        declarationIsObject.setMontantISPaye(montantPaye(declarationIsObject.getSociete().getAge(), declarationIsObject.getTauxIsConfig().getCotisationMinimale(), declarationIsObject.getMontantISCalcule()));
        declarationIsObject.setTauxIS(findTauxIS(declarationIsObject.getTotalHTDiff()));
        DeclarationIS declarationIS = findBySocieteIceAndAnnee(ice, annee);
        if (declarationIS != null){
            declarationIsObject.setDeclarationIS(declarationIS);
        } else { declarationIsObject.setDeclarationIS(null); }

        return declarationIsObject;
    }

    public DeclarationIsObject afficheDecIS(DeclarationIsObject decIsOb){
        List<Facture> facturesD = new ArrayList<Facture>();
        List<Facture> facturesC = new ArrayList<Facture>();
        Societe societe = societeService.findByIce(decIsOb.getSociete().getIce());
        decIsOb.setSociete(societe);
        facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(decIsOb.getSociete().getIce(), decIsOb.getAnnee(), "credit");
        facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(decIsOb.getSociete().getIce(), decIsOb.getAnnee(), "debit");
        decIsOb.setFactureC(facturesC);
        decIsOb.setFactureD(facturesD);
        decIsOb.setTotalHTGain(calculTotalHT(facturesC));
        decIsOb.setTotalHTCharge(calculTotalHT(facturesD));
        decIsOb.setTotalHTDiff(decIsOb.getTotalHTGain() - decIsOb.getTotalHTCharge());
        decIsOb.setMontantISCalcule(calculMontantIS(decIsOb.getTotalHTDiff()));
        decIsOb.setTauxIsConfig(findTauxIsConfig(decIsOb.getAnnee()));
        decIsOb.setMontantISPaye(montantPaye(decIsOb.getSociete().getAge(), decIsOb.getTauxIsConfig().getCotisationMinimale(), decIsOb.getMontantISCalcule()));
        decIsOb.setTauxIS(findTauxIS(decIsOb.getTotalHTDiff()));
        DeclarationIS declarationIS = findBySocieteIceAndAnnee(decIsOb.getSociete().getIce(), decIsOb.getAnnee());
        if (declarationIS != null){
            decIsOb.setDeclarationIS(declarationIS);
        } else { decIsOb.setDeclarationIS(null); }

        return decIsOb;
    }

    public int declarationIsToXML(DeclarationIS declarationIS){
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

        return 1;
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


    public int save(String ice, double annee, String etat) {
        DeclarationIS declarationIS = new DeclarationIS();
        Acomptes acomptes = new Acomptes();
        declarationIS.setAnnee(annee);
        Societe societe = societeService.findByIce(ice);
        declarationIS.setSociete(societe);
        declarationIS.setRef(System.currentTimeMillis()+"");
        EtatDeclaration etatDeclaration = etatDeclarationService.findByLibelle(etat);
        declarationIS.setEtatDeclaration(etatDeclaration);
        if (findByRef(declarationIS.getRef()) != null){ return -1; }
        else if(findBySocieteIceAndAnnee(declarationIS.getSociete().getIce(), declarationIS.getAnnee()) != null){ return -2; }
        else if(societe == null){ return -3; }
        else if(etatDeclaration == null){ return -4; }
        else{
            List<Facture> facturesC = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "credit");
            List<Facture> facturesD = factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(declarationIS.getSociete().getIce(), declarationIS.getAnnee(), "debit");
            declarationIS.setTotalHTGain(calculTotalHT(facturesC));
            declarationIS.setTotalHTCharge(calculTotalHT(facturesD));
            declarationIS.setTotalHTDiff(declarationIS.getTotalHTGain() - declarationIS.getTotalHTCharge());
            if (declarationIS.getTotalHTDiff() > 0){
                declarationIS.setTauxIS(findTauxIS(declarationIS.getTotalHTDiff()));
                declarationIS.setMontantISCalcule(calculMontantIS(declarationIS.getTotalHTDiff()));
            } else{
                declarationIS.setTauxIS(null);
                declarationIS.setMontantISCalcule(0.0);
            }
            declarationIS.setTauxIsConfig(findTauxIsConfig(declarationIS.getAnnee()));
            declarationIS.setMontantISPaye(montantPaye(declarationIS.getSociete().getAge(), declarationIS.getTauxIsConfig().getCotisationMinimale(), declarationIS.getMontantISCalcule()));

            if (declarationIS.getMontantISCalcule() > declarationIS.getTauxIsConfig().getCotisationMinimale()){
                acomptes.setAnneePaye(declarationIS.getAnnee() + 1);
                acomptes.setNumero(1);
                acomptes.setMontant(declarationIS.getMontantISPaye() / 4);
                acomptes.setSociete(declarationIS.getSociete());
                acomptesService.save(acomptes);
                declarationIS.setAcomptes(acomptes);
                declarationIS.setTotalPaye(declarationIS.getMontantISPaye() + declarationIS.getAcomptes().getMontant());
            } else {
                declarationIS.setTotalPaye(declarationIS.getMontantISPaye());
                declarationIS.setAcomptes(null);
            }
            declarationISDao.save(declarationIS);
            setFactureDeclarationIS(declarationIS);
            return 1;
        }
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
    AcomptesService acomptesService;
    @Autowired
    EntityManager entityManager;

}
