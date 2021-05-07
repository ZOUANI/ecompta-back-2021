package stage.sir.gestioncomptabilite.vo;

import javax.xml.bind.annotation.XmlElement;



public class RefF {
    private double iff;
    private String nom;
    private String ice;

    @XmlElement(name = "if")
    public double getIff() {
        return iff;
    }

    public void setIff(double iff) {
        this.iff = iff;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }
}
