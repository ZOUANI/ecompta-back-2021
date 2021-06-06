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
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DemandeDao;
import stage.sir.gestioncomptabilite.dao.EmployeDao;

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
        else if (demande.getOperation() == "Declaration TVA" && demande.getMois() == null && demande.getTrim() == null && demande.getAnnee() == 0){
            return -6;
        }
        else {
        	//User user=userDao.findByUsername("comptable");
        	//demande.setUser(user);
        //	System.out.println("******hanii*****");
            Date dateDemande = new Date();
            demande.setDateDemande(dateDemande);
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
}