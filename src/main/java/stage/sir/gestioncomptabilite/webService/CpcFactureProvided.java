package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stage.sir.gestioncomptabilite.bean.CpcFacture;
import stage.sir.gestioncomptabilite.service.CpcFactureService;

@RestController
@RequestMapping("gestion-categorie/cpc-facture")
public class CpcFactureProvided {
    @Autowired
    private CpcFactureService cpcFactureService;
    @PutMapping("/")
    public int update(@RequestBody CpcFacture cpcFacture) {
        return cpcFactureService.update(cpcFacture);
    }
}
