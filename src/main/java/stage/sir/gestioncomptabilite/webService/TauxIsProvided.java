package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.TauxIS;
import stage.sir.gestioncomptabilite.service.TauxISService;
import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/tauxIS")
public class TauxIsProvided {

    @Autowired
    TauxISService tauxISService;

    @GetMapping("/reference/{ref}")
    public TauxIS findByRef(@PathVariable String ref) {
        return tauxISService.findByRef(ref);
    }

    @DeleteMapping("/reference/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return tauxISService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<TauxIS> findAll() {
        return tauxISService.findAll();
    }

    @PostMapping("/")
    public int save(TauxIS tauxIS) {
        return tauxISService.save(tauxIS);
    }
}
