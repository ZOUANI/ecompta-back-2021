package stage.sir.gestioncomptabilite.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.SousClasseComptable;
import stage.sir.gestioncomptabilite.service.SousClasseComptableService;

import java.util.List;

@RestController
@RequestMapping("gestion-section/section")
public class SousClasseComptableProvided {
    @Autowired
    private SousClasseComptableService sousClasseComptableService;

    @GetMapping("/ClasseComptable/numero/{numero}")
    public List<SousClasseComptable> findByClasseComptableNumero(@PathVariable int numero) {
        return sousClasseComptableService.findByClasseComptableNumero(numero);
    }
@GetMapping("/numero/{numero}")
    public SousClasseComptable findByNumero(@PathVariable int numero) {
        return sousClasseComptableService.findByNumero(numero);
    }

    @DeleteMapping("/ClasseComptable-num/{numero}")
    public int deleteByClasseComptableNumero(@PathVariable int numero) {
        return sousClasseComptableService.deleteByClasseComptableNumero(numero);
    }

    @DeleteMapping("/numero/{numero}")
    public int deleteByNumero(@PathVariable int numero) {
        return sousClasseComptableService.deleteByNumero(numero);
    }

    @GetMapping("/")
    public List<SousClasseComptable> findAll() {
        return sousClasseComptableService.findAll();
    }

    @PutMapping("/")
    public int update(@RequestBody SousClasseComptable sousClasseComptable) {
        return sousClasseComptableService.update(sousClasseComptable);
    }

    @PostMapping("/class-num/{numero}/")
    public int saveAlone(@PathVariable int numero,@RequestBody SousClasseComptable sousClasseComptable) {
        return sousClasseComptableService.saveAlone(sousClasseComptable, numero);
    }
}
