package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class TauxIS {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Double resultatFiscalMin;
    private Double resultatFiscalMax;
    private Double pourcentage;
    //Résultat fiscal  = Résultat comptable + les charges non déductible - les produits non imposable .
    //Résultat comptable = les produits - les charges

    @ManyToOne
    Societe societe;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getResultatFiscalMin() {
        return resultatFiscalMin;
    }

    public void setResultatFiscalMin(Double resultatFiscalMin) {
        this.resultatFiscalMin = resultatFiscalMin;
    }

    public Double getResultatFiscalMax() {
        return resultatFiscalMax;
    }

    public void setResultatFiscalMax(Double resultatFiscalMax) {
        this.resultatFiscalMax = resultatFiscalMax;
    }

    public Double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(Double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
}
