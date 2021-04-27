package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.service.FactureService;
import stage.sir.gestioncomptabilite.vo.ObjectVo;


import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/facture")
public class FactureProvided {
    @Autowired
    private FactureService factureService;

    @GetMapping("/ref/{ref}")
    public Facture findByRef(@PathVariable String ref) {
        return factureService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return factureService.deleteByRef(ref);
    }
    @GetMapping("/societeSource/ice/{ice}/annee/{annee}/trim/{trim}")
    public List<Facture> findBySocieteSourceIceAndAnneeAndTrim(@PathVariable String ice,@PathVariable double annee,@PathVariable double trim) {
        return factureService.findBySocieteSourceIceAndAnneeAndTrim(ice, annee, trim);
    }
    @GetMapping("/societeSource/ice/{ice}/annee/{annee}/mois/{mois}/m2")
    public List<Facture> findBySocieteSourceIceAndAnneeAndMois(@PathVariable String ice,@PathVariable double annee,@PathVariable double mois) {
        return factureService.findBySocieteSourceIceAndAnneeAndMois(ice, annee, mois);
    }
    @GetMapping("/societeSource/ice/{ice}/annee/{annee}/trim/{trim}/typeoperation/{typeoperation}")
    public List<Facture> findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(@PathVariable String ice, @PathVariable double annee,@PathVariable double trim,@PathVariable String typeoperation) {
        return factureService.findBySocieteSourceIceAndAnneeAndTrimAndTypeOperation(ice, annee, trim, typeoperation);
    }
    @GetMapping("/societeSource/ice/{ice}/annee/{annee}/mois/{mois}/typeoperation/{typeoperation}/m2")
    public List<Facture> findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(@PathVariable String ice, @PathVariable double annee,@PathVariable double mois,@PathVariable String typeoperation) {
        return factureService.findBySocieteSourceIceAndAnneeAndMoisAndTypeOperation(ice, annee, mois, typeoperation);
    }

    @GetMapping("/societeSource/ice/{ice}/annee/{annee}")
    public List<Facture> findBySocieteSourceIceAndAnnee(@PathVariable String ice, @PathVariable double annee) {
        return factureService.findBySocieteSourceIceAndAnnee(ice, annee);
    }

    @GetMapping("/societeSource/ice/{ice}/annee/{annee}/typeoperation/{typeoperation}")
    public List<Facture> findBySocieteSourceIceAndAnneeAndTypeOperation( @PathVariable String ice, @PathVariable double annee, @PathVariable String typeoperation) {
        return factureService.findBySocieteSourceIceAndAnneeAndTypeOperation(ice, annee, typeoperation);
    }

    @GetMapping("/")
    public List<Facture> findAll() {
        return factureService.findAll();
    }

    @PutMapping("/")
    public void update(@RequestBody Facture facture) {
        factureService.update(facture);
    }

    @PostMapping("/")
    public int save(@RequestBody Facture facture) {
        return factureService.save(facture);
    }
    @PostMapping("/MultiTache")
    public List<Facture> findByMultiTache(@RequestBody ObjectVo objectVo) {
        return factureService.findByMultiTache(objectVo);
    }
}