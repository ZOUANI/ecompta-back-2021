package stage.sir.gestioncomptabilite.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Details;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.bean.TauxIr;
import stage.sir.gestioncomptabilite.dao.DeclarationIREmployeDao;
import stage.sir.gestioncomptabilite.dao.EmployeDao;
import stage.sir.gestioncomptabilite.dao.SocieteDao;

@Service
public class DeclarationIREmployeService {
	@Autowired
	DeclarationIREmployeDao declarationIREmployeDao;
	@Autowired
	DetailsService detailsService;

	
	@Autowired
	EmployeService employeService;
	@Autowired
	SocieteService societeService;
	@Autowired
	DeclarationIRService declarationIRService;
	
	
	
	
	
	public List<DeclarationIREmploye> findByEmployeCin(String cin) {
		return declarationIREmployeDao.findByEmployeCin(cin);
	}



	@Transactional
	public int deleteByEmployeCin(String cin) {
		return declarationIREmployeDao.deleteByEmployeCin(cin);
	}


	
	public DeclarationIREmploye findByRefEmp(String refEmp) {
		return declarationIREmployeDao.findByRefEmp(refEmp);
	}
	
	public void prepare(DeclarationIREmploye declarationIREmploye) {
		List<Details> detailsList =detailsService.findByDeclarationIR(declarationIREmploye.getSalaireBrut());
		declarationIREmploye.setDetailsEmploye(detailsList);
	}
	
	public int save(DeclarationIREmploye declarationIREmploye) {
		
			
			
			prepare(declarationIREmploye);
			double ir=0;
			for (Details det : declarationIREmploye.getDetailsEmploye()) {
				
				ir+=det.getMontantTrancheRevenu();
				
			}
			
			declarationIREmploye.setMontantIR(ir);
			declarationIREmploye.setSalaireNet(declarationIREmploye.getSalaireBrut()-ir);
			// Attention bro
			declarationIREmployeDao.save(declarationIREmploye);
			
			detailsService.save(declarationIREmploye);
			
		
			return 1;
		
		
		
		
		
	}
	
	
	
			
}
