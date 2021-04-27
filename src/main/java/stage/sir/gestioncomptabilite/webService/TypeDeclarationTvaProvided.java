package stage.sir.gestioncomptabilite.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.TypeDeclarationTva;
import stage.sir.gestioncomptabilite.service.TypeDeclarationTvaService;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/typedeclarationtva")
public class TypeDeclarationTvaProvided {

    @GetMapping("ref/{ref}")
    public TypeDeclarationTva findByRef(@PathVariable String ref) {
        return typeDeclarationTvaService.findByRef(ref);
    }

    @GetMapping("libelle/{libelle}")
    public TypeDeclarationTva findByLibelle(@PathVariable String libelle) {
        return typeDeclarationTvaService.findByLibelle(libelle);
    }
    @DeleteMapping("ref/{ref}")
    public int deleteByRef(@PathVariable String ref) {
        return typeDeclarationTvaService.deleteByRef(ref);
    }
    @GetMapping("/")
    public List<TypeDeclarationTva> findAll() {
        return typeDeclarationTvaService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody TypeDeclarationTva typeDeclarationTva) {
        return typeDeclarationTvaService.save(typeDeclarationTva);
    }

    @Autowired
    TypeDeclarationTvaService typeDeclarationTvaService;
}
