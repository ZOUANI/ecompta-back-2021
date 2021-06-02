package stage.sir.gestioncomptabilite.vo;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;

import stage.sir.gestioncomptabilite.bean.DeclarationIREmploye;
import stage.sir.gestioncomptabilite.bean.Societe;

@XmlRootElement(name = "DeclarationIR")
public class DeclarationIRXML {
	private Long id;
	private String ref;
	private Integer mois;
	private Integer annee;
	private Double total;
	private Societe societe;
	private List<DeclarationIREmploye> declarationsIREmployes;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public Integer getMois() {
		return mois;
	}
	public void setMois(Integer mois) {
		this.mois = mois;
	}
	public Integer getAnnee() {
		return annee;
	}
	public void setAnnee(Integer annee) {
		this.annee = annee;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	public List<DeclarationIREmploye> getDeclarationsIREmployes() {
		return declarationsIREmployes;
	}
	public void setDeclarationsIREmployes(List<DeclarationIREmploye> declarationsIREmployes) {
		this.declarationsIREmployes = declarationsIREmployes;
	}
	

	
	
	

}
