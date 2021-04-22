package stage.sir.gestioncomptabilite.bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class TauxIr {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double salaireMin;
	private Double salaireMax;
	private Double pourcentage;
	@OneToMany(mappedBy = "tauxIr")
	private List<Details> detailsTauxDetails; 
	
	
	
	
	
	
	public List<Details> getDetailsTauxDetails() {
		return detailsTauxDetails;
	}
	public void setDetailsTauxDetails(List<Details> detailsTauxDetails) {
		this.detailsTauxDetails = detailsTauxDetails;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getSalaireMin() {
		return salaireMin;
	}
	public void setSalaireMin(Double salaireMin) {
		this.salaireMin = salaireMin;
	}
	public Double getSalaireMax() {
		return salaireMax;
	}
	public void setSalaireMax(Double salaireMax) {
		this.salaireMax = salaireMax;
	}
	public Double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(Double pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	
	

}
