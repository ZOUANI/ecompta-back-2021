package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationTva;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.bean.TypeDeclarationTva;
import stage.sir.gestioncomptabilite.dao.DeclarationTvaDao;
import stage.sir.gestioncomptabilite.vo.DeclarationTvaVo1;
import stage.sir.gestioncomptabilite.vo.DeclarationTvaVo2;

import java.util.*;

@Service
public class DeclarationTvaService {
    public DeclarationTva findByRef(String ref) {
        return declarationTvaDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return declarationTvaDao.deleteByRef(ref);
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
        declarationTva.setRef(System.currentTimeMillis()+"");
        if (findByRef(declarationTva.getRef())!=null){
            return -1;
        }
        Societe s = societeService.findByIce(declarationTva.getSociete().getIce());
        declarationTva.setSociete(s);
        TypeDeclarationTva t = typeDeclarationTvaService.findByRef(declarationTva.getTypeDeclarationTva().getRef());
        declarationTva.setTypeDeclarationTva(t);
        if (s==null){
            return  -2;
        }
        else if (t==null){
            return -3;
        }else {
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
                if (facture.getTypeOperation().equals("type-1")){
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
            declarationTvaDao.save(myDeclarationTva);
            return 1;
        }
    }

    public DeclarationTvaVo2 findfacturesandcalcultva(DeclarationTvaVo1 declarationTvaVo1){
        DeclarationTvaVo2 declarationTvaVo2 = new DeclarationTvaVo2();
        List<Facture> facturesvente = new ArrayList<Facture>();
        List<Facture> facturesachat = new ArrayList<Facture>();
        double tvacollecter = 0,tvadeductible = 0,differencetva = 0;
        if (declarationTvaVo1.getTypedeclarationtva().equals("trimestrielle")){
            facturesvente = factureService.findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getTrim(),"type-1");
            facturesachat = factureService.findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getTrim(),"type-2");
        }else {
            facturesvente = factureService.findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getMois(),"type-1");
            facturesvente = factureService.findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(declarationTvaVo1.getSocieteref(),declarationTvaVo1.getAnnee(),declarationTvaVo1.getMois(),"type-2");
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

    public List<DeclarationTva> findByAnneeAndMois(double annee, double mois) {
        return declarationTvaDao.findByAnneeAndMois(annee, mois);
    }

    public List<DeclarationTva> findByAnneeAndTrim(double annee, double trim) {
        return declarationTvaDao.findByAnneeAndTrim(annee, trim);
    }

    @Autowired
    DeclarationTvaDao declarationTvaDao;
    @Autowired
    SocieteService societeService;
    @Autowired
    TypeDeclarationTvaService typeDeclarationTvaService;
    @Autowired
    FactureService factureService;

}
