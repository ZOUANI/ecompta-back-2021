package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Acomptes;
import stage.sir.gestioncomptabilite.service.AcomptesService;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/acomptes")
public class AcomptesProvided {

    @GetMapping("/numero/{numero}")
    public List<Acomptes> findByNumero(@PathVariable Integer numero) { return acomptesService.findByNumero(numero); }

    @GetMapping("/ice/{ice}/annee/{annee}/numero/{numero}")
    public Acomptes findBySocieteIceAndAnneePayeAndNumero(@PathVariable String ice, @PathVariable double annee, @PathVariable Integer numero) {
        return acomptesService.findBySocieteIceAndAnneePayeAndNumero(ice, annee, numero);
    }

    @DeleteMapping("/ice/{ice}/annee/{annee}")
    public int deleteBySocieteIceAndAnneePaye(@PathVariable String ice, @PathVariable double annee) {
        return acomptesService.deleteBySocieteIceAndAnneePaye(ice, annee);
    }

    @DeleteMapping("/numero/{numero}")
    public int deleteByNumero(@PathVariable Integer numero) {
        return acomptesService.deleteByNumero(numero);
    }

    @GetMapping("/")
    public List<Acomptes> findAll() {
        return acomptesService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Acomptes acomptes) {
        return acomptesService.save(acomptes);
    }

    @Autowired
    AcomptesService acomptesService;
}