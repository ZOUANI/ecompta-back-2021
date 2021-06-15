package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class PaiementIS {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date datePaiement;
    private Double total;
    private Double montantCptTraiteur;
    private Double montantCptValidateur;
    private Double reste;
    @ManyToOne
    private DeclarationIS declarationIS;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
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

    public DeclarationIS getDeclarationIS() {
        return declarationIS;
    }

    public void setDeclarationIS(DeclarationIS declarationIS) {
        this.declarationIS = declarationIS;
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
}
