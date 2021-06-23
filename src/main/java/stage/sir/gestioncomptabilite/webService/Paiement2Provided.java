package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Paiement2;
import stage.sir.gestioncomptabilite.service.Paiement2Service;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/paiement2")
public class Paiement2Provided {

    @GetMapping("/ref/{ref}")
    public Paiement2 findByRef(@PathVariable String ref) {
        return paiement2Service.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return paiement2Service.deleteByRef(ref);
    }

    @GetMapping("/bydecltvaref/ref/{ref}")
    public List<Paiement2> findByDeclarationTvaRef(@PathVariable String ref) {
        return paiement2Service.findByDeclarationTvaRef(ref);
    }
    @GetMapping("/bydeclisref/ref/{ref}")
    public List<Paiement2> findByDeclarationISRef(@PathVariable String ref) {
        return paiement2Service.findByDeclarationISRef(ref);
    }
    @GetMapping("/")
    public List<Paiement2> findAll() {
        return paiement2Service.findAll();
    }

    @PostMapping("/fortva")
    public int savepaiementtva(@RequestBody Paiement2 paiement2) {
        return paiement2Service.savepaiementtva(paiement2);
    }
    @PostMapping("/foris")
    public int savepaiementis(@RequestBody Paiement2 paiement2) {
        return paiement2Service.savepaiementis(paiement2);
    }
    @PostMapping("/forir")
    public int savepaiementir(@RequestBody Paiement2 paiement2) {
        return paiement2Service.savepaiementir(paiement2);
    }

    @Autowired
    Paiement2Service paiement2Service;
}
