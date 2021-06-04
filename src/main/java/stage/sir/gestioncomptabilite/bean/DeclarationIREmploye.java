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
public class DeclarationIREmploye {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double salaireNet;
	private Double salaireBrut;
	private Double montantIR;
	/*
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
	@OneToMany(mappedBy = "declarationIREmploye")
	List<Details> detailsEmploye;*/
	@ManyToOne
	private TauxIr tauxIr;
	
	@ManyToOne
	private Employe employe;
	
	@ManyToOne
	private DeclarationIR declarationIR;
	

	
	
	
	/*
	@Override
	public String toString() {
		return "DeclarationIREmploye [id=" + id + ", refEmp=" + refEmp + ", salaireNet=" + salaireNet + ", salaireBrut="
				+ salaireBrut + ", montantIR=" + montantIR + ", detailsEmploye=" + detailsEmploye + ", employe="
				+ employe + ", declarationIR=" + declarationIR + "]";
	}*/
/*
	public List<Details> getDetailsEmploye() {
		return detailsEmploye;
	}

	public void setDetailsEmploye(List<Details> detailsEmploye) {
		this.detailsEmploye = detailsEmploye;
	}*/
	
	
	
	public TauxIr getTauxIr() {
		return tauxIr;
	}


	public void setTauxIr(TauxIr tauxIr) {
		this.tauxIr = tauxIr;
	}


	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSalaireNet() {
		return salaireNet;
	}

	public void setSalaireNet(Double salaireNet) {
		this.salaireNet = salaireNet;
	}

	public Double getSalaireBrut() {
		return salaireBrut;
	}

	public void setSalaireBrut(Double salaireBrut) {
		this.salaireBrut = salaireBrut;
	}

	public Double getMontantIR() {
		return montantIR;
	}

	public void setMontantIR(Double montantIR) {
		this.montantIR = montantIR;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public DeclarationIR getDeclarationIR() {
		return declarationIR;
	}

	public void setDeclarationIR(DeclarationIR declarationIR) {
		this.declarationIR = declarationIR;
	}
	
	
    
}
