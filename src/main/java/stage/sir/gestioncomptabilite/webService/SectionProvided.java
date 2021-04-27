package stage.sir.gestioncomptabilite.webService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stage.sir.gestioncomptabilite.bean.Section;
import stage.sir.gestioncomptabilite.service.SectionService;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/section")
public class SectionProvided {
    @Autowired
    private SectionService sectionService;
    @GetMapping("/ClassComptable/ref/{ref}")
    public List<Section> findByClassComptableRef(@PathVariable String ref) {
        return sectionService.findByClassComptableRef(ref);
    }
    @GetMapping("/")
    public List<Section> findAll() {
        return sectionService.findAll();
    }
}
