package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.*;
import stage.sir.gestioncomptabilite.dao.FactureDao;
import stage.sir.gestioncomptabilite.vo.FactureVo;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class FactureService extends AbstractFacade<Facture>{
    @Autowired
    private FactureDao factureDao;
    @Autowired
    private SocieteService societeService;
    @Autowired
    private TvaService tvaService;
    @Autowired
    private EtatFactureService etatFactureService;
    @Autowired
    private EtatPaiementService etatPaiementService;
    @Autowired
    private ClassComptableService comptComptableService;
    @Autowired
    private DeclarationISService declarationISService;
    @Autowired
    private DeclarationIRService declarationIRService;
    @Autowired
    private  DeclarationTvaService declarationTvaService;
    @Autowired
    private EntityManager entityManager;


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

    public List<Facture> findBySocieteSourceIceAndTypeOperation(String ice, String typeoperation) {
        return factureDao.findBySocieteSourceIceAndTypeOperation(ice, typeoperation);
    }

    public List<Facture> findAll() {
        return factureDao.findAll();
    }

    public int save(Facture facture) {
        String etatCredit,etatDebit;
        Societe societeS = societeService.findByIce(facture.getSocieteSource().getIce());
        facture.setSocieteSource(societeS);
        Societe societeD = societeService.findByIce(facture.getSocieteDistination().getIce());
        facture.setSocieteDistination(societeD);
        Tva tv = tvaService.findByRef(facture.getTva().getRef());
        facture.setTva(tv);
        EtatFacture etatFacture = etatFactureService.findByCode(facture.getEtatFacture().getCode());
        facture.setEtatFacture(etatFacture);
        EtatPaiement etatPaiement = etatPaiementService.findByCode(facture.getEtatPaiement().getCode());
        facture.setEtatPaiement(etatPaiement);
        ClasseComptable cpt = comptComptableService.findByNumero(facture.getClassComptable().getNumero());
        facture.setClassComptable(cpt);
        Facture facture1 = factureDao.findByRef(facture.getRef());

        if ((facture1 != null) &&(facture1.getSocieteSource().getIce() == facture.getSocieteSource().getIce())  ) {
            return -1;
        } else if (societeS == null) {
            return -2;
        } else if (societeD == null || societeD == societeS) {
            return -3;
        } else if (tv == null) {
            return -4;
        }  else if (etatFacture == null) {
            return -5;
        }  else if (etatPaiement == null) {
            return -6;
        } else if (cpt == null) {
            return -7;
        }

        else{


            facture.setDeclarationIR(null);
            facture.setDeclarationIS(null);
            facture.setDeclarationTva(null);
            facture.setMontantTVA((facture.getMontantHorsTaxe() * facture.getTva().getValeur()) /100);
            facture.setMontantTTC(facture.getMontantHorsTaxe() + facture.getMontantTVA());
            facture.setTrim(Trouvertrim(facture.getDateOperation()));
            facture.setMois(facture.getDateOperation().getMonth() +1);
            facture.setAnnee(facture.getDateOperation().getYear() + 1900);

            if(facture.getTypeOperation().equals("CRÉDIT")){
                etatCredit = String.valueOf(facture.getMontantTTC());
                etatDebit = "-";
                facture.setCredit(etatCredit);
                facture.setDebit(etatDebit);
            }
            if(facture.getTypeOperation().equals("DÉBIT")){
                etatDebit = String.valueOf(facture.getMontantTTC());
                etatCredit = "-";
                facture.setCredit(etatCredit);
                facture.setDebit(etatDebit);
            }
            factureDao.save(facture);
            return 1;
        }



    }
    public double Trouvertrim(Date date){
        if((date.getMonth() +1) <= 3){
            return 1;
        }
        else if((date.getMonth() +1) > 3 && (date.getMonth() +1) <= 6){
            return 2;
        }
        else if((date.getMonth() +1) > 6 && (date.getMonth() +1) <= 9){
            return 3;
        }

        else {
            return 4;
        }

    }

    public List<Facture>  Journal(FactureVo factureVo){

        String request = "SELECT f FROM Facture f WHERE 1=1 ";

        request+=addConstraintMinMaxDate("f","dateOperation",factureVo.getDmin(),factureVo.getDmax());
        return entityManager.createQuery(request).getResultList();

    }
    public FactureVo CalculSomme(FactureVo factureVo){
        List<Facture> listjournal = Journal(factureVo);
        double sommecredit = 0,sommeDebit = 0;
        for (Facture facture: listjournal) {
            if(facture.getTypeOperation().equals("CRÉDIT")){
                sommecredit += facture.getMontantTTC();

            }
            else {
                sommeDebit += facture.getMontantTTC();


            }
        }
        factureVo.setTotalcredit(sommecredit);
        factureVo.setTotaldebit(sommeDebit);
        return factureVo;
    }

    public int update(Facture facture){
        String etatCredit,etatDebit;
        Societe societeS = societeService.findByIce(facture.getSocieteSource().getIce());
        facture.setSocieteSource(societeS);
        Societe societeD = societeService.findByIce(facture.getSocieteDistination().getIce());
        facture.setSocieteDistination(societeD);
        Tva tv = tvaService.findByRef(facture.getTva().getRef());
        facture.setTva(tv);
        EtatFacture etatFacture = etatFactureService.findByCode(facture.getEtatFacture().getCode());
        facture.setEtatFacture(etatFacture);
        EtatPaiement etatPaiement = etatPaiementService.findByCode(facture.getEtatPaiement().getCode());
        facture.setEtatPaiement(etatPaiement);

        ClasseComptable cpt = comptComptableService.findByNumero(facture.getClassComptable().getNumero());
        facture.setClassComptable(cpt);
        Facture facture1 = factureDao.findByRef(facture.getRef());

        if (societeS == null) {
            return -1;
        } else if (societeD == null || societeD == societeS) {
            return -2;
        } else if (tv == null) {
            return -3;
        } else if (etatFacture == null) {
            return -5;
        }  else if (etatPaiement == null) {
            return -6;
        }  else if (cpt == null) {
            return -4;
        }

        else{

            facture.setMontantTVA((facture.getMontantHorsTaxe() * facture.getTva().getValeur()) /100);
            facture.setMontantTTC(facture.getMontantHorsTaxe() + facture.getMontantTVA());
            facture.setTrim(Trouvertrim(facture.getDateOperation()));
            facture.setMois(facture.getDateOperation().getMonth() +1);
            facture.setAnnee(facture.getDateOperation().getYear() + 1900);
            if(facture.getTypeOperation().equals("CRÉDIT")){
                etatCredit = String.valueOf(facture.getMontantTTC());
                etatDebit = "-";
                facture.setCredit(etatCredit);
                facture.setDebit(etatDebit);
            }
            if(facture.getTypeOperation().equals("DÉBIT")){
                etatDebit = String.valueOf(facture.getMontantTTC());
                etatCredit = "-";
                facture.setCredit(etatCredit);
                facture.setDebit(etatDebit);
            }
            factureDao.save(facture);
            return 1;
        }


    }
    public List<Facture> findBySocieteSourceIceAndAnnee(String ice, double annee) {
        return factureDao.findBySocieteSourceIceAndAnnee(ice, annee);
    }
    public List<Facture> findBySocieteSourceIceAndAnneeAndTypeOperation(String ice, double annee, String typeoperation) {
        return factureDao.findBySocieteSourceIceAndAnneeAndTypeOperation(ice, annee, typeoperation);
    }

}