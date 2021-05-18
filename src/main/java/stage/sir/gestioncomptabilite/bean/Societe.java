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
    private Long id;
    private String ice; //Identifiant Commun de l'Entreprise
    private String adresse;
    private String raisonSociale;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateCreation;
    private int anneeExploitation;
    private Double age;
    @ManyToOne
    private Comptable comptable;
    @ManyToOne
    private EtatSociete etatSociete;

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


    public Long getId() {

        return id;
    }

    public void setId(Long id) {
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

    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public EtatSociete getEtatSociete() {
        return etatSociete;
    }

    public void setEtatSociete(EtatSociete etatSociete) {
        this.etatSociete = etatSociete;
    }
}