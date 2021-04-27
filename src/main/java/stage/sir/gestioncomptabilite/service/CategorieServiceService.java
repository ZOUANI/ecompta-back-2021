package stage.sir.gestioncomptabilite.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stage.sir.gestioncomptabilite.bean.CategorieService;
import stage.sir.gestioncomptabilite.dao.CategorieServiceDao;

@Service
public class CategorieServiceService {
	@Autowired
	CategorieServiceDao categorieServiceDao;

	
	
	
	
	
	public Optional<CategorieService> findById(Long id) {
		return categorieServiceDao.findById(id);
	}
	public List<CategorieService> findAll() {
		return categorieServiceDao.findAll();
	}
	public CategorieService findByTitre(String titre) {
		return categorieServiceDao.findByTitre(titre);
	}
	@Transactional
	public int deleteByTitre(String titre) {
		return categorieServiceDao.deleteByTitre(titre);
	}

	public int save(CategorieService categorieService) {
		if(findByTitre(categorieService.getTitre())!=null) {
			return -1;
		}else {
			categorieServiceDao.save(categorieService);
			return 1;
		}
		
	}



	

}
