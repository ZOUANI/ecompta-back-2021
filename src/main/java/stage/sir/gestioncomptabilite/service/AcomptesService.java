package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Acomptes;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.AcomptesDao;
import stage.sir.gestioncomptabilite.util.StringUtil;
import stage.sir.gestioncomptabilite.vo.AcomptesVo;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AcomptesService {

    public List<Acomptes> findByNumero(Integer numero) { return acomptesDao.findByNumero(numero); }

    public Acomptes findBySocieteIceAndAnneeAndNumero(String ice, double annee, Integer numero) {
        return acomptesDao.findBySocieteIceAndAnneeAndNumero(ice, annee, numero);
    }

    public List<Acomptes> findBySocieteIceAndAnnee(String ice, double annee) {
        return acomptesDao.findBySocieteIceAndAnnee(ice, annee);
    }

    @Transactional
    public int deleteBySocieteIceAndAnneeAndNumero(String ice, double annee, Integer numero) {
        return acomptesDao.deleteBySocieteIceAndAnneeAndNumero(ice, annee, numero);
    }

    @Transactional
    public int deleteBySocieteIceAndAnnee(String ice, double annee) {
        return acomptesDao.deleteBySocieteIceAndAnnee(ice, annee);
    }

    @Transactional
    public int deleteMultipleBySocieteIceAndAnnee(List<Acomptes> acomptesList) {
        int res = 0;
        for (int i = 0; i < acomptesList.size(); i++) {
            res += deleteBySocieteIceAndAnneeAndNumero(acomptesList.get(i).getSociete().getIce(), acomptesList.get(i).getAnnee(), acomptesList.get(i).getNumero());
        }
        return res;
    }

    public List<Acomptes> SearchCriteria(AcomptesVo acomptesVo){
        String query = "SELECT a FROM Acomptes a WHERE 1=1";
        if (StringUtil.isNotEmpty(acomptesVo.getAnnee())){
            query += " AND a.annee LIKE '%" + acomptesVo.getAnnee() + "%'";
        }
        if(StringUtil.isNotEmpty(acomptesVo.getSociete())) {
            query+= " AND a.societe.ice LIKE '%"+ acomptesVo.getSociete()+ "%'";
        }
        if(StringUtil.isNotEmpty(acomptesVo.getNumero())) {
            query+= " AND a.numero LIKE '%"+ acomptesVo.getNumero()+ "%'";
        }
        return entityManager.createQuery(query).getResultList();
    }

    public List<Acomptes> findAll() { return acomptesDao.findAll(); }

    public int update(Acomptes acomptes){
        Societe societe = societeService.findByIce(acomptes.getSociete().getIce());
        acomptes.setSociete(societe);
        if (societe == null){
            return -2;
        }else {
            acomptesDao.save(acomptes);
            return 1;
        }
    }

    public int save(Acomptes acomptes) {
        Societe societe = societeService.findByIce(acomptes.getSociete().getIce());
        acomptes.setSociete(societe);
        if(findBySocieteIceAndAnneeAndNumero(acomptes.getSociete().getIce(), acomptes.getAnnee(), acomptes.getNumero()) != null){
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
    @Autowired
    EntityManager entityManager;
}
