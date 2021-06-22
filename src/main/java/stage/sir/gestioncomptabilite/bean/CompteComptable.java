package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;

@Entity
public class CompteComptable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String libelle;
    private String code;
    @ManyToOne
    private SousClasseComptable sousClasseComptable;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SousClasseComptable getSousClasseComptable() {
        return sousClasseComptable;
    }

    public void setSousClasseComptable(SousClasseComptable sousClasseComptable) {
        this.sousClasseComptable = sousClasseComptable;
    }
}
