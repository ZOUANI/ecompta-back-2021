package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;

@Entity
public class Etape {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String libelle;
    private String description;
    private double MontantFix ;
    private double MontantComptable;
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

    public double getMontantFix() {
        return MontantFix;
    }

    public void setMontantFix(double montantFix) {
        MontantFix = montantFix;
    }

    public double getMontantComptable() {
        return MontantComptable;
    }

    public void setMontantComptable(double montantComptable) {
        MontantComptable = montantComptable;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }
}
