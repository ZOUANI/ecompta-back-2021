package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stage.sir.gestioncomptabilite.bean.Demande;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DemandeDao;

import java.util.List;

@Service
public class DemandeService {

    public Demande findByRef(String ref) {
        return demandeDao.findByRef(ref);
    }

    public int deleteByRef(String ref) {
        return demandeDao.deleteByRef(ref);
    }

    public List<Demande> findByOperation(String operation) {
        return demandeDao.findByOperation(operation);
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
            return -3;
        }
        else {
            demandeDao.save(demande);
            return 1;
        }
    }

    @Autowired
    DemandeDao demandeDao;
    @Autowired
    SocieteService societeService;
}
