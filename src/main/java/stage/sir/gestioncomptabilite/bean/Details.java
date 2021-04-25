package stage.sir.gestioncomptabilite.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Details {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double valeur;
	private Double pourcentage;
	private Double montantTrancheRevenu;
	@ManyToOne
	TauxIr tauxIr;
	@ManyToOne
	DeclarationIREmploye declarationIREmploye;
	
	
	
	
	
	@Override
	public String toString() {
		return "Details [id=" + id + ", valeur=" + valeur + ", pourcentage=" + pourcentage + ", montantTrancheRevenu="
				+ montantTrancheRevenu + ", tauxIr=" + tauxIr + ", declarationIREmploye=" + declarationIREmploye + "]";
	}
	public TauxIr getTauxIr() {
		return tauxIr;
	}
	public void setTauxIr(TauxIr tauxIr) {
		this.tauxIr = tauxIr;
	}
	
	public DeclarationIREmploye getDeclarationIREmploye() {
		return declarationIREmploye;
	}
	public void setDeclarationIREmploye(DeclarationIREmploye declarationIREmploye) {
		this.declarationIREmploye = declarationIREmploye;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Double getValeur() {
		return valeur;
	}
	public void setValeur(Double valeur) {
		this.valeur = valeur;
	}
	public Double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}
	public Double getMontantTrancheRevenu() {
		return montantTrancheRevenu;
	}
	public void setMontantTrancheRevenu(Double montantTrancheRevenu) {
		this.montantTrancheRevenu = montantTrancheRevenu;
	}

	
	
	
	
	
}
