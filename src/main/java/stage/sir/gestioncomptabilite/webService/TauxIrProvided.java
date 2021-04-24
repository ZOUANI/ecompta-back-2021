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

import stage.sir.gestioncomptabilite.bean.TauxIr;
import stage.sir.gestioncomptabilite.service.TauxIrService;

@RestController
@RequestMapping("gestion-comptabilite/tauxir")
public class TauxIrProvided {

	@Autowired
	TauxIrService tauxIrService;
	
	@GetMapping("/id/{id}")
	public Optional<TauxIr> findById(@PathVariable Long id) {
		return tauxIrService.findById(id);
	}
	@DeleteMapping("/id/{id}")
	public void deleteById(@PathVariable Long id) {
		tauxIrService.deleteById(id);
	}
	@PostMapping("/")
	public int save(@RequestBody TauxIr tauxIr) {
		return tauxIrService.save(tauxIr);
	}
	@GetMapping("/")
	public List<TauxIr> findAll() {
		return tauxIrService.findAll();
	}
	
	
	
	
	
}
