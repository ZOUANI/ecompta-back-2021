package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.DeclarationTva;
import stage.sir.gestioncomptabilite.bean.Paiement2;
import stage.sir.gestioncomptabilite.dao.Paiement2Dao;

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
        DeclarationTva declarationTva = declarationTvaService.findByRef(paiement2.getDeclarationTva().getRef());
        paiement2.setDeclarationTva(declarationTva);
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
        DeclarationIS declarationIS = declarationISService.findByRef(paiement2.getDeclarationIS().getRef());
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
    @Autowired
    DeclarationIRService declarationIRService;
    @Autowired
    DeclarationISService declarationISService;
    @Autowired
    DeclarationTvaService declarationTvaService;
    @Autowired
    Paiement2Dao paiement2Dao ;
}
