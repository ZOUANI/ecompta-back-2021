package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.TypeOperation;
import stage.sir.gestioncomptabilite.service.TypeOperationService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/typroperation")
public class TypeOperationProvided {
    @Autowired
    private TypeOperationService typeOperationService;
    @GetMapping("/libelle/{libelle}")
    public TypeOperation findByLibelle(@PathVariable String libelle) {
        return typeOperationService.findByLibelle(libelle);
    }

    @DeleteMapping("/libelle/{libelle}")
    public int deleteByLibelle(@PathVariable String libelle) {
        return typeOperationService.deleteByLibelle(libelle);
    }
    @GetMapping("/")
    public List<TypeOperation> findAll() {
        return typeOperationService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody TypeOperation typeOperation) {
        return typeOperationService.save(typeOperation);
    }
}
