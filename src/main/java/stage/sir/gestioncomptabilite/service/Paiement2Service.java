package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.DeclarationTva;
import stage.sir.gestioncomptabilite.bean.Paiement2;
import stage.sir.gestioncomptabilite.dao.Paiement2Dao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.Paiement2Vo;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class Paiement2Service {
    public Paiement2 findByRef(String ref) {
        return paiement2Dao.findByRef(ref);
    }
    @Transactional
    public int deleteByRef(String ref) {
        return paiement2Dao.deleteByRef(ref);
    }

    @Transactional
    public int deleteMultipleByRef(List<Paiement2> paiement2s) {
        int res = 0;
        for (int i = 0; i < paiement2s.size(); i++) {
            res += deleteByRef(paiement2s.get(i).getRef());
        }
        return res;
    }


    public List<Paiement2> findByDeclarationTvaRef(String ref) {
        return paiement2Dao.findByDeclarationTvaRef(ref);
    }

    public List<Paiement2> findByDeclarationISRef(String ref) {
        return paiement2Dao.findByDeclarationISRef(ref);
    }

    public List<Paiement2> findAll() {
        return paiement2Dao.findAll();
    }
    public int savepaiementtva(Paiement2 paiement2){
        DeclarationTva declarationTva = new DeclarationTva();
        if(paiement2.getTypeTva() == 1){
            declarationTva = declarationTvaService.findBySocieteIceAndEtatDeclarationRefAndAnneeAndTrim(paiement2.getDeclarationTva().getSociete().getIce(),"Valider",paiement2.getDeclarationTva().getAnnee(),paiement2.getDeclarationTva().getTrim());
            paiement2.setDeclarationTva(declarationTva);
        } else if (paiement2.getTypeTva() == 2){
            declarationTva = declarationTvaService.findBySocieteIceAndEtatDeclarationRefAndAnneeAndMois(paiement2.getDeclarationTva().getSociete().getIce(),"Valider",paiement2.getDeclarationTva().getAnnee(),paiement2.getDeclarationTva().getMois());
            paiement2.setDeclarationTva(declarationTva);
        }

        if (findByRef(paiement2.getRef())!= null){
            return -1;
        } else if(declarationTva == null){
            return -2;
        } else {
            Double reste = paiement2.getTotal() - (paiement2.getMontantCptValidateur() + paiement2.getMontantCptTraiteur());
            paiement2.setReste(reste);
            paiement2.setDeclarationIS(null);
            paiement2.setDeclarationIR(null);
            paiement2Dao.save(paiement2);
            return 1;
        }
    }


    public int savepaiementis(Paiement2 paiement2){
        DeclarationIS declarationIS = declarationISService.findBySocieteIceAndAnnee(paiement2.getDeclarationIS().getSociete().getIce(),paiement2.getDeclarationIS().getAnnee());
        paiement2.setDeclarationIS(declarationIS);
        if (findByRef(paiement2.getRef())!= null){
            return -1;
        } else if(declarationIS == null){
            return -2;
        } else {
            Double reste = paiement2.getTotal() - (paiement2.getMontantCptValidateur() + paiement2.getMontantCptTraiteur());
            paiement2.setReste(reste);
            paiement2.setDeclarationTva(null);
            paiement2.setDeclarationIR(null);
            paiement2.setTypeTva(0);
            paiement2Dao.save(paiement2);
            return 1;
        }
    }

    public int savepaiementir(Paiement2 paiement2){
        DeclarationIR declarationIR = declarationIRService.findByRef(paiement2.getDeclarationIR().getRef());
        paiement2.setDeclarationIR(declarationIR);
        if (findByRef(paiement2.getRef())!= null){
            return -1;
        } else if(declarationIR == null){
            return -2;
        } else {
            Double reste = paiement2.getTotal() - (paiement2.getMontantCptValidateur() + paiement2.getMontantCptTraiteur());
            paiement2.setReste(reste);
            paiement2.setDeclarationTva(null);
            paiement2.setDeclarationIS(null);
            paiement2Dao.save(paiement2);
            return 1;
        }
    }
    public List<Paiement2> paiementCriteria(Paiement2Vo paiement2Vo){
        String query = "SELECT p FROM Paiement2 p WHERE 1=1";
        if (StringUtil.isNotEmpty(paiement2Vo.getRef())){
            query += " AND p.ref = '" + paiement2Vo.getRef() + "'";
        }
        if (StringUtil.isNotEmpty(paiement2Vo.getSocieteis())){
            query += " AND p.declarationIS.societe.ice = '" + paiement2Vo.getSocieteis() + "'";
        }
        if (StringUtil.isNotEmpty(paiement2Vo.getAnneeis())){
            query += " AND p.declarationIS.annee = '" + paiement2Vo.getAnneeis() + "'";
        }
        if (StringUtil.isNotEmpty(paiement2Vo.getSocietetva())){
            query += " AND p.declarationTva.societe.ice = '" + paiement2Vo.getSocietetva() +"'";
        }
        if (StringUtil.isNotEmpty(paiement2Vo.getAnneetva())){
            query += " AND p.declarationTva.annee = '" + paiement2Vo.getAnneetva() + "'";
        }
        if (StringUtil.isNotEmpty(paiement2Vo.getMois())){
            query += " AND p.declarationTva.mois = '" + paiement2Vo.getMois() + "'";
        }
        if (StringUtil.isNotEmpty(paiement2Vo.getTrim())){
            query += " AND p.declarationTva.trim = '" + paiement2Vo.getTrim() + "'";
        }
        return entityManager.createQuery(query).getResultList();
    }

    public int update(Paiement2 paiement2){
        DeclarationTva declarationTva = new DeclarationTva();
        DeclarationIS declarationIS = new DeclarationIS();
        if (paiement2.getDeclarationIR() == null && paiement2.getDeclarationIS() == null){
            declarationTva = declarationTvaService.findByRef(paiement2.getDeclarationTva().getRef());
            paiement2.setDeclarationTva(declarationTva);
        } else if (paiement2.getDeclarationIR() == null && paiement2.getDeclarationTva() == null){
            declarationIS = declarationISService.findByRef(paiement2.getDeclarationIS().getRef());
            paiement2.setDeclarationIS(declarationIS);
        }
        if (declarationTva == null){
            return -1;
        } else if(declarationIS == null){
            return -2;
        } else{
            Double reste = paiement2.getTotal() - (paiement2.getMontantCptValidateur() + paiement2.getMontantCptTraiteur());
            paiement2.setReste(reste);
            paiement2Dao.save(paiement2);
            return 1;
        }
    }
    @Autowired
    DeclarationIRService declarationIRService;
    @Autowired
    DeclarationISService declarationISService;
    @Autowired
    DeclarationTvaService declarationTvaService;
    @Autowired
    Paiement2Dao paiement2Dao ;
    @Autowired
    private EntityManager entityManager;
}
