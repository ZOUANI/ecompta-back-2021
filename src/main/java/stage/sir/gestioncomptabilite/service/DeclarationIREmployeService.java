package stage.sir.gestioncomptabilite.service;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import stage.sir.gestioncomptabilite.dao.TauxIrDao;

@Service
public class DeclarationIREmployeService {
	@Autowired
	DeclarationIREmployeDao declarationIREmployeDao;
	
	

	
/*	@Autowired
	DetailsService detailsService;*/
	@Autowired
	TauxIrDao tauxIrDao;
	
	@Autowired
	EmployeService employeService;
	@Autowired
	SocieteService societeService;
	@Autowired
	DeclarationIRService declarationIRService;
	
	Societe societe;
	
	@Autowired
	TauxIrService tauxIrService;
	
	
	
	public DeclarationIREmploye findBySalaireNet(Double salaireNet) {
		return declarationIREmployeDao.findBySalaireNet(salaireNet);
	}
	
	List<DeclarationIREmploye> findByDeclarationIREmployes(DeclarationIR declarationIR){
		//List<Employe> employes=employeService.findBySocieteEmpIce(declarationIR.getSociete().getIce());
		//System.out.println("*****************");
		//System.out.println(declarationIR.getSociete());
		
		List<Employe> employes=declarationIR.getSociete().getEmployes();
		List<DeclarationIREmploye> declarationIREmployes= new ArrayList<>();
		
		
		
		
		for (Employe employe : employes) {
			DeclarationIREmploye declaration=new DeclarationIREmploye();
			//List<Details> details=detailsService.findByDeclarationIR(employe.getSalaire());
			List<TauxIr> decla =tauxIrService.findByDeclarationIR(employe.getSalaire());
			ArrayList<Double> tauxPorc=new ArrayList<Double>();
			for (TauxIr taux : decla) {
				tauxPorc.add(taux.getPourcentage());
			}
			
			
			Double maximumTaux=Collections.max(tauxPorc);
			TauxIr maxTaux=tauxIrDao.findByPourcentage(maximumTaux);
			
			
			List<Double> irDetails=new ArrayList<>();	
				double val=0;
				double diff=0;
			for(TauxIr tauxIr: decla) {
				//double pourc=(tauxIr.getPourcentage())/100;
				
				if (tauxIr.getSalaireMax()>employe.getSalaire() && tauxIr.getSalaireMin()<employe.getSalaire()) {
					diff=(employe.getSalaire()-tauxIr.getSalaireMin());
					
				}
				else if (tauxIr.getSalaireMax()<employe.getSalaire()) {
					diff=(tauxIr.getSalaireMax()-tauxIr.getSalaireMin());
					
				}
			
				val+=diff;
				double trans=(diff*tauxIr.getPourcentage())/100;
				irDetails.add(trans);
			
			}
			
			double ir=0;
			for(int d=0;d<irDetails.size();d++) {
				
				ir+=irDetails.get(d);
				
			}
			
			
			
		
			
			declaration.setMontantIR(ir);
			
			declaration.setSalaireBrut(employe.getSalaire());
			declaration.setSalaireNet(employe.getSalaire()-ir);
			declaration.setTauxIr(maxTaux);
			declaration.setEmploye(employe);
			declaration.setDeclarationIR(declarationIR);
			
			
			declarationIREmployes.add(declaration);
			
		}
		
		return declarationIREmployes;
	}
	
	
	
	public List<DeclarationIREmploye> findByDeclarationIRRef(String ref) {
		return declarationIREmployeDao.findByDeclarationIRRef(ref);
	}

	
	
	@Transactional
	public int deleteByDeclarationIRRef(String ref) {
		return declarationIREmployeDao.deleteByDeclarationIRRef(ref);
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


	
	
	
	public void prepare(DeclarationIREmploye declarationIREmploye) {
		Employe employe =employeService.findByCin(declarationIREmploye.getEmploye().getCin());
		declarationIREmploye.setSalaireBrut(employe.getSalaire());
		//List<Details> detailsList =detailsService.findByDeclarationIR(declarationIREmploye.getSalaireBrut());
		//declarationIREmploye.setDetailsEmploye(detailsList);
		
	}
	@Autowired
	EmployeDao employeDao;
	/*
	public DeclarationIREmploye updateMontantEmploye(Employe employe) {
		
		Employe employe2=employeService.findByCin(employe.getCin());
		employe.setId(employe2.getId());
		
		employeDao.save(employe);
		List<TauxIr> tauxIrs=tauxIrService.findByDeclarationIR(employe.getSalaire());
		List<Details> details=detailsService.findByDeclarationIR(employe.getSalaire());
		DeclarationIREmploye declarationIREmploye=new DeclarationIREmploye();
		double ir=0;
		for (Details details1 : details) {
			ir+=details1.getMontantTrancheRevenu();
		}
		declarationIREmploye.setSalaireBrut(employe.getSalaire());
		declarationIREmploye.setSalaireNet(employe.getSalaire()-ir);
		//declarationIREmploye.setDetailsEmploye(details);
		declarationIREmploye.setMontantIR(ir);
		declarationIREmploye.setEmploye(employe);
		
		
		return declarationIREmploye;
	}*/
	
	
	
	public int save(DeclarationIR declarationIR) {
		for (DeclarationIREmploye declarationIREmploye : declarationIR.getDeclarationsIREmployes()) {
			//prepare(declarationIREmploye);
			DeclarationIR nvDeclarationIR=declarationIRService.findByRef(declarationIR.getRef());
			Employe employe= employeDao.findByCin(declarationIREmploye.getEmploye().getCin());
			declarationIREmploye.setEmploye(employe);
			declarationIREmploye.setDeclarationIR(nvDeclarationIR);
			declarationIREmployeDao.save(declarationIREmploye);
			//List<Details> details=detailsService.findByDeclarationIR(declarationIREmploye.getEmploye().getSalaire());
			
			//declarationIREmploye.setDetailsEmploye(details);
		//	detailsService.save(declarationIREmploye);
			
			
		}
		
		return 1;
		
		
	}

	

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




	
	
	
	
			

