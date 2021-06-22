package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.ClasseComptable;
import stage.sir.gestioncomptabilite.service.ClassComptableService;

import java.util.List;

@RestController
@RequestMapping("gestion-class/class-comptable")
public class ClassComptableProvided {
    @Autowired
    private ClassComptableService classComptableService;

    @GetMapping("/numero/{numero}")
    public ClasseComptable findByNumero(@PathVariable int numero) {
        return classComptableService.findByNumero(numero);
    }

    @DeleteMapping("/numero/{numero}")
    public int deleteByNumero(@PathVariable int numero) {
        return classComptableService.deleteByNumero(numero);
    }

    @GetMapping("/")
    public List<ClasseComptable> findAll() {
        return classComptableService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody ClasseComptable classeComptable) {
         classComptableService.save(classeComptable);
        return 1;
    }
    @PutMapping("/")
    public int update(@RequestBody ClasseComptable classeComptable) {
        return classComptableService.update(classeComptable);
    }

    @PostMapping("/alone/")
    public int saveAlone(@RequestBody ClasseComptable classeComptable) {
        classComptableService.saveAlone(classeComptable);
        return 1;
    }
}
