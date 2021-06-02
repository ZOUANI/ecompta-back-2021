package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.OperationSociete;
import stage.sir.gestioncomptabilite.service.OperationSocieteService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/operationSociete")
public class OperationSocieteProvided {
    @Autowired
    private OperationSocieteService operationSocieteService;
    @GetMapping("/ref/{ref}")
    public OperationSociete findByRef(@PathVariable String ref) {
        return operationSocieteService.findByRef(ref);
    }
    @GetMapping("/ice/{ice}")
    public List<OperationSociete> findBySocieteIce(@PathVariable String ice) {
        return operationSocieteService.findBySocieteIce(ice);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return operationSocieteService.deleteByRef(ref);
    }
    @GetMapping("/")
    public List<OperationSociete> findAll() {
        return operationSocieteService.findAll();
    }
    @PostMapping("/")
    public int saveAlone(@RequestBody OperationSociete operationSociete) {
        return operationSocieteService.saveAlone(operationSociete);
    }
}
