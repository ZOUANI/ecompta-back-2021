package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class DeclarationIS {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double annee;
    private Double totalHTGain;
    private Double totalHTCharge;
    private Double totalHTDiff;
    private Double montantISCalcule;
    private Double montantISPaye;
    @ManyToOne
    private Societe societe;
    @OneToOne
    private TauxIS tauxIS;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "declarationIS")
    private List<Facture> facture = new ArrayList<Facture>();

    @ManyToOne
    private TauxIsConfig tauxIsConfig;


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

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
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

    public List<Facture> getFacture() {
        return facture;
    }

    public void setFacture(List<Facture> facture) {
        this.facture = facture;
    }

    public TauxIsConfig getTauxIsConfig() {
        return tauxIsConfig;
    }

    public void setTauxIsConfig(TauxIsConfig tauxIsConfig) {
        this.tauxIsConfig = tauxIsConfig;
    }
}