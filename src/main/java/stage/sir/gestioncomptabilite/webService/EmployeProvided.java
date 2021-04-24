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

import stage.sir.gestioncomptabilite.bean.Employe;
import stage.sir.gestioncomptabilite.service.EmployeService;
@RestController
@RequestMapping("gestion-comptabilite/employe")
public class EmployeProvided {
	@Autowired
	EmployeService employeService;

	
	@GetMapping("/societe/ice/{ice}")
	public List<Employe> findBySocieteEmpIce(@PathVariable String ice) {
		return employeService.findBySocieteEmpIce(ice);
	}
	@GetMapping("/cin/{cin}")
	public Employe findByCin(@PathVariable String cin) {
		return employeService.findByCin(cin);
	}
	@DeleteMapping("/cin/{cin}")
	public int deleteByCin(@PathVariable String cin) {
		return employeService.deleteByCin(cin);
	}
	@PostMapping("/")
	public int save(@RequestBody Employe employe) {
		return employeService.save(employe);
	}
	@GetMapping("/")
	public List<Employe> findAll() {
		return employeService.findAll();
	}

}
