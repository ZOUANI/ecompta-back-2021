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

    private String annee;
    private String moisMin;
    private String moisMax;
    private String comptableTraiteurCode;
    private String comptableValidateurCode;
    
    
    
    
    

    private String etatDemande;



	public String getComptableValidateurCode() {
		return comptableValidateurCode;
	}

	public void setComptableValidateurCode(String comptableValidateurCode) {
		this.comptableValidateurCode = comptableValidateurCode;
	}

	public String getComptableTraiteurCode() {
		return comptableTraiteurCode;
	}

	public void setComptableTraiteurCode(String comptableTraiteurCode) {
		this.comptableTraiteurCode = comptableTraiteurCode;
	}

	public String getAnnee() {
		return annee;
	}

	public void setAnnee(String annee) {
		this.annee = annee;
	}

	public String getMoisMin() {
		return moisMin;
	}

	public void setMoisMin(String moisMin) {
		this.moisMin = moisMin;
	}

	public String getMoisMax() {
		return moisMax;
	}

	public void setMoisMax(String moisMax) {
		this.moisMax = moisMax;
	}

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
