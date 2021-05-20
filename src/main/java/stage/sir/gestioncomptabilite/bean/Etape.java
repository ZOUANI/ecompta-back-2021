package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;

@Entity
public class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private String description;
    private String delai;
    private double montantfix;
    private double montantcomptable;
    @ManyToOne
    private TypeOperation typeOperation;

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

    public String getDelai() {
        return delai;
    }

    public void setDelai(String delai) {
        this.delai = delai;
    }

    public double getMontantfix() {
        return montantfix;
    }

    public void setMontantfix(double montantfix) {
        this.montantfix = montantfix;
    }

    public double getMontantcomptable() {
        return montantcomptable;
    }

    public void setMontantcomptable(double montantcomptable) {
        this.montantcomptable = montantcomptable;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
}
