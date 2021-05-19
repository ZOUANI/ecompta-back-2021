package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.EtatOperationSociete;
import stage.sir.gestioncomptabilite.service.EtatOperationSocieteService;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/EtatOperationSociete")
public class EtatOperationSocieteProvided {
    @GetMapping("ref/{ref}")
    public EtatOperationSociete findByRef(@PathVariable String ref) {
        return etatOperationSocieteService.findByRef(ref);
    }

    @DeleteMapping("ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return etatOperationSocieteService.deleteByRef(ref);
    }
    @GetMapping("/")
    public List<EtatOperationSociete> findAll() {
        return etatOperationSocieteService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody EtatOperationSociete etatOperationSociete) {
        return etatOperationSocieteService.save(etatOperationSociete);
    }

    @Autowired
    EtatOperationSocieteService etatOperationSocieteService;
}
