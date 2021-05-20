package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Etape;
import stage.sir.gestioncomptabilite.bean.TypeOperation;
import stage.sir.gestioncomptabilite.service.EtapeService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/etape")
public class EtapeProvided {
    @Autowired
    private EtapeService etapeService;
    @GetMapping("/libelle/{libelle}")
    public Etape findByLibelle(@PathVariable String libelle) {
        return etapeService.findByLibelle(libelle);
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return etapeService.deleteByLibelle(libelle);
    }
    @GetMapping("/findByTypeOperation/libelle/{libelle}")
    public List<Etape> findByTypeOperationLibelle(@PathVariable String libelle) {
        return etapeService.findByTypeOperationLibelle(libelle);
    }

    @DeleteMapping("/deleteByTypeOperation/libelle/{libelle}")
    public int deleteByTypeOperationLibelle(@PathVariable String libelle) {
        return etapeService.deleteByTypeOperationLibelle(libelle);
    }
    @PutMapping("/")
    public void update(@RequestBody Etape etape) {
        etapeService.update(etape);
    }

    @GetMapping("/")
    public List<Etape> findAll() {
        return etapeService.findAll();
    }
    /*@PostMapping("/")
    public int save(@RequestBody TypeOperation typeOperation,@PathVariable List<Etape> etapes) {
        return etapeService.save(typeOperation, etapes);
    }*/
}
