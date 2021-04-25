package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.DeclarationTva;
import stage.sir.gestioncomptabilite.service.DeclarationTvaService;
import stage.sir.gestioncomptabilite.vo.DeclarationTvaVo1;
import stage.sir.gestioncomptabilite.vo.DeclarationTvaVo2;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/declarationtva")
public class DeclarationTvaProvided {

    @GetMapping("ref/{ref}")
    public DeclarationTva findByRef(@PathVariable String ref) {
        return declarationTvaService.findByRef(ref);
    }

    @DeleteMapping("ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return declarationTvaService.deleteByRef(ref);
    }


    @GetMapping("societe/ice/{ice}")
    public List<DeclarationTva> findBySocieteIce(@PathVariable String ice) {
        return declarationTvaService.findBySocieteIce(ice);
    }

    @DeleteMapping("societe/ice/{ice}")
    public int deleteBySocieteIce(@PathVariable String ice) {
        return declarationTvaService.deleteBySocieteIce(ice);
    }
    @GetMapping("typeDeclarationTva/ref/{ref}")
    public List<DeclarationTva> findByTypeDeclarationTvaRef(@PathVariable String ref) {
        return declarationTvaService.findByTypeDeclarationTvaRef(ref);
    }

    @DeleteMapping("typeDeclarationTva/ref/{ref}")
    public int deleteByTypeDeclarationTvaRef(@PathVariable String ref) {
        return declarationTvaService.deleteByTypeDeclarationTvaRef(ref);
    }

    @GetMapping("/")
    public List<DeclarationTva> findAll() {
        return declarationTvaService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody DeclarationTva declarationTva) {
        return declarationTvaService.save(declarationTva);
    }

    @GetMapping("annee/{annee}/mois/{mois}")
    public List<DeclarationTva> findByAnneeAndMois(double annee, double mois) {
        return declarationTvaService.findByAnneeAndMois(annee, mois);
    }
    @GetMapping("annee/{annee}/trim/{trim}/m2")
    public List<DeclarationTva> findByAnneeAndTrim(double annee, double trim) {
        return declarationTvaService.findByAnneeAndTrim(annee, trim);
    }
    @PostMapping("/findfacturesandcalcultva")
    public DeclarationTvaVo2 findfacturesandcalcultva(@RequestBody DeclarationTvaVo1 declarationTvaVo1) {
        return declarationTvaService.findfacturesandcalcultva(declarationTvaVo1);
    }

    @Autowired
    DeclarationTvaService declarationTvaService;
}
