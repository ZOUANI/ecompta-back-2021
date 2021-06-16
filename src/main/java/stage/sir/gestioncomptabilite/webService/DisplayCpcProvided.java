package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Cpc;
import stage.sir.gestioncomptabilite.bean.Facture;
import stage.sir.gestioncomptabilite.service.CpcService;
import stage.sir.gestioncomptabilite.service.DisplayCpcService;
import stage.sir.gestioncomptabilite.service.TauxISService;
import stage.sir.gestioncomptabilite.vo.CalCpcVo;
import stage.sir.gestioncomptabilite.vo.CpcVo;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/display-cpc")
public class DisplayCpcProvided {


    @Autowired
    private DisplayCpcService displayCpcService;
    @Autowired
    private TauxISService tauxISService;

    @PostMapping("/find")
    public List<Cpc> findCpc(@RequestBody CpcVo cpcVo) {
        return cpcService.findCpc(cpcVo);
    }
    @GetMapping("/")
    public List<Cpc> findAll() {
        return cpcService.findAll();
    }

    @Autowired
    private CpcService cpcService;

    @GetMapping("/resultat/{resultat}")
    public Double Pourcentage(@PathVariable Double resultat) {
        return tauxISService.findTauxIs(resultat);
    }

    @PostMapping("/type/{type}/")
    public List<Facture> bringFact(@PathVariable String type,@RequestBody CalCpcVo calCpcVo) {
        return displayCpcService.bringFact(type, calCpcVo);
    }
    @PostMapping("/operation/{operation}/")
    public Double calculEx(@RequestBody CalCpcVo calCpcVo,@PathVariable String operation) {
        return displayCpcService.calculEx(calCpcVo, operation);
    }
    @PostMapping("/")
    public int save(@RequestBody Cpc cpc) {
        return cpcService.save(cpc);
    }
    @PutMapping("/update/")
    public int update(@RequestBody Cpc cpc) {
        return cpcService.save(cpc);
    }

    @DeleteMapping("/delete/id/{id}")
    public int delete(@PathVariable Long id) {
        if (id == null){
            return -1;
        }
        return cpcService.delete(id);
    }
}
