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

import stage.sir.gestioncomptabilite.bean.Login;
import stage.sir.gestioncomptabilite.service.LoginService;

@RestController
@RequestMapping("gestion-comptabilite/login")
public class LoginProvided {
	@Autowired
	LoginService loginService;
	@PostMapping("/loginUser")
	public Login loginUser(@RequestBody Login login) throws Exception {
		return loginService.loginUser(login);
	}
	@GetMapping("/usename/{usename}")
	public Login findByUsername(@PathVariable String usename) {
		return loginService.findByUsername(usename);
	}
	@GetMapping("/type/{type}")
	public Login findByType(@PathVariable String type) {
		return loginService.findByType(type);
	}
	@DeleteMapping("/type/{type}")
	public int deleteByType(@PathVariable String type) {
		return loginService.deleteByType(type);
	}
	@GetMapping("/username/{username}/password/{password}")
	public Login findByUsernameAndPassword(@PathVariable String username,@PathVariable String password) {
		return loginService.findByUsernameAndPassword(username, password);
	}
	@PostMapping("/")
	public int save(@RequestBody Login login) {
		return loginService.save(login);
	}


	@GetMapping("/")
	public List<Login> findAll() {
		return loginService.findAll();
	}
	

}
