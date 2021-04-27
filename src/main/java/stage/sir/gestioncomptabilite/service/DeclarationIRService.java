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

	public List<DeclarationIR> findByMoisAndAnnee(Integer mois, Integer annee) {
		return declarationIRDao.findByMoisAndAnnee(mois, annee);
	}

	public int save(DeclarationIR declarationIR) {
		if (declarationIRDao.findById(declarationIR.getId())!=null) {
			return -1;
			
		}else {
			Societe societe=societeService.findByIce(declarationIR.getSociete().getIce());
			declarationIR.setSociete(societe);
			declarationIRDao.save(declarationIR);
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
