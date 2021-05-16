package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.DeclarationIS;
import stage.sir.gestioncomptabilite.service.DeclarationISService;
import stage.sir.gestioncomptabilite.vo.DeclarationIsObject;
import stage.sir.gestioncomptabilite.vo.DeclarationIsVo;
import stage.sir.gestioncomptabilite.vo.DeclarationIsXml;

import java.util.List;

@RestController
@RequestMapping(value = "gestion-comptabilite/declarationIS")
public class DeclarationIsProvided {


    @GetMapping("/ref/{ref}")
    public DeclarationIS findByRef(@PathVariable String ref) {
        return declarationISService.findByRef(ref);
    }

    @DeleteMapping("/annee/{annee}")
    public int deleteByAnnee(@PathVariable double annee) {
        return declarationISService.deleteByAnnee(annee);
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

    @GetMapping("/afficheDecIS/ice/{ice}/annee/{annee}")
    public DeclarationIsObject afficheObject(@PathVariable String ice, @PathVariable double annee) {
        return declarationISService.afficheObject(ice, annee);
    }

    @PostMapping("/validerBrouillon/")
    public int validerBrouillon(@RequestBody DeclarationIS declarationIS) {
        return declarationISService.validerBrouillon(declarationIS);
    }

    @GetMapping("/findTauxIS/benefice/{benefice}")
    public Double findTauxIS(@PathVariable double benefice) {
        return declarationISService.findTauxIS(benefice);
    }

    @PutMapping("/")
    public int update(@RequestBody DeclarationIS declarationIS) {
        return declarationISService.update(declarationIS);
    }

    @GetMapping("/")
    public List<DeclarationIS> findAll() {
        return declarationISService.findAll();
    }

    @PostMapping("/toXML/")
    public void declarationIsToXML(@RequestBody DeclarationIS declarationIS) {
        declarationISService.declarationIsToXML(declarationIS);
    }

    @GetMapping("/xmlToDec/fileName/{fileName}")
    public DeclarationIsXml XmlToDecIS(@PathVariable String fileName) {
        return declarationISService.XmlToDecIS(fileName);
    }

    @GetMapping("/ice/{ice}/annee/{annee}/etat/{etat}")
    public int save22(@PathVariable String ice, @PathVariable double annee, @PathVariable String etat) {
        return declarationISService.save22(ice, annee, etat);
    }

    @Autowired
    DeclarationISService declarationISService;
}