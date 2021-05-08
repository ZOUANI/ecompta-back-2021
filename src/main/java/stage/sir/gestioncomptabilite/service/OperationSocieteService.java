package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.CategorieService;
import stage.sir.gestioncomptabilite.bean.EtatOperationSociete;
import stage.sir.gestioncomptabilite.bean.OperationSociete;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.OperationSocieteDao;

import java.util.List;

@Service
public class OperationSocieteService {
    @Autowired
    private OperationSocieteDao operationSocieteDao;
    @Autowired
    private CategorieServiceService categorieServiceService;
    @Autowired
    private EtatOperationSocieteService etatOperationSocieteService;
    @Autowired
    private OperationSocieteJustifService operationSocieteJustifService;
    @Autowired
    private PaiementService paiementService;
    @Autowired
    private SocieteService societeService;

    public OperationSociete findByRef(String ref) {
        return operationSocieteDao.findByRef(ref);
    }

    public int deleteByRef(String ref) {
        return operationSocieteDao.deleteByRef(ref);
    }

    public List<OperationSociete> findAll() {
        return operationSocieteDao.findAll();
    }

    public int saveAlone(OperationSociete operationSociete) {
        CategorieService categorieService = categorieServiceService.findByTitre(operationSociete.getCategorieService().getTitre());
        operationSociete.setCategorieService(categorieService);
        EtatOperationSociete etatOperationSociete = etatOperationSocieteService.findByRef(operationSociete.getRef());
        operationSociete.setEtatOperationSociete(etatOperationSociete);
        Societe societe = societeService.findByIce(operationSociete.getSociete().getIce());
        operationSociete.setSociete(societe);
        if (findByRef(operationSociete.getRef()) != null) return -1;
        if (categorieService == null) return -2;
        if (operationSociete == null) return -3;
        if (societe == null) return  -4;
        else {
            operationSociete.setComptableTaiteur(null);
            operationSociete.setComptableValidateur(null);
            operationSociete.setOperationSocieteJustifs(null);
            operationSociete.setPaiements(null);
            operationSocieteDao.save(operationSociete);
            return 1;
        }
    }

    public int save(OperationSociete operationSociete) {
        CategorieService categorieService = categorieServiceService.findByTitre(operationSociete.getCategorieService().getTitre());
        operationSociete.setCategorieService(categorieService);
        EtatOperationSociete etatOperationSociete = etatOperationSocieteService.findByRef(operationSociete.getRef());
        operationSociete.setEtatOperationSociete(etatOperationSociete);
        Societe societe = societeService.findByIce(operationSociete.getSociete().getIce());
        operationSociete.setSociete(societe);
        if (findByRef(operationSociete.getRef()) != null) return -1;
        if (categorieService == null) return -2;
        if (operationSociete == null) return -3;
        if (societe == null) return  -4;
        else {
            operationSociete.setComptableTaiteur(null);
            operationSociete.setComptableValidateur(null);
            operationSocieteDao.save(operationSociete);
            operationSocieteJustifService.save2(operationSociete, operationSociete.getOperationSocieteJustifs());
            paiementService.save2(operationSociete, operationSociete.getPaiements());
            return 1;
        }

    }
}
