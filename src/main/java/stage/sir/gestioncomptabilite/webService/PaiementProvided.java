package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Paiement;
import stage.sir.gestioncomptabilite.service.PaiementService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/paiement")
public class PaiementProvided {
    @Autowired
    private PaiementService paiementService;
    @GetMapping("/ref/{ref}")
    public Paiement findByRef(@PathVariable String ref) {
        return paiementService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return paiementService.deleteByRef(ref);
    }
    @GetMapping("/")
    public List<Paiement> findAll() {
        return paiementService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody Paiement paiement) {
        return paiementService.save(paiement);
    }
}
