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

    @GetMapping("/etatDeclaration/libelle/{libelle}")
    public List<DeclarationIS> findByEtatDeclarationLibelle(@PathVariable String libelle) {
        return declarationISService.findByEtatDeclarationLibelle(libelle);
    }

    @PostMapping("/criteria/")
    public List<DeclarationIS> searchCriteria(@RequestBody DeclarationIsVo declarationIsVo) {
        return declarationISService.searchCriteria(declarationIsVo);
    }

    @GetMapping("/montantISCalcule/rf/{rf}")
    public double calculMontantIS(@PathVariable double rf) {
        return declarationISService.calculMontantIS(rf);
    }

    @PostMapping("/afficheDecIS/")
    public DeclarationIsObject afficheDecIS(@RequestBody DeclarationIsObject decIsOb) {
        return declarationISService.afficheDecIS(decIsOb);
    }

    @PutMapping("/")
    public int update(@RequestBody DeclarationIS declarationIS) {
        return declarationISService.update(declarationIS);
    }

    @PostMapping("/saveBrouillon/")
    public int saveBrouillon(@RequestBody DeclarationIS declarationIS) {
        return declarationISService.saveBrouillon(declarationIS);
    }

    @PostMapping("/validerBrouillon/")
    public void validerBrouillon(@RequestBody DeclarationIS declarationIS) {
        declarationISService.validerBrouillon(declarationIS);
    }

    @GetMapping("/findTauxIS/benefice/{benefice}")
    public Double findTauxIS(@PathVariable double benefice) {
        return declarationISService.findTauxIS(benefice);
    }

    @GetMapping("/")
    public List<DeclarationIS> findAll() {
        return declarationISService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody DeclarationIS declarationIS) {
        return declarationISService.save(declarationIS);
    }

    @Autowired
    DeclarationISService declarationISService;
}