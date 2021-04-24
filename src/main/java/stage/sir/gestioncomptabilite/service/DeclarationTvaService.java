package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationTva;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.bean.TypeDeclarationTva;
import stage.sir.gestioncomptabilite.dao.DeclarationTvaDao;

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
            List<Facture> f = new ArrayList<Facture>();
            double tvac = 0,tvap = 0,dtva = 0;
            if (declarationTva.getTypeDeclarationTva().getLibelle().equals("trimestrielle")){
                 f = factureService.findBySocieteSourceIceAndAnneeAndTrim(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getTrim());
            }else {
                f = factureService.findBySocieteSourceIceAndAnneeAndMois(declarationTva.getSociete().getIce(),declarationTva.getAnnee(),declarationTva.getMois());
            }
            DeclarationTva d = findByRef(declarationTva.getRef());
            for (Facture ff:f){
                if (ff.getTypeOperation().equals("type-1")){
                    tvac += ff.getMontantTVA();
                    ff.setDeclarationTva(d);
                    factureService.update(ff);
                }else {
                    tvap += ff.getMontantTVA();
                    ff.setDeclarationTva(d);
                    factureService.update(ff);
                }
            }
            d.setTvacollecter(tvac);
            d.setTvaperdue(tvap);
            dtva = tvac - tvap;
            d.setDifftva(dtva);
            declarationTvaDao.save(d);
            return 1;
        }
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
