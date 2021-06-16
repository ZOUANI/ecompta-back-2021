package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.bean.PaiementIS;
import stage.sir.gestioncomptabilite.dao.PaiementISDao;

import java.util.List;

@Service
public class PaiementISService {

    public PaiementIS findByRef(String ref) {
        return paiementISDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return paiementISDao.deleteByRef(ref);
    }

    public List<PaiementIS> findAll() {
        return paiementISDao.findAll();
    }

    public int save(PaiementIS paiementIS) {
        DeclarationIS declarationIS = declarationISService.findBySocieteIceAndAnnee(paiementIS.getDeclarationIS().getSociete().getIce(), paiementIS.getDeclarationIS().getAnnee());
        paiementIS.setDeclarationIS(declarationIS);
        if (findByRef(paiementIS.getRef()) != null){
            return -1;
        }
        else if (declarationIS == null){
            return -2;
        }
        else {
            Double reste = paiementIS.getTotal() - ( paiementIS.getMontantCptTraiteur() + paiementIS.getMontantCptValidateur());
            paiementIS.setReste(reste);
            paiementISDao.save(paiementIS);
            return 1;
        }
    }

    @Autowired
    PaiementISDao paiementISDao;
    @Autowired
    DeclarationISService declarationISService;
}
