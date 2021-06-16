package stage.sir.gestioncomptabilite.bean;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity @XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DeclarationIS {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private double annee;
    private Double totalHTGain;
    private Double totalHTCharge;
    private Double totalHTDiff;
    private Double montantISCalcule;
    private Double montantISPaye;
    private Double totalPaye;
    @ManyToOne
    private Societe societe;
    @ManyToOne
    private TauxIS tauxIS;
    @ManyToOne
    private TauxIsConfig tauxIsConfig;
    @ManyToOne
    private EtatDeclaration etatDeclaration;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getTotalPaye() { return totalPaye; }

    public void setTotalPaye(Double totalPaye) { this.totalPaye = totalPaye; }

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

    public TauxIsConfig getTauxIsConfig() {
        return tauxIsConfig;
    }

    public void setTauxIsConfig(TauxIsConfig tauxIsConfig) {
        this.tauxIsConfig = tauxIsConfig;
    }

    public String getRef() { return ref; }

    public void setRef(String ref) { this.ref = ref; }

    public EtatDeclaration getEtatDeclaration() { return etatDeclaration; }

    public void setEtatDeclaration(EtatDeclaration etatDeclaration) { this.etatDeclaration = etatDeclaration; }

    @Override
    public String toString() {
        return "DeclarationIS{" +
                "id=" + id +
                ", ref='" + ref + '\'' +
                ", annee=" + annee +
                ", totalHTGain=" + totalHTGain +
                ", totalHTCharge=" + totalHTCharge +
                ", totalHTDiff=" + totalHTDiff +
                ", montantISCalcule=" + montantISCalcule +
                ", montantISPaye=" + montantISPaye +
                ", societe=" + societe +
                ", tauxIS=" + tauxIS +
                ", tauxIsConfig=" + tauxIsConfig +
                ", etatDeclaration=" + etatDeclaration +
                '}';
    }
}