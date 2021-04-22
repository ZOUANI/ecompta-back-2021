package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.*;
import stage.sir.gestioncomptabilite.dao.FactureDao;
//import stage.sir.gestioncomptabilite.service.util.DateUtil;

import java.util.List;

@Service
public class FactureService {
    @Autowired
    private FactureDao factureDao;
    @Autowired
    private SocieteService societeService;
    @Autowired
    private TvaService tvaService;
    @Autowired
    private ClassComptableService comptComptableService;
    @Autowired
    private DeclarationISService declarationISService;
    @Autowired
    private DeclarationIRService declarationIRService;
    // @Autowired
   // private DeclarationTVAService declarationTVAService;

    // private DateUtil dateUtil;

    public Facture findByRef(String ref) {
        return factureDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return factureDao.deleteByRef(ref);
    }

    public List<Facture> findBySocieteSourceIceAndAnneeAndTrim(String ice, double annee, double trim) {
        return factureDao.findBySocieteSourceIceAndAnneeAndTrim(ice, annee, trim);
    }

    public List<Facture> findBySocieteSourceIceAndAnneeAndMois(String ice, double annee, double mois) {
        return factureDao.findBySocieteSourceIceAndAnneeAndMois(ice, annee, mois);
    }

    public List<Facture> findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(String ice, double annee, double trim, String typeoperation) {
        return factureDao.findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(ice, annee, trim, typeoperation);
    }

    public List<Facture> findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(String ice, double annee, double mois, String typeoperation) {
        return factureDao.findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(ice, annee, mois, typeoperation);
    }

    public List<Facture> findAll() {
        return factureDao.findAll();
    }
    public void update(Facture facture){
        factureDao.save(facture);
    }

    public int save(Facture facture) {
        Societe societeS = societeService.findByIce(facture.getSocieteSource().getIce());
        facture.setSocieteSource(societeS);
        Societe societeD = societeService.findByIce(facture.getSocieteDistination().getIce());
        facture.setSocieteDistination(societeD);
        Tva tv = tvaService.findByRef(facture.getTva().getRef());
        facture.setTva(tv);
        ClassComptable cpt = comptComptableService.findByRef(facture.getClassComptable().getRef());
        facture.setClassComptable(cpt);
        DeclarationIR ir = declarationIRService.findByRef(facture.getDeclarationIR().getRef());
        facture.setDeclarationIR(ir);
        DeclarationIS is = declarationISService.findByRef(facture.getDeclarationIS().getRef());
        facture.setDeclarationIS(is);
       // DeclarationTVA dtva = declarationTVAService.findByRef(facture.getDeclarationTVA().getRef());
       // facture.setDeclarationTVA(dtva);
        Facture facture1 = factureDao.findByRef(facture.getRef());

        if ((facture1 != null) &&(facture1.getSocieteSource().getIce() == facture.getSocieteSource().getIce()) && (facture1.getSocieteDistination().getIce() == facture.getSocieteDistination().getIce()) ) {
            return -1;
        } else if (societeS == null) {
            return -2;
        } else if (societeD == null) {
            return -3;
        } else if (tv == null) {
            return -4;
        } else if (cpt == null) {
            return -5;
        }
        /*else {
           /* Long v =util.dateUtil(facture.getDateOperation());
            if(v> 0) {
                facture.setTrim(v);

            }*/
        else{

           /* facture.setTrim(dateUtil.compareDates(facture.getDateOperation()));
            facture.setAnnee(facture.getDateOperation().getYear());
            facture.setMois(facture.getDateOperation().getMonth());*/
            factureDao.save(facture);
            return 1;
        }



    }


}