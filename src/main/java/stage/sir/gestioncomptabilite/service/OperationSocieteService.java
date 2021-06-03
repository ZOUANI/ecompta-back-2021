package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.*;
import stage.sir.gestioncomptabilite.dao.OperationSocieteDao;

import javax.persistence.EntityManager;
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
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ComptableService comptableService;

    public OperationSociete findByRef(String ref) {
        return operationSocieteDao.findByRef(ref);
    }

    public List<OperationSociete> findBySocieteIce(String ice) {
        return operationSocieteDao.findBySocieteIce(ice);
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
    public int validateOperation(OperationSociete operationSociete){
        EtatOperationSociete etatOperationSocieteValidate = etatOperationSocieteService.findByRef("Validate");
        operationSociete.setEtatOperationSociete(etatOperationSocieteValidate);
        operationSocieteDao.save(operationSociete);
        return 1;
    }
    public int refuseOperation(OperationSociete operationSociete){
        EtatOperationSociete etatOperationSociete = etatOperationSocieteService.findByRef("Refuse");
        operationSociete.setEtatOperationSociete(etatOperationSociete);
        operationSocieteDao.save(operationSociete);
        return 1;
    }
    public List<OperationSociete> findBySocieteIceAndEtatOperationSocieteRef(String ice, String etat) {
        return operationSocieteDao.findBySocieteIceAndEtatOperationSocieteRef(ice, etat);
    }
    public List<OperationSociete> findOperationPourAffecterComptable(){
        String query = "SELECT o FROM OperationSociete o WHERE o.etatOperationSociete.ref = 'Validate' AND o.comptableTaiteur IS NULL AND o.comptableValidateur IS NULL";
        return entityManager.createQuery(query).getResultList();
    }
    public int affecterComptableTraiteur(OperationSociete operationSociete){
        Comptable comptableTraiteur = comptableService.findByCode(operationSociete.getComptableTaiteur().getCode());
        Societe societe = societeService.findByIce(operationSociete.getSociete().getIce());
        societe.setComptable(comptableTraiteur);
        operationSociete.setComptableTaiteur(comptableTraiteur);
        societeService.update(societe);
        operationSocieteDao.save(operationSociete);
        return 1;
    }
    public int affecterComptableValidateur(OperationSociete operationSociete){
        Comptable comptableValidateur = comptableService.findByCode(operationSociete.getComptableValidateur().getCode());
        operationSociete.setComptableValidateur(comptableValidateur);
        operationSocieteDao.save(operationSociete);
        return 1;
    }

}
