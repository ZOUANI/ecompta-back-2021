package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationTva;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.bean.TypeDeclarationTva;
import stage.sir.gestioncomptabilite.dao.DeclarationTvaDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.*;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

@Service
public class DeclarationTvaService {
    public DeclarationTva findByRef(String ref) {
        return declarationTvaDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        List<Facture> factures = new ArrayList<Facture>();
        DeclarationTva declarationTva = findByRef(ref);
        if (declarationTva.getTypeDeclarationTva().getLibelle().equals("TDTV1")){
            factures = factureService.findBySocieteSourceIceAndAnneeAndTrim(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getTrim());
        }else {
            factures = factureService.findBySocieteSourceIceAndAnneeAndMois(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getMois());
        }
        for (Facture facture:factures) {
            facture.setDeclarationTva(null);
            factureService.update(facture);
        }
        return declarationTvaDao.deleteByRef(ref);
    }
    @Transactional
    public int deleteMultipleByref(List<DeclarationTva> declarationTvas){
        int res = 0;
        for (DeclarationTva declarationTva:declarationTvas) {
            res += deleteByRef(declarationTva.getRef());
        }
        return res;
    }

    public List<DeclarationTva> findBySocieteIce(String ice) {
        return declarationTvaDao.findBySocieteIce(ice);
    }

    @Transactional
    public int deleteBySocieteIce(String ice) {
        return declarationTvaDao.deleteBySocieteIce(ice);
    }

    public List<DeclarationTva> findByTypeDeclarationTvaRef(String ref) {
        return declarationTvaDao.findByTypeDeclarationTvaRef(ref);
    }

    @Transactional
    public int deleteByTypeDeclarationTvaRef(String ref) {
        return declarationTvaDao.deleteByTypeDeclarationTvaRef(ref);
    }

    public List<DeclarationTva> findAll() {
        return declarationTvaDao.findAll();
    }

    public int save(DeclarationTva declarationTva){
        if (declarationTva.getEtatDeclaration() == null){
            declarationTva.setRef(System.currentTimeMillis()+"");
        }
        Societe s = societeService.findByIce(declarationTva.getSociete().getIce());
        declarationTva.setSociete(s);
        TypeDeclarationTva t = typeDeclarationTvaService.findByRef(declarationTva.getTypeDeclarationTva().getRef());
        declarationTva.setTypeDeclarationTva(t);
        if (declarationTva.getTypeDeclarationTva().getLibelle().equals("trimestrielle")){
            if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getTrim())!= null){
                declarationTva.setId(declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getTrim()).getId());
            }
        }else {
            if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getMois())!= null){
                declarationTva.setId(declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getMois()).getId());
            }
        }

        if (s==null){
            return  -2;
        }
        else if (t==null){
            return -3;
        }
        else if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(declarationTva.getSociete().getIce(),"Valider",declarationTva.getAnnee(),declarationTva.getTrim())!= null){
            return -4;
        }
        else if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(declarationTva.getSociete().getIce(),"Valider",declarationTva.getAnnee(),declarationTva.getMois())!= null){
            return -5;
        }
        else {
            declarationTvaDao.save(declarationTva);
            List<Facture> factures = new ArrayList<Facture>();
            double tvac = 0,tvap = 0,dtva = 0;
            if (declarationTva.getTypeDeclarationTva().getLibelle().equals("trimestrielle")){
                factures = factureService.findBySocieteSourceIceAndAnneeAndTrim(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getTrim());
            }else {
                factures = factureService.findBySocieteSourceIceAndAnneeAndMois(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getMois());
            }
            DeclarationTva myDeclarationTva = findByRef(declarationTva.getRef());
            for (Facture facture:factures){
                if (facture.getTypeOperation().equals("Credit")){
                    tvac += facture.getMontantTVA();
                    facture.setDeclarationTva(myDeclarationTva);
                    factureService.update(facture);
                }else {
                    tvap += facture.getMontantTVA();
                    facture.setDeclarationTva(myDeclarationTva);
                    factureService.update(facture);
                }
            }
            myDeclarationTva.setTvacollecter(tvac);
            myDeclarationTva.setTvaperdue(tvap);
            dtva = tvac - tvap;
            myDeclarationTva.setDifftva(dtva);
            myDeclarationTva.setEtatDeclaration(etatDeclarationService.findByRef("Valider"));
            declarationTvaDao.save(myDeclarationTva);
            return 1;
        }
    }
    public int savebrouillon(DeclarationTva declarationTva){
        if (declarationTva.getEtatDeclaration() == null){
            declarationTva.setRef(System.currentTimeMillis()+"");
        }
        Societe s = societeService.findByIce(declarationTva.getSociete().getIce());
        declarationTva.setSociete(s);
        TypeDeclarationTva t = typeDeclarationTvaService.findByRef(declarationTva.getTypeDeclarationTva().getRef());
        declarationTva.setTypeDeclarationTva(t);
        if (declarationTva.getTypeDeclarationTva().getLibelle().equals("trimestrielle")){
            if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getTrim())!= null){
                declarationTva.setId(declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getTrim()).getId());
            }
        }else {
            if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getMois())!= null){
                declarationTva.setId(declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(declarationTva.getSociete().getIce(),"Brouillon",declarationTva.getAnnee(),declarationTva.getMois()).getId());
            }
        }
        if (s==null){
            return  -2;
        }
        else if (t==null){
            return -3;
        }
        else if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(declarationTva.getSociete().getIce(),"Valider",declarationTva.getAnnee(),declarationTva.getTrim())!= null){
            return -4;
        }
        else if (declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(declarationTva.getSociete().getIce(),"Valider",declarationTva.getAnnee(),declarationTva.getMois())!= null){
            return -5;
        }
        else {
            declarationTvaDao.save(declarationTva);
            List<Facture> factures = new ArrayList<Facture>();
            double tvac = 0,tvap = 0,dtva = 0;
            if (declarationTva.getTypeDeclarationTva().getLibelle().equals("trimestrielle")){
                factures = factureService.findBySocieteSourceIceAndAnneeAndTrim(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getTrim());
            }else {
                factures = factureService.findBySocieteSourceIceAndAnneeAndMois(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getMois());
            }
            DeclarationTva myDeclarationTva = findByRef(declarationTva.getRef());
            for (Facture facture:factures){
                if (facture.getTypeOperation().equals("Credit")){
                    tvac += facture.getMontantTVA();
                    facture.setDeclarationTva(myDeclarationTva);
                    factureService.update(facture);
                }else {
                    tvap += facture.getMontantTVA();
                    facture.setDeclarationTva(myDeclarationTva);
                    factureService.update(facture);
                }
            }
            myDeclarationTva.setTvacollecter(tvac);
            myDeclarationTva.setTvaperdue(tvap);
            dtva = tvac - tvap;
            myDeclarationTva.setDifftva(dtva);
            myDeclarationTva.setEtatDeclaration(etatDeclarationService.findByRef("Brouillon"));
            declarationTvaDao.save(myDeclarationTva);
            return 1;
        }
    }

    public DeclarationTvaVo2 findfacturesandcalcultva(DeclarationTvaVo1 declarationTvaVo1){
        DeclarationTvaVo2 declarationTvaVo2 = new DeclarationTvaVo2();
        List<Facture> facturesvente = new ArrayList<Facture>();
        List<Facture> facturesachat = new ArrayList<Facture>();
        double tvacollecter = 0,tvadeductible = 0,differencetva = 0;
        if (declarationTvaVo1.getTypedeclarationtva().equals("TDTV1")){
            facturesvente = factureService.findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getTrim(),"Credit");
            facturesachat = factureService.findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getTrim(),"Debit");
        }else {
            facturesvente = factureService.findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getMois(),"Credit");
            facturesachat = factureService.findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getMois(),"Debit");
        }
        for (Facture facture:facturesvente) {
            tvacollecter += facture.getMontantTVA();
        }
        for (Facture facture:facturesachat) {
            tvadeductible += facture.getMontantTVA();
        }
        declarationTvaVo2.setListfacturevente(facturesvente);
        declarationTvaVo2.setListfactureachat(facturesachat);
        declarationTvaVo2.setTvacollecter(tvacollecter);
        declarationTvaVo2.setTvadeductible(tvadeductible);
        differencetva = tvacollecter - tvadeductible;
        declarationTvaVo2.setDifferencetva(differencetva);
        return declarationTvaVo2;
    }
    public List<DeclarationTva> findByCriteria(DeclarationTvaCriteria declarationTvaCriteria){
        String query = "SELECT d FROM DeclarationTva d WHERE 1=1";
        if (StringUtil.isNotEmpty(declarationTvaCriteria.getSocieteref())){
            query += " AND d.societe.ice = '" + declarationTvaCriteria.getSocieteref() + "'";
        }
        if (StringUtil.isNotEmpty(declarationTvaCriteria.getTypedeclarationtva())){
            query += " AND d.typeDeclarationTva.ref = '" + declarationTvaCriteria.getTypedeclarationtva() + "'";
        }
        if (StringUtil.isNotEmpty(declarationTvaCriteria.getAnnee())){
            query += " AND d.annee = '" + declarationTvaCriteria.getAnnee() + "'";
        }
        if (StringUtil.isNotEmpty(declarationTvaCriteria.getMois())){
            query += " AND d.mois = '" + declarationTvaCriteria.getMois() + "'";
        }
        if (StringUtil.isNotEmpty(declarationTvaCriteria.getTrim())){
            query += " AND d.trim = '" + declarationTvaCriteria.getMois() + "'";
        }
        if (StringUtil.isNotEmpty(declarationTvaCriteria.getDifftvamin())){
            query += " AND d.difftva >= '" + declarationTvaCriteria.getDifftvamin() + "'";
        }
        if (StringUtil.isNotEmpty(declarationTvaCriteria.getDifftvamax())){
            query += " AND d.difftva <= '" + declarationTvaCriteria.getDifftvamax() + "'";
        }
        return entityManager.createQuery(query).getResultList();
    }
    public void convertToXmlFile(DeclarationTva declarationTva){
        DeclarationReleveDeduction declarationReleveDeduction = convertdeclarationtva(declarationTva);
        try
        {
            JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationReleveDeduction.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            jaxbMarshaller.marshal(declarationReleveDeduction,new FileOutputStream("C:\\Users\\book\\Downloads\\"+declarationTva.getRef()+".xml"));
        }
        catch (JAXBException | FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }
    public DeclarationReleveDeduction convertdeclarationtva(DeclarationTva declarationTva){
        DeclarationReleveDeduction declarationReleveDeduction = new DeclarationReleveDeduction();
        ReleveDeductions releveDeductions = new ReleveDeductions();
        List<Facture> factures = new ArrayList<Facture>();
        List<Rd> rds = new ArrayList<Rd>();

        declarationReleveDeduction.setIdentifiantFiscal(declarationTva.getId());
        declarationReleveDeduction.setAnnee(declarationTva.getAnnee());
        if (declarationTva.getTypeDeclarationTva().getLibelle().equals("trimestrielle")){
            declarationReleveDeduction.setPeriode(declarationTva.getTrim());
            factures = factureService.findBySocieteSourceIceAndAnneeAndTrim(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getTrim());
        }else{
            declarationReleveDeduction.setPeriode(declarationTva.getMois());
            factures = factureService.findBySocieteSourceIceAndAnneeAndMois(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getMois());
        }
        declarationReleveDeduction.setRegime(1);
        int i =0;
        for (Facture facture:factures){
            Rd rd = new Rd(); RefF refF = new RefF(); Mp mp = new Mp();
            rd.setOrd(i);  rd.setNum(facture.getRef());  rd.setDes(facture.getLibelle()); rd.setMht(facture.getMontantHorsTaxe()); rd.setTva(facture.getMontantTVA());
            rd.setDfac(facture.getDateOperation()); rd.setDpai(facture.getDateOperation()); rd.setTtc(facture.getMontantTTC());
            refF.setIff(facture.getSocieteSource().getId()); refF.setIce(facture.getSocieteSource().getIce()); refF.setNom(facture.getSocieteSource().getRaisonSociale());
            rd.setRefF(refF);
            if (facture.getTypeOperation().equals("Credit")){
                mp.setId(1);
            } else if (facture.getTypeOperation().equals("Debit")){
                mp.setId(2);
            }
            rd.setMp(mp);
            rds.add(rd);
            i++;
        }
        releveDeductions.setRd(rds);
        declarationReleveDeduction.setReleveDeductions(releveDeductions);

        return declarationReleveDeduction;
    }
    public DeclarationReleveDeduction convertXmlfileToJavaobject(EmplacementXml emplacementXml) {
        DeclarationReleveDeduction declarationReleveDeduction = new DeclarationReleveDeduction();
        try {
            File file = new File(emplacementXml.getEmplacement());
            JAXBContext jaxbContext = JAXBContext.newInstance(DeclarationReleveDeduction.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            declarationReleveDeduction = (DeclarationReleveDeduction) jaxbUnmarshaller.unmarshal(file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return declarationReleveDeduction;
    }
    public DeclarationTvaVo1 convertDeclarationReleveDeductionToDeclarationTvaVo1(EmplacementXml emplacementXml){
        DeclarationTvaVo1 declarationTvaVo1 = new DeclarationTvaVo1();
        DeclarationReleveDeduction declarationReleveDeduction = convertXmlfileToJavaobject(emplacementXml);
        declarationTvaVo1.setSocieteref(declarationReleveDeduction.getReleveDeductions().getRd().get(0).getRefF().getIce());
        declarationTvaVo1.setAnnee(declarationReleveDeduction.getAnnee());
        if (declarationReleveDeduction.getRegime() == 1){
            declarationTvaVo1.setTypedeclarationtva("TDTV1");
            declarationTvaVo1.setTrim(declarationReleveDeduction.getPeriode());
        } else {
            declarationTvaVo1.setTypedeclarationtva("TDTV2");
            declarationTvaVo1.setMois(declarationReleveDeduction.getPeriode());
        }
        return declarationTvaVo1;
    }
    public DeclarationTvaVo2 convertDeclarationReleveDeductionToDeclarationTvaVo2(EmplacementXml emplacementXml){
        DeclarationTvaVo2 declarationTvaVo2 = new DeclarationTvaVo2();
        DeclarationReleveDeduction declarationReleveDeduction = convertXmlfileToJavaobject(emplacementXml);
        for (Rd rd:declarationReleveDeduction.getReleveDeductions().getRd()) {
            Facture facture = new Facture();
            if (rd.getMp().getId() == 1){
                facture = factureService.findByRef(rd.getNum());
                declarationTvaVo2.getListfacturevente().add(facture);
            } else if (rd.getMp().getId() == 2){
                facture = factureService.findByRef(rd.getNum());
                declarationTvaVo2.getListfactureachat().add(facture);
            }
        }

        double tvacollecter = 0, tvadeductible = 0, difftva = 0;
        for (Facture facture:declarationTvaVo2.getListfacturevente()) {
            tvacollecter += facture.getMontantTVA();
        }
        for (Facture facture:declarationTvaVo2.getListfactureachat()) {
            tvadeductible += facture.getMontantTVA();
        }
        declarationTvaVo2.setTvacollecter(tvacollecter);
        declarationTvaVo2.setTvadeductible(tvadeductible);
        difftva = tvacollecter - tvadeductible;
        declarationTvaVo2.setDifferencetva(difftva);

        return declarationTvaVo2;
    }
    public List<DeclarationTva> findByAnneeAndMois(double annee, double mois) {
        return declarationTvaDao.findByAnneeAndMois(annee, mois);
    }

    public List<DeclarationTva> findByAnneeAndTrim(double annee, double trim) {
        return declarationTvaDao.findByAnneeAndTrim(annee, trim);
    }

    public DeclarationTva findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(String refsociete, String refetatdeclaration, double annee, double trim) {
        return declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(refsociete, refetatdeclaration, annee, trim);
    }

    public DeclarationTva findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(String refsociete, String refetatdeclaration, double annee, double mois) {
        return declarationTvaDao.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(refsociete, refetatdeclaration, annee, mois);
    }

    @Autowired
    DeclarationTvaDao declarationTvaDao;
    @Autowired
    SocieteService societeService;
    @Autowired
    TypeDeclarationTvaService typeDeclarationTvaService;
    @Autowired
    FactureService factureService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    EtatDeclarationService etatDeclarationService;
}
