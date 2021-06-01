package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;

@Entity

public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String type;
    private String etat;
    @OneToOne
    private Societe societeLogin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Societe getSocieteLogin() {
        return societeLogin;
    }

    public void setSocieteLogin(Societe societeLogin) {
        this.societeLogin = societeLogin;
    }
}
