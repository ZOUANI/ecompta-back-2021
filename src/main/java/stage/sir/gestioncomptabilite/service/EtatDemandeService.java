package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.EtatDemande;
import stage.sir.gestioncomptabilite.dao.EtatDemandeDao;

import java.util.List;

@Service
public class EtatDemandeService {

    public EtatDemande findByRef(String ref) {
        return etatDemandeDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return etatDemandeDao.deleteByRef(ref);
    }

    public EtatDemande findByLibelle(String libelle) {
        return etatDemandeDao.findByLibelle(libelle);
    }

    public List<EtatDemande> findAll() {
        return etatDemandeDao.findAll();
    }

    public int save(EtatDemande etatDemande) {
        if (findByRef(etatDemande.getRef()) != null){ return -1; }
         etatDemandeDao.save(etatDemande);
         return 1;
    }

    @Autowired
    EtatDemandeDao etatDemandeDao;
}
