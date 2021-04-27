package stage.sir.gestioncomptabilite.webService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stage.sir.gestioncomptabilite.bean.CategorieService;
import stage.sir.gestioncomptabilite.service.CategorieServiceService;

@RestController
@RequestMapping("gestion-comptabilite/categorieService")
public class CategorieServiceProvided {
	@Autowired
	CategorieServiceService categorieServiceService;

	@GetMapping("/id/{id}")
	public Optional<CategorieService> findById(@PathVariable Long id) {
		return categorieServiceService.findById(id);
	}
	@GetMapping("/titre/{titre}")
	public CategorieService findByTitre(@PathVariable String titre) {
		return categorieServiceService.findByTitre(titre);
	}
	@DeleteMapping("/titre/{titre}")
	public int deleteByTitre(@PathVariable String titre) {
		return categorieServiceService.deleteByTitre(titre);
	}
	@PostMapping("/")
	public int save(@RequestBody CategorieService categorieService) {
		return categorieServiceService.save(categorieService);
	}
	@GetMapping("/")
	public List<CategorieService> findAll() {
		return categorieServiceService.findAll();
	}

}
