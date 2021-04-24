package stage.sir.gestioncomptabilite.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stage.sir.gestioncomptabilite.bean.Categorie;
import stage.sir.gestioncomptabilite.service.CategorieService;

import java.util.List;

@RestController
@RequestMapping("gestion-categorie/categorie")
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
}
