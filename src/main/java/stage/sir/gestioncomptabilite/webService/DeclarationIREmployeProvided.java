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

import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.dao.DeclarationIREmployeDao;
import stage.sir.gestioncomptabilite.service.DeclarationIREmployeService;

@RestController
@RequestMapping("gestion-comptabilite/declarationiremploye")
public class DeclarationIREmployeProvided {
	@Autowired
	DeclarationIREmployeService declarationIREmployeService;
	@GetMapping("employe/cin/{cin}")
	public List<DeclarationIREmploye> findByEmployeCin(@PathVariable String cin) {
		return declarationIREmployeService.findByEmployeCin(cin);
	}
	@GetMapping("/refemp/{refEmp}")
	public DeclarationIREmploye findByRefEmp(@PathVariable String refEmp) {
		return declarationIREmployeService.findByRefEmp(refEmp);
	}
	@DeleteMapping("/employe/cin/{cin}")
	public int deleteByEmployeCin(@PathVariable String cin) {
		return declarationIREmployeService.deleteByEmployeCin(cin);
	}
	@PostMapping("/")
	public int save(@RequestBody DeclarationIREmploye declarationIREmploye) {
		return declarationIREmployeService.save(declarationIREmploye);
	}
	
	
	
	
	
	

}
