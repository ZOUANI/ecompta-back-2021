package stage.sir.gestioncomptabilite.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DeclarationReleveDeduction {
    private double identifiantFiscal;
    private double annee;
    private double periode;
    private double regime;
    private ReleveDeductions releveDeductions;

    public double getIdentifiantFiscal() {
        return identifiantFiscal;
    }

    public void setIdentifiantFiscal(double identifiantFiscal) {
        this.identifiantFiscal = identifiantFiscal;
    }

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
    }

    public double getPeriode() {
        return periode;
    }

    public void setPeriode(double periode) {
        this.periode = periode;
    }

    public double getRegime() {
        return regime;
    }

    public void setRegime(double regime) {
        this.regime = regime;
    }

    public ReleveDeductions getReleveDeductions() {
        return releveDeductions;
    }

    public void setReleveDeductions(ReleveDeductions releveDeductions) {
        this.releveDeductions = releveDeductions;
    }
}
