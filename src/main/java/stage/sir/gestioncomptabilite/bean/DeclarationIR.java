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
public class DeclarationIR {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String ref;
	private Integer mois;
	private Integer annee;
	private Double total;
	@ManyToOne
	private Societe societe;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY )
	@OneToMany(mappedBy = "declarationIR")
	private List<DeclarationIREmploye> declarationsIREmployes;
	

	@Override
	public String toString() {
		return "DeclarationIR [id=" + id + ", ref=" + ref + ", mois=" + mois + ", annee=" + annee + ", total=" + total
				+ ", societe=" + societe + ", declarationsIREmployes=" + declarationsIREmployes + "]";
	}
	public List<DeclarationIREmploye> getDeclarationsIREmployes() {
		return declarationsIREmployes;
	}
	public void setDeclarationsIREmployes(List<DeclarationIREmploye> declarationsIREmployes) {
		this.declarationsIREmployes = declarationsIREmployes;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public Societe getSociete() {
		return societe;
	}
	public void setSociete(Societe societe) {
		this.societe = societe;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	
	

}
