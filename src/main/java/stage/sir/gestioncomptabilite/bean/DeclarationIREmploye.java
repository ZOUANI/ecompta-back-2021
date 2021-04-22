package stage.sir.gestioncomptabilite.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class DeclarationIREmploye {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String refEmp;
	private Double salaireNet;
	private Double salaireBrut;
	private Double montantIR;
	@OneToMany(mappedBy = "declarationIREmploye")
	List<Details> detailsEmploye;
	
	
	@ManyToOne
	private Employe employe;
	
	@ManyToOne
	private DeclarationIR declarationIR;
	

	
	
	public List<Details> getDetailsEmploye() {
		return detailsEmploye;
	}

	public void setDetailsEmploye(List<Details> detailsEmploye) {
		this.detailsEmploye = detailsEmploye;
	}

	public String getRefEmp() {
		return refEmp;
	}

	public void setRefEmp(String refEmp) {
		this.refEmp = refEmp;
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
