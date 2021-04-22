package stage.sir.gestioncomptabilite.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stage.sir.gestioncomptabilite.bean.TauxIr;
import stage.sir.gestioncomptabilite.dao.TauxIrDao;

@Service
public class TauxIrService {
	@Autowired
	TauxIrDao tauxIrDao;
	
	
	
	

	public List<TauxIr> findByDeclarationIR(Double salaire) {
		List<TauxIr> tauxIrs=findAll();
		List<TauxIr> resultaTauxIrs= new ArrayList<TauxIr>();
		for(TauxIr tauxIr : tauxIrs) {
			if (tauxIr.getSalaireMax() >=salaire && tauxIr.getSalaireMin()<=salaire || salaire>tauxIr.getSalaireMax()) {
				
				resultaTauxIrs.add(tauxIr);
			}
			
		}
		return resultaTauxIrs;
		
	}

	public Optional<TauxIr> findById(Long id) {
		return tauxIrDao.findById(id);
	}
	@Transactional
	public void deleteById(Long id) {
		tauxIrDao.deleteById(id);
	}

	public int save(TauxIr tauxIr) {
		
		 if(tauxIr.getSalaireMax()<tauxIr.getSalaireMin()) {
			return-2;
		}else {
			tauxIrDao.save(tauxIr);
			return 1;
		}
	}

	public List<TauxIr> findAll() {
		return tauxIrDao.findAll();
	}

	
}
