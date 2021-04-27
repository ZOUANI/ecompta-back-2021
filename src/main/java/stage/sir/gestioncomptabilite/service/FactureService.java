package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.*;
import stage.sir.gestioncomptabilite.dao.FactureDao;
import stage.sir.gestioncomptabilite.vo.ObjectVo;
//import stage.sir.gestioncomptabilite.service.util.DateUtil;

import javax.persistence.EntityManager;
import java.util.Date;
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
    @Autowired
    private  DeclarationTvaService declarationTvaService;
    @Autowired
    private EntityManager entityManager;


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

    public List<Facture> findBySocieteSourceIceAndAnnee(String ice, double annee) {
        return factureDao.findBySocieteSourceIceAndAnnee(ice, annee);
    }

    public List<Facture> findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(String ice, double annee, double trim, String typeoperation) {
        return factureDao.findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(ice, annee, trim, typeoperation);
    }

    public List<Facture> findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(String ice, double annee, double mois, String typeoperation) {
        return factureDao.findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(ice, annee, mois, typeoperation);
    }

    public List<Facture> findBySocieteSourceIceAndAnneeAndTypeOperation(String ice, double annee, String typeoperation) {
        return factureDao.findBySocieteSourceIceAndAnneeAndTypeOperation(ice, annee, typeoperation);
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
        Facture facture1 = factureDao.findByRef(facture.getRef());

        if ((facture1 != null) &&(facture1.getSocieteSource().getIce() == facture.getSocieteSource().getIce())  ) {
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
            facture.setDeclarationIR(null);
            facture.setDeclarationIS(null);
            facture.setDeclarationTva(null);
            facture.setMontantTVA(facture.getTva().getValeur());
            facture.setTrim(Trouvertrim(facture.getDateOperation()));
            facture.setMois(facture.getDateOperation().getMonth());
            facture.setAnnee(facture.getDateOperation().getYear());
            factureDao.save(facture);
            return 1;
        }



    }
    public double Trouvertrim(Date date){
        if(date.getMonth() <= 3){
            return 1;
        }
        else if(date.getMonth() > 3 && date.getMonth() <= 6){
            return 2;
        }
        else if(date.getMonth() > 6 && date.getMonth() <= 9){
            return 3;
        }
        else {
            return 4;
        }

    }
    public List<Facture>  findByMultiTache(ObjectVo objectVo){
        long ma, min;
        ma = objectVo.getDmax().getTime();
        min = objectVo.getDmin().getTime();
        String request = "SELECT f FROM Facture f WHERE 1=1 ";
        if (objectVo.getDmin().getTime() - objectVo.getDmax().getTime() < 0){
            request += " AND f.dateOperation <=" + ma + "";
            request += " AND f.dateOperation >=" + min + "";

        }
        return entityManager.createQuery(request).getResultList();

    }

    public int saveFacturesIS(DeclarationIS declarationIS, List<Facture> listFactures){
        double gain = 0;
        double charge = 0;
        for (Facture f: listFactures){
            f.setDeclarationIS(declarationIS);
            Societe societeS = societeService.findByIce(f.getSocieteSource().getIce());
            f.setSocieteSource(societeS);
            Societe societeD = societeService.findByIce(f.getSocieteDistination().getIce());
            f.setSocieteDistination(societeD);
            Tva tv = tvaService.findByRef(f.getTva().getRef());
            f.setTva(tv);
            ClassComptable cpt = comptComptableService.findByRef(f.getClassComptable().getRef());
            f.setClassComptable(cpt);
            Facture facture1 = factureDao.findByRef(f.getRef());

            if ((facture1 != null) &&(facture1.getSocieteSource().getIce() == f.getSocieteSource().getIce()) && (facture1.getSocieteDistination().getIce() == f.getSocieteDistination().getIce()) ) {
                return -1;
            } else if (societeS == null) {
                return -2;
            } else if (societeD == null) {
                return -3;
            } else if (tv == null) {
                return -4;
            } else if (cpt == null) {
                return -5;
            }else {
                factureDao.save(f);
                return 1;
            }
        }
        return 0;
    }
}