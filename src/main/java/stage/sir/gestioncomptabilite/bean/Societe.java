package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
public class Societe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ice; //Identifiant Commun de l'Entreprise
    private String adresse;
    private String raisonSociale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreation;
    private int anneeExploitation;
    private double age;
    @ManyToOne
    private Comptable comptable;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "societe")
    private List<DeclarationIR> declarationIRs;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "societeEmp")
    private List<Employe> employes;


    @Override
    public String toString() {
        return "Societe [id=" + id + ", ice=" + ice + ", adresse=" + adresse + ", raisonSociale=" + raisonSociale
                + ", anneeExploitation=" + anneeExploitation + ", age=" + age + ", declarationIRs=" + declarationIRs
                + ", employes=" + employes + "]";
    }


    public List<Employe> getEmployes() {
        return employes;
    }


    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIce() {
        return ice;
    }

    public void setIce(String ice) {
        this.ice = ice;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getRaisonSociale() {
        return raisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        this.raisonSociale = raisonSociale;
    }

    public int getAnneeExploitation() {
        return anneeExploitation;
    }

    public void setAnneeExploitation(int anneeExploitation) {
        this.anneeExploitation = anneeExploitation;

    }

    public Comptable getComptable() {
        return comptable;
    }

    public void setComptable(Comptable comptable) {
        this.comptable = comptable;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}