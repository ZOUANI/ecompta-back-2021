package stage.sir.gestioncomptabilite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable;
import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DeclarationIRDao;

@Service
public class DeclarationIRService {
	
	@Autowired
	DeclarationIRDao declarationIRDao;
	
	
	public List<DeclarationIR> findByMoisAndAnnee(Integer mois,Integer annee) {
		return declarationIRDao.findByMoisAndAnnee(mois, annee);
	}

	@Autowired
	DeclarationIREmployeService declarationIREmployeService;

	@Autowired
	SocieteService societeService;
	
	
	
	

	
	
	public DeclarationIR findByRef(String ref) {
		return declarationIRDao.findByRef(ref);
	}
	public DeclarationIR findByAnnee(Integer annee) {
		return declarationIRDao.findByAnnee(annee);
	}
	@Transactional
	public int deleteByMois(Integer mois) {
		return declarationIRDao.deleteByMois(mois);
	}
	
	
	public void prepareDeclaration(DeclarationIR declarationIR) {
		List<DeclarationIREmploye> DeclarationIREmploye =declarationIREmployeService.findByDeclarationIREmployes(declarationIR);
		System.out.println(DeclarationIREmploye.toString());
		declarationIR.setDeclarationsIREmployes(DeclarationIREmploye);
		
		
		
	}
	
	
	
	public int save(DeclarationIR declarationIR) {
		if (declarationIRDao.findByRef(declarationIR.getRef())!=null) {
			return -1;
			
		}else {
			Societe societe=societeService.findByIce(declarationIR.getSociete().getIce());
			declarationIR.setSociete(societe);
			prepareDeclaration(declarationIR);
			
			
			double total=declarationIREmployeService.calculTotal(declarationIR.getDeclarationsIREmployes());
			declarationIR.setTotal(total);
			
			
			
			declarationIRDao.save(declarationIR);
			
			declarationIREmployeService.save(declarationIR);
			return 1;
		}
	}

	public List<DeclarationIR> findAll() {
		return declarationIRDao.findAll();
	}

	public Optional<DeclarationIR> findById(Long id) {
		return declarationIRDao.findById(id);
	}

}
