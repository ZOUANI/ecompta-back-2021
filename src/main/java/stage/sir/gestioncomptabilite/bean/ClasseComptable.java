package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class ClasseComptable {

    @Id  @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String libelle;
    private int numero;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "classeComptable")
    private List<SousClasseComptable> listeSousClasseComptables;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public List<SousClasseComptable> getListeSousClasseComptables() {
        return listeSousClasseComptables;
    }

    public void setListeSousClasseComptables(List<SousClasseComptable> listeSousClasseComptables) {
        this.listeSousClasseComptables = listeSousClasseComptables;
    }
}
