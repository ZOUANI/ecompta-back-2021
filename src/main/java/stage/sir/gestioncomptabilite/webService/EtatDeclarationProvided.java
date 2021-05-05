package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.EtatDeclaration;
import stage.sir.gestioncomptabilite.service.EtatDeclarationService;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/etatDeclaration")
public class EtatDeclarationProvided {

    @GetMapping("/ref/{ref}")
    public EtatDeclaration findByRef(@PathVariable String ref) {
        return etatDeclarationService.findByRef(ref);
    }

    @DeleteMapping("/")
    public int deleteByRef(@PathVariable String ref) {
        return etatDeclarationService.deleteByRef(ref);
    }

    @GetMapping("libelle/{libelle}")
    public EtatDeclaration findByLibelle(@PathVariable String libelle) { return etatDeclarationService.findByLibelle(libelle); }

    @GetMapping("/")
    public List<EtatDeclaration> findAll() { return etatDeclarationService.findAll(); }

    @PostMapping("/")
    public int save(@RequestBody EtatDeclaration etatDeclaration) {
        return etatDeclarationService.save(etatDeclaration);
    }

    @Autowired
    EtatDeclarationService etatDeclarationService;
}
