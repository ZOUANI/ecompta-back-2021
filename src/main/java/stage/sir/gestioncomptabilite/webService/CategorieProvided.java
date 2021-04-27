package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Categorie;
import stage.sir.gestioncomptabilite.service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/categorie")
public class CategorieProvided {
    @Autowired
    private CategorieService categorieService;

    @GetMapping("/Section/ref/{ref}")
    public List<Categorie> findBySectionRef(@PathVariable String ref) {
        return categorieService.findBySectionRef(ref);
    }
    @GetMapping("/")
    public List<Categorie> findAll() {
        return categorieService.findAll();
    }

    @PostMapping("/")
    public int save(@RequestBody Categorie categorie) {
        return categorieService.save(categorie);
    }
}
