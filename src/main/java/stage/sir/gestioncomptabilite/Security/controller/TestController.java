package stage.sir.gestioncomptabilite.Security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stage.sir.gestioncomptabilite.Security.models.User;
import stage.sir.gestioncomptabilite.Security.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	UserRepository userRepository;
	
	
	@GetMapping("/username/{username}")
	public Optional<User> findByUsername(@PathVariable String username) {
		return userRepository.findByUsername(username);
	}

	/*@GetMapping("/role/{role}")
	public List<User> getAdminList(@PathVariable ERole role) {
		return userRepository.getAdminList(role);
	}*/
	//@PreAuthorize("hasRole('ADMIN') or hasRole('COMPTABLE')")
	@GetMapping("/")
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}
	
	/*@GetMapping("/usersByRole/{userRole}")
	public List<User> getUser(@PathVariable ERole userRole, Pageable pageable){
	    return userRepository.findByRolesIn(Arrays.asList(userRole), pageable);
	}*/
	/*
	@GetMapping("/userRole")
	public List<User> getAdminList() {
	    String roleName = "ROLE_SOCIETE";
	    return userRepository.getAdminList(roleName);
	}*/
	
	@GetMapping("/user")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	//@PreAuthorize("hasRole('MODERATOR')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
	//@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
