package stage.sir.gestioncomptabilite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.bytebuddy.dynamic.scaffold.InstrumentedType.Prepareable;
import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.dao.DeclarationIRDao;

@Service
public class DeclarationIRService {
	
	@Autowired
	DeclarationIRDao declarationIRDao;
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
		List<DeclarationIREmploye> DeclarationIREmploye = declarationIREmployeService.findByDeclarationIREmployes(declarationIR);
				
		
		declarationIR.setDeclarationsIREmployes(DeclarationIREmploye);

	}
	
	
	

	public List<DeclarationIR> findByMoisAndAnnee(Integer mois, Integer annee) {
		return declarationIRDao.findByMoisAndAnnee(mois, annee);
	}
	
	
	
	
	
	
/*	public DeclarationIR createDeclarationIr(DeclarationIR declarationIR) {
		Object[] res = save(declarationIR, true);
		if (Integer.parseInt(res[0].toString()) > 0) {
			return (DeclarationIR) res[1];
		} else {
			return null;
		}

	}*/
	
	public List<DeclarationIREmploye> createDeclarationIr(DeclarationIR declarationIR) {
		
		
		Object[] res = save(declarationIR, true,false);
		if (Integer.parseInt(res[0].toString()) > 0) {
			DeclarationIR dec=  (DeclarationIR) res[1];
			return (List<DeclarationIREmploye>) dec.getDeclarationsIREmployes();
		} else {
			return null;
		}

	}
	
	public int saveModification(DeclarationIR declarationIR) {
		Object[] saveModi = save(declarationIR, false,true);
		return 1;
	}
	
	public int save(DeclarationIR declarationIR) {
		return (Integer) save(declarationIR,false,false)[0];
	}

	private Object[] save(DeclarationIR declarationIR, boolean simuler,boolean saveModification) {
		if (declarationIRDao.findByRef(declarationIR.getRef())!=null) {
			return new Object[] { -1, null };
			
		}else {
			
			Societe societe = societeService.findByIce("2");
			
			declarationIR.setSociete(societe);
			if (saveModification ==false) {
				
			
			prepareDeclaration(declarationIR);
			}
			double total = declarationIREmployeService.calculTotal(declarationIR.getDeclarationsIREmployes());
			declarationIR.setTotal(total);
			
			if (simuler == false) {
				declarationIRDao.save(declarationIR);
				declarationIREmployeService.save(declarationIR);
			}
			return new Object[] { 1, declarationIR };
		}
		}
	

	public List<DeclarationIR> findAll() {
		return declarationIRDao.findAll();
	}

	public Optional<DeclarationIR> findById(Long id) {
		return declarationIRDao.findById(id);
	}



}
