package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
@Entity
public class OperationSocieteJustif {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private String libelle;
    private String description;
    private String chemin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateJustif;
    @ManyToOne
    private OperationSociete operationSociete;

    public OperationSociete getOperationSociete() {
        return operationSociete;
    }

    public void setOperationSociete(OperationSociete operationSociete) {
        this.operationSociete = operationSociete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChemin() {
        return chemin;
    }

    public void setChemin(String chemin) {
        this.chemin = chemin;
    }

    public Date getDateJustif() {
        return dateJustif;
    }

    public void setDateJustif(Date dateJustif) {
        this.dateJustif = dateJustif;
    }
}
