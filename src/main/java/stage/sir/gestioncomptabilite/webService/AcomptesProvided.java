package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Acomptes;
import stage.sir.gestioncomptabilite.service.AcomptesService;
import stage.sir.gestioncomptabilite.vo.AcomptesVo;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/acomptes")
public class AcomptesProvided {

    @GetMapping("/numero/{numero}")
    public List<Acomptes> findByNumero(@PathVariable Integer numero) { return acomptesService.findByNumero(numero); }

    @GetMapping("/ice/{ice}/annee/{annee}/numero/{numero}")
    public Acomptes findBySocieteIceAndAnneeAndNumero(@PathVariable String ice, @PathVariable double annee, @PathVariable Integer numero) {
        return acomptesService.findBySocieteIceAndAnneeAndNumero(ice, annee, numero);
    }

    @DeleteMapping("/ice/{ice}/annee/{annee}/numero/{numero}")
    public int deleteBySocieteIceAndAnneeAndNumero(@PathVariable String ice, @PathVariable double annee, @PathVariable Integer numero) {
        return acomptesService.deleteBySocieteIceAndAnneeAndNumero(ice, annee, numero);
    }

    @GetMapping("/ice/{ice}/annee/{annee}")
    public List<Acomptes> findBySocieteIceAndAnnee(@PathVariable String ice, @PathVariable double annee) { return acomptesService.findBySocieteIceAndAnnee(ice, annee); }

    @DeleteMapping("/ice/{ice}/annee/{annee}")
    public int deleteBySocieteIceAndAnnee(@PathVariable String ice, @PathVariable double annee) {
        return acomptesService.deleteBySocieteIceAndAnnee(ice, annee);
    }

    @PostMapping("/recherche-multi-critere/")
    public List<Acomptes> SearchCriteria(@RequestBody AcomptesVo acomptesVo) { return acomptesService.SearchCriteria(acomptesVo); }

    @PostMapping("delete-multiple-by-societe-ice-and-annee-and-numero")
    public int deleteMultipleBySocieteIceAndAnnee(@RequestBody List<Acomptes> acomptesList) {
        return acomptesService.deleteMultipleBySocieteIceAndAnnee(acomptesList);
    }

    @GetMapping("/")
    public List<Acomptes> findAll() {
        return acomptesService.findAll();
    }

    @PutMapping("/")
    public int update(@RequestBody Acomptes acomptes) {
        return acomptesService.update(acomptes);
    }

    @PostMapping("/")
    public int save(@RequestBody Acomptes acomptes) {
        return acomptesService.save(acomptes);
    }

    @Autowired
    AcomptesService acomptesService;
}
