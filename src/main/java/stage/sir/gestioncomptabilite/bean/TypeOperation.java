package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class TypeOperation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private String description;
    private double fraixfixtotal ;
    private double fraixcomptabletotal ;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "typeOperation")

    private List<Etape> etapes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public double getFraixfixtotal() {
        return fraixfixtotal;
    }

    public void setFraixfixtotal(double fraixfixtotal) {
        this.fraixfixtotal = fraixfixtotal;
    }

    public double getFraixcomptabletotal() {
        return fraixcomptabletotal;
    }

    public void setFraixcomptabletotal(double fraixcomptabletotal) {
        this.fraixcomptabletotal = fraixcomptabletotal;
    }

    public List<Etape> getEtapes() {
        return etapes;
    }

    public void setEtapes(List<Etape> etapes) {
        this.etapes = etapes;
    }
}
