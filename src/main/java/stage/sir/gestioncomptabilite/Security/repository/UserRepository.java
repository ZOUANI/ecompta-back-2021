package stage.sir.gestioncomptabilite.Security.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import stage.sir.gestioncomptabilite.Security.models.ERole;
import stage.sir.gestioncomptabilite.Security.models.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
	
	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
	//List<User> findByRolesIn(List<String> roles);
	//List<User> findByRolesIn(Collection<String> names, Pageable pageable);

	//List<User> findByRolesIn(List<ERole> asList, Pageable pageable);
	
	/*@Query("SELECT u FROM User u WHERE u.id IN (SELECT ur.userId FROM UserRole ur WHERE ur.name = :role)")
	List<User> getAdminList(@Param("role") String userRole);*/
	//@Query("SELECT u FROM User u WHERE u.id IN (SELECT ur.id FROM Role ur WHERE ur.name = :role)")
	
	//List<User> getAdminList(ERole role);
}
