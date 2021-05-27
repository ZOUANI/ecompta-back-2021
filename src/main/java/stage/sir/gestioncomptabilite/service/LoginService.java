package stage.sir.gestioncomptabilite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stage.sir.gestioncomptabilite.bean.Login;
import stage.sir.gestioncomptabilite.dao.LoginDao;

@Service
public class LoginService {
	@Autowired
	LoginDao loginDao;

	public Login findByUsername(String usename) {
		return loginDao.findByUsername(usename);
	}
	public Login findByType(String type) {
		return loginDao.findByType(type);
	}
	@Transactional
	public int deleteByType(String type) {
		return loginDao.deleteByType(type);
	}

	public Login findByUsernameAndPassword(String username, String password) {
		return loginDao.findByUsernameAndPassword(username, password);
	}
	

    
    
    public Login loginUser(Login login) throws Exception {
    	
    	Login userSociete=null;
    	if (login.getUsername()!=null && login.getPassword()!=null) {
    		
			userSociete=fetchLoginByUsernameAndPassword(login.getUsername(), login.getPassword());
		}
    	if (userSociete==null) {
    		
			throw new Exception("login is too bad");
		}
    	
    	return userSociete;
    	
    	
    }
    
    
    public Login fetchLoginByUsernameAndPassword(String login,String password) {
    	return loginDao.findByUsernameAndPassword(login, password);
    }
	
	
	
	
	
	
	
	
	
	

	public int save(Login login) {
		if (findByUsername(login.getUsername())!=null) {
			return -1;
		}else {
			loginDao.save(login);
			return 1;
		}
		
	}


	public List<Login> findAll() {
		return loginDao.findAll();
	}
	

}
