package stage.sir.gestioncomptabilite.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Details;
import stage.sir.gestioncomptabilite.bean.TauxIr;
import stage.sir.gestioncomptabilite.dao.DetailsDao;

@Service
public class DetailsService {
	@Autowired
	DetailsDao detailsDao;
	@Autowired
	TauxIrService tauxIrService;
	@Autowired
	DeclarationIREmployeService declarationIREmployeService;
	
	
	public List<Details> findByDeclarationIR(Double salaire){
		List<TauxIr> decla =tauxIrService.findByDeclarationIR(salaire);
		List<Details> detaiList= new ArrayList<>();
		for(TauxIr tauxIr: decla) {
			//double pourc=(tauxIr.getPourcentage())/100;
			double diff=0;
			if (tauxIr.getSalaireMax()>salaire && tauxIr.getSalaireMin()<salaire) {
				diff=(salaire-tauxIr.getSalaireMin());
			}
			else if (tauxIr.getSalaireMax()<salaire) {
				diff=(tauxIr.getSalaireMax()-tauxIr.getSalaireMin());
			}
			
			
		Details details=new Details();
		
		details.setTauxIr(tauxIr);
		details.setValeur(diff);
		details.setPourcentage(tauxIr.getPourcentage());
		double trans=(diff*tauxIr.getPourcentage())/100;
		details.setMontantTrancheRevenu(trans);
		//details.setTrancheRevenu(tauxIr.getRefTaux());
			
			
		detaiList.add(details);
			
		}
	/*	DeclarationIREmploye declarationIREmploye=new DeclarationIREmploye();
		declarationIREmploye.setMontantIR(diff);
		declarationIREmploye.setSalaireNet(salaire);
		declarationIREmploye.setSalaireBrut(salaire+diff);*/
		
		return  detaiList;
	
	}

	public Optional<Details> findById(Long id) {
		return detailsDao.findById(id);
	}

	/*public Details findByTrancheRevenu(String trancheRevenu) {
		return detailsDao.findByTrancheRevenu(trancheRevenu);
	}*/

	public int save(DeclarationIREmploye declarationIREmploye) {
		for (Details details : declarationIREmploye.getDetailsEmploye()) {
			//System.out.println(declarationIREmploye.toString());
			
			details.setDeclarationIREmploye(declarationIREmploye);
			//DeclarationIREmploye decla=declarationIREmployeService.findByEmployeCin(declarationIREmploye.getEmploye().getCin());
			//details.setDeclarationIREmploye(decla);
			detailsDao.save(details);
			
			
		}
		return 1;
	}

	public List<Details> findAll() {
		return detailsDao.findAll();
	}
	@Transactional
	public void deleteById(Long id) {
		detailsDao.deleteById(id);
	}

}
