package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.PaiementIS;
import stage.sir.gestioncomptabilite.service.PaiementISService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/paiementIS")
public class PaiementISProvided {

    @GetMapping("/ref/{ref}")
    public PaiementIS findByRef(@PathVariable String ref) {
        return paiementISService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return paiementISService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<PaiementIS> findAll() {
        return paiementISService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody PaiementIS paiementIS) {
        return paiementISService.save(paiementIS);
    }

    @Autowired
    PaiementISService paiementISService;
}
