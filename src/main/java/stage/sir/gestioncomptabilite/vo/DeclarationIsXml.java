package stage.sir.gestioncomptabilite.vo;

import stage.sir.gestioncomptabilite.bean.*;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "DeclarationIS")
public class DeclarationIsXml {
    private Long id;
    private String ref;
    private double annee;
    private Double totalHTGain;
    private Double totalHTCharge;
    private Double totalHTDiff;
    private Double montantISCalcule;
    private Double montantISPaye;
    private SocieteXml societeXml;
    private TauxIS tauxIS;
    private TauxIsConfig tauxIsConfig;
    private List<FactureXml> factures;

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

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
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

    public SocieteXml getSocieteXml() {
        return societeXml;
    }

    public void setSocieteXml(SocieteXml societeXml) {
        this.societeXml = societeXml;
    }

    public TauxIS getTauxIS() {
        return tauxIS;
    }

    public void setTauxIS(TauxIS tauxIS) {
        this.tauxIS = tauxIS;
    }

    public TauxIsConfig getTauxIsConfig() {
        return tauxIsConfig;
    }

    public void setTauxIsConfig(TauxIsConfig tauxIsConfig) {
        this.tauxIsConfig = tauxIsConfig;
    }

    public List<FactureXml> getFactures() {
        return factures;
    }

    public void setFactures(List<FactureXml> factures) {
        this.factures = factures;
    }

    @Override
    public String toString() {
        return "DeclarationIsXml{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", annee=" + annee +
                ", totalHTGain=" + totalHTGain +
                ", totalHTCharge=" + totalHTCharge +
                ", totalHTDiff=" + totalHTDiff +
                ", montantISCalcule=" + montantISCalcule +
                ", montantISPaye=" + montantISPaye +
                ", societeXml=" + societeXml +
                ", tauxIS=" + tauxIS +
                ", tauxIsConfig=" + tauxIsConfig +
                ", factures=" + factures +
                '}';
    }
}
