package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Comptable;
import stage.sir.gestioncomptabilite.service.ComptableService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/comptable")
public class ComptableProvided {
    @Autowired
    private ComptableService comptableService;
    @GetMapping("/code/{code}")
    public Comptable findByCode(@PathVariable String code) {
        return comptableService.findByCode(code);
    }
    @DeleteMapping("/code/{code}")
    public int deleteByCode(@PathVariable String code) {
        return comptableService.deleteByCode(code);
    }
    @GetMapping("/")
    public List<Comptable> findAll() {
        return comptableService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody Comptable comptable) {
        return comptableService.save(comptable);
    }
}
