package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.EtatPaiement;
import stage.sir.gestioncomptabilite.service.EtatPaiementService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/etatpaiment")
public class EtatPaiementProvided {
    @Autowired
    private EtatPaiementService etatPaiementService;
    @GetMapping("/code/{code}")
    public EtatPaiement findByCode(@PathVariable double code) {
        return etatPaiementService.findByCode(code);
    }
    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable double code) {
        return etatPaiementService.deleteByCode(code);
    }
    @GetMapping("/")
    public List<EtatPaiement> findAll() {
        return etatPaiementService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody EtatPaiement etatPaiement) {
        return etatPaiementService.save(etatPaiement);
    }
}
