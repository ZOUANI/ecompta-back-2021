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
    
}
