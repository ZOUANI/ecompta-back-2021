package stage.sir.gestioncomptabilite.webService;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stage.sir.gestioncomptabilite.bean.DeclarationIR;
import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Employe;
import stage.sir.gestioncomptabilite.dao.DeclarationIREmployeDao;
import stage.sir.gestioncomptabilite.service.DeclarationIREmployeService;

@RestController
@RequestMapping("gestion-comptabilite/declarationiremploye")
public class DeclarationIREmployeProvided {
	@Autowired
	DeclarationIREmployeService declarationIREmployeService;
	
	
	
	@DeleteMapping("/DeclarationIR/ref/{ref}")
	public int deleteByDeclarationIRRef(@PathVariable String ref) {
		return declarationIREmployeService.deleteByDeclarationIRRef(ref);
	}

	/*
	
	@PostMapping("/updateMontantEmploye")
	public DeclarationIREmploye updateMontantEmploye(@RequestBody Employe employe) {
		return declarationIREmployeService.updateMontantEmploye(employe);
	}*/

	@GetMapping("/employe/cin/{cin}")
	public DeclarationIREmploye findByEmployeCin(@PathVariable String cin) {
		return (DeclarationIREmploye) declarationIREmployeService.findByEmployeCin(cin);
	}

	@GetMapping("/declarationir/ref/{ref}")
	public List<DeclarationIREmploye> findByDeclarationIRRef(@PathVariable String ref) {
		return declarationIREmployeService.findByDeclarationIRRef(ref);
	}
	

	@DeleteMapping("/employe/cin/{cin}")
	public int deleteByEmployeCin(@PathVariable String cin) {
		return declarationIREmployeService.deleteByEmployeCin(cin);
	}
	
	/*@PostMapping("/")
	public int save(@RequestBody DeclarationIR declarationIR) {
		return declarationIREmployeService.save(declarationIR);
	}*/
	
	
	
	
	
	

}
