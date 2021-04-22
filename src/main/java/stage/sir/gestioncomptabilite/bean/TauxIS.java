package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;

@Entity
public class TauxIS {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ref;
    private Double resultatFiscalMin;
    private Double resultatFiscalMax;
    private Double pourcentage;


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
}
