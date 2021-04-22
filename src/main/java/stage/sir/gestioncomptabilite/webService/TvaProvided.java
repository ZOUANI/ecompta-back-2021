package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Tva;
import stage.sir.gestioncomptabilite.service.TvaService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/tva")
public class TvaProvided {
    @Autowired
    TvaService tvaService;
    @GetMapping("/ref/{ref}")
    public Tva findByRef(@PathVariable String ref) {
        return tvaService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return tvaService.deleteByRef(ref);
    }
    @GetMapping("/")
    public List<Tva> findAll() {
        return tvaService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody Tva tva) {
        return tvaService.save(tva);
    }
}