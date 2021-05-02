package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.service.DeclarationISService;
import stage.sir.gestioncomptabilite.vo.DeclarationIsObject;
import stage.sir.gestioncomptabilite.vo.DeclarationIsVo;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/declarationIS")
public class DeclarationIsProvided {

    @Autowired
    DeclarationISService declarationISService;


    @GetMapping("/ref/{ref}")
    public DeclarationIS findByRef(@PathVariable String ref) {
        return declarationISService.findByRef(ref);
    }

    @DeleteMapping("/ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return declarationISService.deleteByRef(ref);
    }

    @GetMapping("/societe/ice/{ice}")
    public List<DeclarationIS> findBySocieteIce(@PathVariable String ice) {
        return declarationISService.findBySocieteIce(ice);
    }

    @GetMapping("/annee/{annee}")
    public DeclarationIS findByAnnee(@PathVariable double annee) {
        return declarationISService.findByAnnee(annee);
    }

    @PostMapping("/criteria/")
    public List<DeclarationIS> searchCriteria(@RequestBody DeclarationIsVo declarationIsVo) {
        return declarationISService.searchCriteria(declarationIsVo);
    }

    @GetMapping("/montantISCalcule/rf/{rf}")
    public double calculMontantIS(@PathVariable double rf) {
        return declarationISService.calculMontantIS(rf);
    }

    @PostMapping("/frontEnd/")
    public DeclarationIsObject afficheDecIS(@RequestBody DeclarationIsObject decIsOb) {
        return declarationISService.afficheDecIS(decIsOb);
    }

    @GetMapping("/")
    public List<DeclarationIS> findAll() {
        return declarationISService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody DeclarationIS declarationIS) {
        return declarationISService.save(declarationIS);
    }
}
