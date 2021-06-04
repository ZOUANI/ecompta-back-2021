package stage.sir.gestioncomptabilite.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Acomptes {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer numero;
    private double anneePaye;
    private Double montant;

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

    public double getAnneePaye() {
        return anneePaye;
    }

    public void setAnneePaye(double anneePaye) {
        this.anneePaye = anneePaye;
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }
}
