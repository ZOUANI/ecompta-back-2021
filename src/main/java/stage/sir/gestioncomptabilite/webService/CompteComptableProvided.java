package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.CompteComptable;
import stage.sir.gestioncomptabilite.service.CompteComptableService;

import java.util.List;

@RestController
@RequestMapping("gestion-categorie/categorie")
public class CompteComptableProvided {
    @Autowired
    private CompteComptableService compteComptableService;
    @GetMapping("/SousClasseComptable/num/{numero}")
    public List<CompteComptable> findBySousClasseComptableNumero(@PathVariable int numero) {
        return compteComptableService.findBySousClasseComptableNumero(numero);
    }
    @GetMapping("/")
    public List<CompteComptable> findAll() {
        return compteComptableService.findAll();
    }

    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return compteComptableService.deleteByCode(code);
    }

    @DeleteMapping("/sousClasseComptable-num/{numero}")
    public int deleteBySousClasseComptableNumero(@PathVariable int numero) {
        return compteComptableService.deleteBySousClasseComptableNumero(numero);
    }
    @PutMapping("/")
    public int update(@RequestBody CompteComptable compteComptable) {
        return compteComptableService.update(compteComptable);
    }

    @PostMapping("/sousClass-num/{numero}/")
    public int saveAlone(@PathVariable int numero,@RequestBody CompteComptable compteComptable) {
        return compteComptableService.saveAlone(compteComptable, numero);
    }
}
