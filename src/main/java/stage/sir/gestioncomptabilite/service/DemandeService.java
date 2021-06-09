package stage.sir.gestioncomptabilite.service;


import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import stage.sir.gestioncomptabilite.Security.models.User;
import stage.sir.gestioncomptabilite.Security.repository.UserRepository;

import stage.sir.gestioncomptabilite.bean.Demande;

import stage.sir.gestioncomptabilite.bean.Employe;

import stage.sir.gestioncomptabilite.bean.EtatDemande;

import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DemandeDao;

import stage.sir.gestioncomptabilite.dao.EmployeDao;

import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.DemandeVo;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.List;


@Service
public class DemandeService {
	
	
	
	
	
	/*public List<Demande> findBycomptableTraiteurCodeAndAnneeAndMois(String code, Double annee, Integer mois) {
		return demandeDao.findBycomptableTraiteurCodeAndAnneeAndMois(code, annee, mois);
	}*/

	public List<Demande> findBycomptableValidateurCode(String code) {
		return demandeDao.findBycomptableValidateurCode(code);
	}

	public List<Demande> findBycomptableTraiteurCode(String code) {
		return demandeDao.findBycomptableTraiteurCode(code);
	}

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

    public Integer findTrimestre(Integer mois){
        if (mois>=1 && mois<=3){ return 1; }
        if (mois>=4 && mois<=6){ return 2; }
        if (mois>=7 && mois<=9){ return 3; }
        else{ return 4; }
    }

    public List<Demande> searchCriteria(DemandeVo demandeVo){
    	
        String query = "SELECT d FROM Demande d WHERE 1=1";
        if(StringUtil.isNotEmpty(demandeVo.getRef())) {
            query+= " AND d.ref LIKE '%"+ demandeVo.getRef()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getOperation())) {
            query+= " AND d.operation LIKE '%"+ demandeVo.getOperation()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getComptableTraiteurCode())) {
        	
            query+= " AND d.comptableTraiteur.code LIKE '%"+ demandeVo.getComptableTraiteurCode()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getComptableValidateur())) {
            query+= " AND d.comptableValidateur.code LIKE '%"+ demandeVo.getComptableValidateurCode()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getSociete())) {
            query+= " AND d.societe.ice LIKE '%"+ demandeVo.getSociete() + "%'";
        }

        if(StringUtil.isNotEmpty(demandeVo.getAnnee())) {
            query+= " AND d.annee = '"+ demandeVo.getAnnee()+ "'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getMoisMin())) {
            query+= " AND d.mois >= '"+ demandeVo.getMoisMin()+ "'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getMoisMax())) {
            query+= " AND d.mois <= '"+ demandeVo.getMoisMax()+ "'";
        }


        if(StringUtil.isNotEmpty(demandeVo.getEtatDemande())) {
            query+= " AND d.etatDemande.libelle LIKE '%"+ demandeVo.getEtatDemande() + "%'";
        }


        return entityManager.createQuery(query).getResultList();
    }
    
    
    
    
    
    
    
    
    public List<Demande> searchDemandeCriteria(DemandeVo demandeVo){
        String query = "SELECT d FROM Demande d WHERE 1=1";
      
        if(StringUtil.isNotEmpty(demandeVo.getOperation())) {
            query+= " AND d.operation LIKE '%"+ demandeVo.getOperation()+ "%'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getAnnee())) {
            query+= " AND d.annee = '"+ demandeVo.getAnnee()+ "'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getMoisMin())) {
            query+= " AND d.mois >= '"+ demandeVo.getMoisMin()+ "'";
        }
        if(StringUtil.isNotEmpty(demandeVo.getMoisMax())) {
            query+= " AND d.mois <= '"+ demandeVo.getMoisMax()+ "'";
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
            return -1;
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
        EtatDemande etatDemande = etatDemandeService.findByLibelle("en attente de réponse");
        if (findByRef(demande.getRef()) != null){ return -1; }
        else if (demande.getOperation() == null){ return -2; }
        else if (societe == null){ return -3; }
        else {

        	//User user=userDao.findByUsername("comptable");
        	//demande.setUser(user);
        //	System.out.println("******hanii*****");

            if (demande.getMois() != null){
                demande.setTrimestre(findTrimestre(demande.getMois()));
            }

            Date dateDemande = new Date();
            demande.setDateDemande(dateDemande);
            demande.setComptableTraiteur(null);
            demande.setComptableValidateur(null);
            demande.setUser(null);
            demande.setComptableTraiteur(null);
            demande.setComptableValidateur(null);
            demande.setEtatDemande(etatDemande);
            demandeDao.save(demande);
            return 1;
        }
    }

    @Autowired
    EmployeDao employeDao;
    @Autowired
    UserRepository userDao;
    
    @Autowired
    DemandeDao demandeDao;
    

	@Autowired
    SocieteService societeService;
    @Autowired
    EtatDemandeService etatDemandeService;
    @Autowired
    EntityManager entityManager;
	
	
	
}