package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.OperationSocieteJustif;
import stage.sir.gestioncomptabilite.service.OperationSocieteJustifService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/OperationSocieteJustif")
public class OperationSocieteJustifProvided {
    @GetMapping("ref/{ref}")
    public OperationSocieteJustif findByRef(@PathVariable String ref) {
        return operationSocieteJustifService.findByRef(ref);
    }

    @DeleteMapping("ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return operationSocieteJustifService.deleteByRef(ref);
    }
    @GetMapping("chemin/{chemin}")
    public OperationSocieteJustif findByChemin(@PathVariable String chemin) {
        return operationSocieteJustifService.findByChemin(chemin);
    }

    @DeleteMapping("chemin/{chemin}")
    public int deleteByChemin(@PathVariable String chemin) {
        return operationSocieteJustifService.deleteByChemin(chemin);
    }
    @GetMapping("date/{dateJustif}")
    public OperationSocieteJustif findByDateJustif(@PathVariable Date dateJusti) {
        return operationSocieteJustifService.findByDateJustif(dateJusti);
    }

    @DeleteMapping("date/{dateJustif}")
    public int deleteByDateJustif(@PathVariable Date dateJustif) {
        return operationSocieteJustifService.deleteByDateJustif(dateJustif);
    }
    @GetMapping("/")
    public List<OperationSocieteJustif> findAll() {
        return operationSocieteJustifService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody OperationSocieteJustif operationSocieteJustif) {
        return operationSocieteJustifService.save(operationSocieteJustif);
    }

    @Autowired
    OperationSocieteJustifService operationSocieteJustifService;
}
