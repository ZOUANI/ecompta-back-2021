package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stage.sir.gestioncomptabilite.bean.TauxIS;
import stage.sir.gestioncomptabilite.service.TauxISService;
import java.util.List;

@RestController
@RequestMapping(value = "Gestion-Comptabilité/tauxIS")
public class TauxISWS {

    @Autowired
    TauxISService tauxISService;

    @GetMapping("/")
    public List<TauxIS> findAll() {
        return tauxISService.findAll();
    }

    @PostMapping("/")
    public void save(TauxIS tauxIS) {
        tauxISService.save(tauxIS);
    }
}
