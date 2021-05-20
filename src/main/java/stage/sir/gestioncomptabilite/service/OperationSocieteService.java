package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.*;
import stage.sir.gestioncomptabilite.dao.OperationSocieteDao;

import java.util.List;

@Service
public class OperationSocieteService {
    @Autowired
    private OperationSocieteDao operationSocieteDao;
    @Autowired
    private TypeOperationService typeOperationService;
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
    @Transactional
    public int deleteByRef(String ref) {
        return operationSocieteDao.deleteByRef(ref);
    }

    public List<OperationSociete> findAll() {
        return operationSocieteDao.findAll();
    }

    public int saveAlone(OperationSociete operationSociete) {
        TypeOperation typeOperation = typeOperationService.findByLibelle(operationSociete.getTypeOperation().getLibelle());
        operationSociete.setTypeOperation(typeOperation);
        Societe societe = societeService.findByIce(operationSociete.getSociete().getIce());
        operationSociete.setSociete(societe);
        if (findByRef(operationSociete.getRef()) != null) return -1;
        if (typeOperation == null) return -2;
        if (societe == null) return  -4;
        else {
            operationSociete.setFraixFix(typeOperation.getFraixfixtotal());
            operationSociete.setFraixComptable(typeOperation.getFraixcomptabletotal());
            operationSociete.setComptableTaiteur(null);
            operationSociete.setComptableValidateur(null);
            operationSociete.setOperationSocieteJustifs(null);
            operationSociete.setPaiements(null);
            operationSociete.setEtatOperationSociete(null);
            operationSocieteDao.save(operationSociete);
            return 1;
        }
    }

    public int save(OperationSociete operationSociete) {
        TypeOperation typeOperation = typeOperationService.findByLibelle(operationSociete.getTypeOperation().getLibelle());
        operationSociete.setTypeOperation(typeOperation);
        Societe societe = societeService.findByIce(operationSociete.getSociete().getIce());
        operationSociete.setSociete(societe);
        if (findByRef(operationSociete.getRef()) != null) return -1;
        if (typeOperation == null) return -2;

        if (societe == null) return  -4;
        else {
            operationSociete.setComptableTaiteur(null);
            operationSociete.setComptableValidateur(null);
            operationSociete.setEtatOperationSociete(null);

            operationSocieteDao.save(operationSociete);
            operationSocieteJustifService.save2(operationSociete, operationSociete.getOperationSocieteJustifs());
            paiementService.save2(operationSociete, operationSociete.getPaiements());
            return 1;
        }

    }
}
