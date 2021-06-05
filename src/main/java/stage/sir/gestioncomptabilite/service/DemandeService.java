package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Demande;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DemandeDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.DemandeVo;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;

@Service
public class DemandeService {

    public Demande findByRef(String ref) {
        return demandeDao.findByRef(ref);
    }

    @Transactional
    public int deleteByRef(String ref) {
        return demandeDao.deleteByRef(ref);
    }

    public List<Demande> findByOperation(String operation) {
        return demandeDao.findByOperation(operation);
    }

    public Demande findByUserUsername(String username) {
        return demandeDao.findByUserUsername(username);
    }

    public List<Demande> searchCriteria(DemandeVo demandeVo){
        String query = "SELECT d FROM Demande d WHERE 1=1";
        if(StringUtil.isNotEmpty(demandeVo.getRef())) {
            query+= " AND d.ref LIKE '%"+ demandeVo.getRef()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getOperation())) {
            query+= " AND d.operation LIKE '%"+ demandeVo.getOperation()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getComptableTraiteur())) {
            query+= " AND d.comptableTraiteur.nom LIKE '%"+ demandeVo.getComptableTraiteur()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getComptableValidateurt())) {
            query+= " AND d.comptableValidateur.nom LIKE '%"+ demandeVo.getComptableValidateurt() + "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getSociete())) {
            query+= " AND d.societe.ice LIKE '%"+ demandeVo.getSociete() + "%'";
        }

        return entityManager.createQuery(query).getResultList();
    }


    public List<Demande> findAll() {
        return demandeDao.findAll();
    }

    public int edit(Demande demande){
        Societe societe = societeService.findByIce(demande.getSociete().getIce());
        demande.setSociete(societe);
        if (societe == null){
            return -3;
        }
        else {
            Date dateDemande = new Date();
            demande.setDateDemande(dateDemande);
            demandeDao.save(demande);
            return 1;
        }
    }

    public int save(Demande demande) {
        Societe societe = societeService.findByIce(demande.getSociete().getIce());
        demande.setSociete(societe);

        if (findByRef(demande.getRef()) != null){ return -1; }
        else if (demande.getOperation() == null){ return -2; }
        else if (societe == null){ return -3; }
        else if (demande.getOperation() == "Declaration IS" && demande.getAnnee() == 0){
            return -4;

        }
        else if (demande.getOperation() == "Declaration IR" && demande.getMois() == null && demande.getAnnee() == 0){
            return -5;
        }
        else if (demande.getOperation() == "Declaration TVA" && demande.getMois() == null && demande.getTrimestre() == null && demande.getAnnee() == 0){
            return -6;
        }
        else {
        	
            Date dateDemande = new Date();
            demande.setDateDemande(dateDemande);
            demande.setUser(null);
            demandeDao.save(demande);


            return 1;
        }
    }

    @Autowired
    DemandeDao demandeDao;
    @Autowired
    SocieteService societeService;
    @Autowired
    EntityManager entityManager;
}