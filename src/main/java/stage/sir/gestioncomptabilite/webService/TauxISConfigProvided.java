package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.TauxIsConfig;
import stage.sir.gestioncomptabilite.service.TauxISConfigService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/tauxISConfig")
public class TauxISConfigProvided {
    @Autowired
    TauxISConfigService tauxISConfigService;

    @GetMapping("/ref/{ref}")
    public TauxIsConfig findByRef(@PathVariable String ref) {
        return tauxISConfigService.findByRef(ref);
    }

    @GetMapping("/")
    public List<TauxIsConfig> findAll() {
        return tauxISConfigService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody TauxIsConfig tauxIsConfig) {
        return tauxISConfigService.save(tauxIsConfig);
    }
}
