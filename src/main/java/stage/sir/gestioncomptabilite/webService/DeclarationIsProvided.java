package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.service.DeclarationISService;
import stage.sir.gestioncomptabilite.vo.DeclarationIsVo;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/declarationIS")
public class DeclarationIsProvided {

    @Autowired
    DeclarationISService declarationISService;

    @GetMapping("/societe/ice/{ice}")
    public List<DeclarationIS> findBySocieteIce(@PathVariable String ice) {
        return declarationISService.findBySocieteIce(ice);
    }
    @GetMapping("annee/{annee}")
    public DeclarationIS findByAnnee(@PathVariable Integer annee) {
        return declarationISService.findByAnnee(annee);
    }
    @GetMapping("ref/{ref}")
    public DeclarationIS findByRef(@PathVariable String ref) {
        return declarationISService.findByRef(ref);
    }

    @DeleteMapping("ref{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return declarationISService.deleteByRef(ref);
    }

    @DeleteMapping("annee/{annee}")
    public int deleteByAnnee(@PathVariable Integer annee) {
        return declarationISService.deleteByAnnee(annee);
    }

    @DeleteMapping("/societe/ice/{ice}")
    public int deleteBySocieteIce(@PathVariable String ice) {
        return declarationISService.deleteBySocieteIce(ice);
    }

    @PostMapping("/criteria/")
    public List<DeclarationIS> searchCriteria(@RequestBody DeclarationIsVo declarationIsVo) {
        return declarationISService.searchCriteria(declarationIsVo);
    }

    @GetMapping("/")
    public List<DeclarationIS> findAll() {
        return declarationISService.findAll();
    }

    @PostMapping("/")
    public void save(@RequestBody DeclarationIS declarationIS) {
        declarationISService.save(declarationIS);
    }
}
