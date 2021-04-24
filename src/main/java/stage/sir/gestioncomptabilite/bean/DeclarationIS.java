package stage.sir.gestioncomptabilite.bean;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class DeclarationIS {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Integer annee;
    private String ref;
    private Double totalHTGain;
    private Double totalHTCharge;
    private Double totalHTDiff; //resultatFiscal
    private Double montantISCalcule;
    private Double montantISPaye;
    @ManyToOne
    private Societe societe;
    @OneToOne
    private TauxIS tauxIS;
   /* @ManyToOne
    private TauxIsConfig tauxIsConfig; */

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getTotalHTGain() {
        return totalHTGain;
    }

    public void setTotalHTGain(Double totalHTGain) {
        this.totalHTGain = totalHTGain;
    }

    public Double getTotalHTCharge() {
        return totalHTCharge;
    }

    public void setTotalHTCharge(Double totalHTCharge) {
        this.totalHTCharge = totalHTCharge;
    }

    public Double getTotalHTDiff() {
        return totalHTDiff;
    }

    public void setTotalHTDiff(Double totalHTDiff) {
        this.totalHTDiff = totalHTDiff;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public Double getMontantISCalcule() {
        return montantISCalcule;
    }

    public void setMontantISCalcule(Double montantISCalcule) {
        this.montantISCalcule = montantISCalcule;
    }

    public Double getMontantISPaye() {
        return montantISPaye;
    }

    public void setMontantISPaye(Double montantISPaye) {
        this.montantISPaye = montantISPaye;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    public TauxIS getTauxIS() {
        return tauxIS;
    }

    public void setTauxIS(TauxIS tauxIS) {
        this.tauxIS = tauxIS;
    }


}