package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
@Entity
public class Section {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private  long id;
  private  String ref;
  private  String libelle;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
@OneToMany(mappedBy = "section")
  private List<Categorie> listeCategories;
  @ManyToOne
  private ClassComptable classComptable;

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

    public List<Categorie> getListeCategories() {
        return listeCategories;
    }

    public void setListeCategories(List<Categorie> listeCategories) {
        this.listeCategories = listeCategories;
    }

    public ClassComptable getClassComptable() {
        return classComptable;
    }

    public void setClassComptable(ClassComptable classComptable) {
        this.classComptable = classComptable;
    }
}
