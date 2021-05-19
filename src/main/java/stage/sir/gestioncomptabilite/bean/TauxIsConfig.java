package stage.sir.gestioncomptabilite.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TauxIsConfig {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private Double cotisationMinimale;
    private Integer anneeMin;
    private Integer anneeMax;


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

    public Double getCotisationMinimale() {
        return cotisationMinimale;
    }

    public void setCotisationMinimale(Double cotisationMinimale) {
        this.cotisationMinimale = cotisationMinimale;
    }

    public Integer getAnneeMin() { return anneeMin; }

    public void setAnneeMin(Integer anneeMin) {
        this.anneeMin = anneeMin;
    }

    public Integer getAnneeMax() {
        return anneeMax;
    }

    public void setAnneeMax(Integer anneeMax) {
        this.anneeMax = anneeMax;
    }
}
