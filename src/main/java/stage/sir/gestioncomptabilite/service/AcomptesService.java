package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Acomptes;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.AcomptesDao;

import java.util.List;

@Service
public class AcomptesService {

    public List<Acomptes> findByNumero(Integer numero) { return acomptesDao.findByNumero(numero); }

    public Acomptes findBySocieteIceAndAnneePayeAndNumero(String ice, double annee, Integer numero) {
        return acomptesDao.findBySocieteIceAndAnneePayeAndNumero(ice, annee, numero);
    }

    @Transactional
    public int deleteByNumero(Integer numero) {
        int res = 0;
        List<Acomptes> acomptes = findByNumero(numero);
        for (Acomptes acompte: acomptes){
            acomptesDao.deleteByNumero(acompte.getNumero());
            res+=1;
        }
        return res;
    }

    @Transactional
    public int deleteBySocieteIceAndAnneePaye(String ice, double annee) {
        return acomptesDao.deleteBySocieteIceAndAnneePaye(ice, annee);
    }

    public List<Acomptes> findAll() { return acomptesDao.findAll(); }

    public int save(Acomptes acomptes) {
        Societe societe = societeService.findByIce(acomptes.getSociete().getIce());
        acomptes.setSociete(societe);
        if(findBySocieteIceAndAnneePayeAndNumero(acomptes.getSociete().getIce(), acomptes.getAnneePaye(), acomptes.getNumero()) != null){
            return -1;
        }else if (societe == null){
            return -2;
        }else {
            acomptesDao.save(acomptes);
            return 1;
        }

    }


    @Autowired
    AcomptesDao acomptesDao;
    @Autowired
    SocieteService societeService;
}
