package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import stage.sir.gestioncomptabilite.bean.Employe;
import stage.sir.gestioncomptabilite.bean.Societe;
import stage.sir.gestioncomptabilite.service.SocieteService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/societe")
public class SocieteProvided {
    @Autowired
    SocieteService societeService;
    @GetMapping("/iceSociete/{iceSociete}")
    public List<Employe> findListEmployes(@PathVariable String iceSociete){
    	
    	return societeService.findListEmployes(iceSociete);	
    }
    
    
    /*
    @PostMapping("/loginSociete")
    public Societe loginSociete(@RequestBody Societe societe) throws Exception {
		return societeService.loginSociete(societe);
	}
    
    @GetMapping("/login/{login}/password/{password}")
    public Societe findByLoginAndPassword(@PathVariable String login,@PathVariable String password) {
		return societeService.findByLoginAndPassword(login, password);
	}*/

	@GetMapping("/ice/{ice}")
    public Societe findByIce(@PathVariable String ice) {
        return societeService.findByIce(ice);
    }

    @DeleteMapping("/ice/{ice}")
    public int deleteByIce(@PathVariable String ice) {
        return societeService.deleteByIce(ice);
    }

    @GetMapping("/")
    public List<Societe> findAll() {
        return societeService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Societe societe) {
        return societeService.save(societe);
    }
    @PostMapping("/save2")
    public int save2(@RequestBody Societe societe) {
        return societeService.save2(societe);
    }

}
