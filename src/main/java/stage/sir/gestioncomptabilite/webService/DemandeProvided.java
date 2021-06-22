package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Demande;
import stage.sir.gestioncomptabilite.service.DemandeService;
import stage.sir.gestioncomptabilite.vo.DemandeVo;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/demande")
public class DemandeProvided {
	
	
	
	
	/*@GetMapping("/comptableTraiteur/code/{code}/annee/{annee}/mois/{mois}")
	public List<Demande> findBycomptableTraiteurCodeAndAnneeAndMois(@PathVariable String code,@PathVariable Double annee,@PathVariable Integer mois) {
		return demandeService.findBycomptableTraiteurCodeAndAnneeAndMois(code, annee, mois);
	}*/
	@GetMapping("/societe/ice/{ice}")
	public List<Demande> findBySocieteIce(@PathVariable String ice) {
		return demandeService.findBySocieteIce(ice);
	}

	@GetMapping("/comptableValidateur/code/{code}")
	public List<Demande> findBycomptableValidateurCode(@PathVariable String code) {
		return demandeService.findBycomptableValidateurCode(code);
	}

	@GetMapping("/comptableTraiteur/code/{code}")
    public List<Demande> findBycomptableTraiteurCode(@PathVariable String code) {
		return demandeService.findBycomptableTraiteurCode(code);
	}

	@GetMapping("/ref/{ref}")
    public Demande findByRef(@PathVariable String ref) {
        return demandeService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return demandeService.deleteByRef(ref);
    }

    @GetMapping("/operation/{operation}")
    public List<Demande> findByOperation(@PathVariable String operation) {
        return demandeService.findByOperation(operation);
    }

    @GetMapping("/")
    public List<Demande> findAll() {
        return demandeService.findAll();
    }

    @PostMapping("/recherche-multi-critere/")
    public List<Demande> searchCriteria(@RequestBody DemandeVo demandeVo) {
        return demandeService.searchCriteria(demandeVo);
    }

    @PutMapping("/")
    public int edit(@RequestBody Demande demande) {
        return demandeService.edit(demande);
    }

    @PostMapping("/")
    public int save(@RequestBody Demande demande) {
        return demandeService.save(demande);
    }

    @Autowired
    DemandeService demandeService;
    /*
	@PostMapping("/searchDemandeCriteria")
	public List<Demande> searchDemandeCriteria(@RequestBody DemandeVo demandeVo) {
    	
    	
		return demandeService.searchDemandeCriteria(demandeVo);
	}

     */

}
