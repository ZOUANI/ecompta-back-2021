package stage.sir.gestioncomptabilite.ws;

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

import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Details;
import stage.sir.gestioncomptabilite.service.DetailsService;

@RestController
@RequestMapping("gestion-comptabilite/details")
public class DetailsProvided {
	@Autowired
	DetailsService detailsService;
	@GetMapping("/id/{id}")
	public Optional<Details> findById(@PathVariable Long id) {
		return detailsService.findById(id);
	}
	/*@GetMapping("trancheRevenu/{trancheRevenu}")
	public Details findByTrancheRevenu(@PathVariable String trancheRevenu) {
		return detailsService.findByTrancheRevenu(trancheRevenu);
	}*/
	
	@GetMapping("/")
	public List<Details> findAll() {
		return detailsService.findAll();
	}
	@DeleteMapping("id/{id}")
	public void deleteById(@PathVariable Long id) {
		detailsService.deleteById(id);
	}

}
