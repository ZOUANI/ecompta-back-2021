package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.EtatDemande;
import stage.sir.gestioncomptabilite.service.EtatDemandeService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/etatDemande")
public class EtatDemandeProvided {

    @GetMapping("/ref/{ref}")
    public EtatDemande findByRef(@PathVariable String ref) {
        return etatDemandeService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return etatDemandeService.deleteByRef(ref);
    }

    @GetMapping("/libelle/{libelle}")
    public EtatDemande findByLibelle(@PathVariable String libelle) {
        return etatDemandeService.findByLibelle(libelle);
    }

    @GetMapping("/")
    public List<EtatDemande> findAll() {
        return etatDemandeService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody EtatDemande etatDemande) {
        return etatDemandeService.save(etatDemande);
    }

    @Autowired
    EtatDemandeService etatDemandeService;
}
