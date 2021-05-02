package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
public class SousClasseComptable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private  long id;
    private int numero;
  private  String libelle;
    @ManyToOne
    private ClasseComptable classeComptable;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "sousClasseComptable")
  private List<CompteComptable> listeCompteComptable;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<CompteComptable> getListeCompteComptable() {
        return listeCompteComptable;
    }

    public void setListeCompteComptable(List<CompteComptable> listeCategories) {
        this.listeCompteComptable = listeCategories;
    }

    public ClasseComptable getClasseComptable() {
        return classeComptable;
    }

    public void setClasseComptable(ClasseComptable classeComptable) {
        this.classeComptable = classeComptable;
    }
}
