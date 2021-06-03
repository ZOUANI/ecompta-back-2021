package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stage.sir.gestioncomptabilite.bean.Demande;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DemandeDao;

import java.util.Date;
import java.util.List;

@Service
public class DemandeService {
	@Autowired
    DemandeDao demandeDao;
    @Autowired
    SocieteService societeService;

	


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
            demandeDao.save(demande);
            return 1;
        }
    }
    public int save(Demande demande) {
        Societe societe = societeService.findByIce(demande.getSociete().getIce());
        demande.setSociete(societe);


       
       
         if (demande.getSociete() == null){

        if (findByRef(demande.getRef()) != null){
            return -1;
        }
        else if (demande.getOperation() == null){
            return -2;
        }
        else if (societe == null){

            return -3;
        }
        else {
            Date dateDemande = new Date();
            demande.setDateDemande(dateDemande);
            demandeDao.save(demande);
           // return 1;
        }
    }
		return 1;

    } }
    
