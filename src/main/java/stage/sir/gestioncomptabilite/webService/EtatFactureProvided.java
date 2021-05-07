package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.EtatFacture;
import stage.sir.gestioncomptabilite.service.EtatFactureService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/etatfacture")
public class EtatFactureProvided {
    @Autowired
    private EtatFactureService etatFactureService;
    @GetMapping("/code/{code}")
    public EtatFacture findByCode(@PathVariable double code) {
        return etatFactureService.findByCode(code);
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable double code) {
        return etatFactureService.deleteByCode(code);
    }
    @GetMapping("/")
    public List<EtatFacture> findAll() {
        return etatFactureService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody EtatFacture etatFacture) {
        return etatFactureService.save(etatFacture);
    }
}
