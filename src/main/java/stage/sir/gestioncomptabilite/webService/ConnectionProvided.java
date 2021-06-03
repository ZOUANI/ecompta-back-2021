package stage.sir.gestioncomptabilite.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import stage.sir.gestioncomptabilite.bean.Connection;
import stage.sir.gestioncomptabilite.service.ConnectionService;

import java.util.List;

@RestController
@RequestMapping("gestion-comptabilite/connection")
public class ConnectionProvided {
    @Autowired
    private ConnectionService connectionService;
    @GetMapping("/")
    public List<Connection> findAll() {
        return connectionService.findAll();
    }
    @PostMapping("/")
    public int save(@RequestBody Connection connection) {
        return connectionService.save(connection);
    }
    @GetMapping("/usernam/{username}/password/{password}")
    public Connection findByUsernameAndPassword(@PathVariable String username,@PathVariable String password) {
        return connectionService.findByUsernameAndPassword(username, password);
    }

    @GetMapping("/username/{username}/password/{password}")
    public int loger(@PathVariable String username,@PathVariable String password) {
        return connectionService.loger(username, password);
    }
    @GetMapping("/username/{username}/password/{password}/ref/{ref}")
    public int associer(@PathVariable String username,@PathVariable String password,@PathVariable String ref) {
        return connectionService.associer(username, password, ref);
    }
    @PutMapping("/ref/{ref}")
    public int update(@RequestBody Connection connection,@PathVariable String ref) {
        return connectionService.update(connection, ref);
    }
    @GetMapping("/usernome/{username}/password/{password}")
    public int etatLogin(@PathVariable String username,@PathVariable String password) {
        return connectionService.etatLogin(username, password);
    }
}
