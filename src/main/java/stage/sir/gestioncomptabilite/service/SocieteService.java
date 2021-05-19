package stage.sir.gestioncomptabilite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.SocieteDao;

import java.util.Calendar;

import java.util.List;

@Service
public class SocieteService {

    @Autowired
    SocieteDao societeDao;
    
    
    
    
    
    
    
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

    public int save(Societe societe) {
        if(findByIce(societe.getIce()) != null){
            return -1;
        }
        else{
            Calendar c = Calendar. getInstance();
            int anneeAct = c. get(Calendar. YEAR);
            int annee = societe.getAnneeExploitation();
            societe.setAge((double) anneeAct-annee);
            societeDao.save(societe);
            return 1;
        }
    }

	
}