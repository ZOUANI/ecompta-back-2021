package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Paiement2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datePaiement;
    private Double total;
    private Double montantCptTraiteur;
    private Double montantCptValidateur;
    private Double reste;
    private double typeTva;
    @ManyToOne
    private DeclarationIS declarationIS;
    @ManyToOne
    private DeclarationTva declarationTva;
    @ManyToOne
    private DeclarationIR declarationIR;

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

    public Date getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(Date datePaiement) {
        this.datePaiement = datePaiement;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getMontantCptTraiteur() {
        return montantCptTraiteur;
    }

    public void setMontantCptTraiteur(Double montantCptTraiteur) {
        this.montantCptTraiteur = montantCptTraiteur;
    }

    public Double getMontantCptValidateur() {
        return montantCptValidateur;
    }

    public void setMontantCptValidateur(Double montantCptValidateur) {
        this.montantCptValidateur = montantCptValidateur;
    }

    public Double getReste() {
        return reste;
    }

    public void setReste(Double reste) {
        this.reste = reste;
    }

    public DeclarationIS getDeclarationIS() {
        return declarationIS;
    }

    public void setDeclarationIS(DeclarationIS declarationIS) {
        this.declarationIS = declarationIS;
    }

    public DeclarationTva getDeclarationTva() {
        return declarationTva;
    }

    public void setDeclarationTva(DeclarationTva declarationTva) {
        this.declarationTva = declarationTva;
    }

    public DeclarationIR getDeclarationIR() {
        return declarationIR;
    }

    public void setDeclarationIR(DeclarationIR declarationIR) {
        this.declarationIR = declarationIR;
    }

    public double getTypeTva() {
        return typeTva;
    }

    public void setTypeTva(double typeTva) {
        this.typeTva = typeTva;
    }
}
