package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
@Entity
public class ClassComptable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ref;
    private String libelle;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "classComptable")
    private List<Section> listeSections;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public List<Section> getListeSections() {
        return listeSections;
    }

    public void setListeSections(List<Section> listeSections) {
        this.listeSections = listeSections;
    }
}
