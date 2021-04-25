package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.ClassComptable;
import stage.sir.gestioncomptabilite.service.ClassComptableService;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/class-comptable")
public class ClassComptableProvided {
    @Autowired
    private ClassComptableService classComptableService;

    @GetMapping("ref/{ref}")
    public ClassComptable findByRef(@PathVariable String ref) {
        return classComptableService.findByRef(ref);
    }

    @DeleteMapping("ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return classComptableService.deleteByRef(ref);
    }

    @GetMapping("/")
    public List<ClassComptable> findAll() {
        return classComptableService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody ClassComptable classComptable) {
        return classComptableService.save(classComptable);
    }
}
