package stage.sir.gestioncomptabilite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stage.sir.gestioncomptabilite.bean.Employe;

import stage.sir.gestioncomptabilite.dao.EmployeDao;

@Service
public class EmployeService {
	@Autowired
	EmployeDao employeDao;

	

	@Autowired
	SocieteService societeService;
	
	
	  
	public List<Employe> findBySocieteEmpIce(String ice) {
		return employeDao.findBySocieteEmpIce(ice);
	}
	
	
	




	public Employe findByCin(String cin) {
		return employeDao.findByCin(cin);
	}
	@Transactional
	public int deleteByCin(String cin) {
		return employeDao.deleteByCin(cin);
	}

	public int save(Employe employe) {
		if (employeDao.findByCin(employe.getCin())!=null) {
			
			return -1;
		}else {
			employeDao.save(employe);
			return 1;
		}
	}

	public List<Employe> findAll() {
		return employeDao.findAll();
	}
	

}
