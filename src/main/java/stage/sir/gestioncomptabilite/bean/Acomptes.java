package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;

@Entity
public class Acomptes {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numero;
    private double annee;
    private Double montant;
    @ManyToOne
    private Societe societe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public Societe getSociete() { return societe; }

    public void setSociete(Societe societe) { this.societe = societe; }
}
