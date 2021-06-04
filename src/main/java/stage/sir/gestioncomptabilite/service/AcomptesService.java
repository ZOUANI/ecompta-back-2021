package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Acomptes;
import stage.sir.gestioncomptabilite.dao.AcomptesDao;

import java.util.List;

@Service
public class AcomptesService {

    public List<Acomptes> findByNumero(Integer numero) { return acomptesDao.findByNumero(numero); }

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
        acomptesDao.save(acomptes);
        return 1;
    }

    @Autowired
    AcomptesDao acomptesDao;
}
