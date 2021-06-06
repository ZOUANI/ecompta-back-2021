package stage.sir.gestioncomptabilite.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Employe {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cin;
	private String nom;
	private String prenom;
	private Double salaire;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
	@OneToMany(mappedBy = "employe")
	private List<DeclarationIREmploye> declarationIREmployes;

	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToOne
	Societe societeEmp;
	
	
	
	@Override
	public String toString() {
		return "Employe [id=" + id + ", cin=" + cin + ", nom=" + nom + ", prenom=" + prenom + ", salaire=" + salaire
				+ ", societeEmp=" + societeEmp + ", declarationIREmployes=" + declarationIREmployes + "]";
	}
	public Societe getSocieteEmp() {
		return societeEmp;
	}
	public void setSocieteEmp(Societe societeEmp) {
		this.societeEmp = societeEmp;
	}
	public List<DeclarationIREmploye> getDeclarationIREmployes() {
		return declarationIREmployes;
	}
	public void setDeclarationIREmployes(List<DeclarationIREmploye> declarationIREmployes) {
		this.declarationIREmployes = declarationIREmployes;
	}




	public Double getSalaire() {
		return salaire;
	}
	public void setSalaire(Double salaire) {
		this.salaire = salaire;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}
