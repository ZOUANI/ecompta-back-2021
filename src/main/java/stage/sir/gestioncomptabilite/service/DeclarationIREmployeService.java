package stage.sir.gestioncomptabilite.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Details;
import stage.sir.gestioncomptabilite.bean.Employe;
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
	
	Societe societe;
	
	
	
	List<DeclarationIREmploye> findByDeclarationIREmployes(DeclarationIR declarationIR){
		List<Employe> employes=employeService.findBySocieteEmpIce(declarationIR.getSociete().getIce());
		System.out.println(employes.toString());
		List<DeclarationIREmploye> declarationIREmployes= new ArrayList<>();
		
		
		
		
		for (Employe employe : employes) {
			DeclarationIREmploye declaration=new DeclarationIREmploye();
			List<Details> details=detailsService.findByDeclarationIR(employe.getSalaire());
			
				double ir=0;
			for (Details det : details) {
				
				ir+=det.getMontantTrancheRevenu();
				
			}
			
			
			
			declaration.setMontantIR(ir);
			
			declaration.setSalaireBrut(employe.getSalaire());
			declaration.setSalaireNet(employe.getSalaire()-ir);
			declaration.setEmploye(employe);
			declaration.setDeclarationIR(declarationIR);
			
			
			declarationIREmployes.add(declaration);
			
		}
		System.out.println(declarationIREmployes.toString());
		return declarationIREmployes;
	}
	
	
	
	public List<DeclarationIREmploye> findByDeclarationIRRef(String ref) {
		return declarationIREmployeDao.findByDeclarationIRRef(ref);
	}

	
	
	
	public Double calculTotal(List<DeclarationIREmploye> declarationIREmployes) {
		double total=0;
		for (DeclarationIREmploye declarationIREmploye : declarationIREmployes) {
			total+=declarationIREmploye.getMontantIR();
			
		}
		return total;
		
		
	}
	
	
	public DeclarationIREmploye findByEmployeCin(String cin) {
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
		Employe employe =employeService.findByCin(declarationIREmploye.getEmploye().getCin());
		declarationIREmploye.setSalaireBrut(employe.getSalaire());
		List<Details> detailsList =detailsService.findByDeclarationIR(declarationIREmploye.getSalaireBrut());
		declarationIREmploye.setDetailsEmploye(detailsList);
		
	}
	
	public int save(DeclarationIR declarationIR) {
		for (DeclarationIREmploye declarationIREmploye : declarationIR.getDeclarationsIREmployes()) {
			prepare(declarationIREmploye);
			DeclarationIR nvDeclarationIR=declarationIRService.findByRef(declarationIR.getRef());
			declarationIREmploye.setDeclarationIR(nvDeclarationIR);
			declarationIREmployeDao.save(declarationIREmploye);
			List<Details> details=detailsService.findByDeclarationIR(declarationIREmploye.getEmploye().getSalaire());
			
			declarationIREmploye.setDetailsEmploye(details);
			detailsService.save(declarationIREmploye);
			
		
		}
		
		return 1;
		
		
	}
	
	
	
	
	
	
	
	
	/*public int save(DeclarationIREmploye declarationIREmploye) {
		
			
			
			prepare(declarationIREmploye);
			double ir=0;
			for (Details det : declarationIREmploye.getDetailsEmploye()) {
				
				ir+=det.getMontantTrancheRevenu();
				
			}
			Employe employe =employeService.findByCin(declarationIREmploye.getEmploye().getCin());
			declarationIREmploye.setMontantIR(ir);
			declarationIREmploye.setSalaireNet(declarationIREmploye.getSalaireBrut()-ir);
			// Attention bro
			
			declarationIREmploye.setEmploye(employe);
			DeclarationIR declarationIR=declarationIRService.findByRef(declarationIREmploye.getDeclarationIR().getRef());
			declarationIREmploye.setDeclarationIR(declarationIR);
			System.out.println(declarationIREmploye);
			declarationIREmployeDao.save(declarationIREmploye);
			
			detailsService.save(declarationIREmploye);
			
		
			return 1;
		
		
		
		
		
	}*/




	
	
	
	
			
}
