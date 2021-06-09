package stage.sir.gestioncomptabilite.vo;

import java.util.Date;

public class DemandeVo {

    private String ref;
    private String operation;
    private Date dateDemande;
    private Date dateDemandeMax;
    private Date dateDemandeMin;
    private String societe;
    private String comptableTraiteur;
    private String comptableValidateur;
    private String etatDemande;


    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Date getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(Date dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Date getDateDemandeMax() {
        return dateDemandeMax;
    }

    public void setDateDemandeMax(Date dateDemandeMax) {
        this.dateDemandeMax = dateDemandeMax;
    }

    public Date getDateDemandeMin() {
        return dateDemandeMin;
    }

    public void setDateDemandeMin(Date dateDemandeMin) {
        this.dateDemandeMin = dateDemandeMin;
    }

    public String getSociete() {
        return societe;
    }

    public void setSociete(String societe) {
        this.societe = societe;
    }

    public String getComptableTraiteur() {
        return comptableTraiteur;
    }

    public void setComptableTraiteur(String comptableTraiteur) {
        this.comptableTraiteur = comptableTraiteur;
    }

    public String getComptableValidateur() {
        return comptableValidateur;
    }

    public void setComptableValidateur(String comptableValidateur) {
        this.comptableValidateur = comptableValidateur;
    }

    public String getEtatDemande() { return etatDemande; }

    public void setEtatDemande(String etatDemande) { this.etatDemande = etatDemande; }
}