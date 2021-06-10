package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stage.sir.gestioncomptabilite.bean.Employe;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.config.DateUtil;
import stage.sir.gestioncomptabilite.dao.EmployeDao;
import stage.sir.gestioncomptabilite.dao.SocieteDao;

import java.util.Date;
import java.util.List;

@Service
public class SocieteService {

    @Autowired
    SocieteDao societeDao;
    @Autowired
    EmployeDao employeDao;
    
    
    
    
    
    
    
   /* 
    public Societe findByLoginAndPassword(String login, String password) {
		return societeDao.findByLoginAndPassword(login, password);
	}
    
    
    public Societe loginSociete(Societe societe) throws Exception {
    	String log=societe.getLogin();
    	String password=societe.getPassword();
    	Societe userSociete=null;
    	if (log!=null && password!=null) {
    		
			userSociete=fetchLoginByLogAndPassword(log, password);
		}
    	if (userSociete==null) {
    		
			throw new Exception("login is too bad");
		}
    	
    	return userSociete;
    	
    	
    }
    
    
    public Societe fetchLoginByLogAndPassword(String login,String password) {
    	return societeDao.findByLoginAndPassword(login, password);
    }*/

	//public Societe findByIce(String ice) {




    public Societe findByIce(String ice) {

        return societeDao.findByIce(ice);
    }

    @Transactional
    public int deleteByIce(String ice) {
        return societeDao.deleteByIce(ice);
    }

    public List<Societe> findAll() {
        return societeDao.findAll();
    }
    public List<Employe> findListEmployes(String ice){
    	List<Employe> employes=employeDao.findBySocieteEmpIce(ice);
    	return employes;	
    }

    public int save(Societe societe) {
        if(findByIce(societe.getIce()) != null){
            return -1;
        }
        else{

            /*Calendar c = Calendar. getInstance();

            int anneeAct = c. get(Calendar. YEAR);
            int anneeCreation = societe.getDateCreation().getYear() + 1900;
            societe.setAge((double) anneeAct-anneeCreation);*/

            Long days = DateUtil.diffDays(new Date(), societe.getDateCreation());
            Long agee = days/365;
            Double ageSociete = (double) agee;
            societe.setAge(ageSociete);
            societe.setEtatSociete(null);
            societe.setComptable(null);
            societeDao.save(societe);
            return 1;
        }
    }
    public int save2(Societe societe) {
        if(findByIce(societe.getIce()) != null){
            return -1;
        }
        else{

            Long days = DateUtil.diffDays(new Date(), societe.getDateCreation());
            Long agee = days/365;
            Double ageSociete = (double) agee;
            societe.setAge(ageSociete);
          //  societe.setLogin(null);
            societe.setEtatSociete(null);
            societe.setComptable(null);
            societeDao.save(societe);
            return 1;
        }
    }
    public void update(Societe societe){
        societeDao.save(societe);
    }


	
}