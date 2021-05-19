package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class OperationSociete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private String libelle;
    private double fraixFix ;
    private double fraixComptable ;
    @ManyToOne
    private TypeOperation typeOperation  ;
    @ManyToOne
    private EtatOperationSociete etatOperationSociete;
    @ManyToOne
    Comptable comptableTaiteur,comptableValidateur;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOperationSociete;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "operationSociete")
    private List<OperationSocieteJustif> operationSocieteJustifs;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToMany(mappedBy = "operationSociete")
    private List<Paiement> paiements;

    @ManyToOne
    private Societe societe;

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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getFraixFix() {
        return fraixFix;
    }

    public void setFraixFix(double fraixFix) {
        this.fraixFix = fraixFix;
    }

    public double getFraixComptable() {
        return fraixComptable;
    }

    public void setFraixComptable(double fraixComptable) {
        this.fraixComptable = fraixComptable;
    }

    public TypeOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(TypeOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public EtatOperationSociete getEtatOperationSociete() {
        return etatOperationSociete;
    }

    public void setEtatOperationSociete(EtatOperationSociete etatOperationSociete) {
        this.etatOperationSociete = etatOperationSociete;
    }

    public Comptable getComptableTaiteur() {
        return comptableTaiteur;
    }

    public void setComptableTaiteur(Comptable comptableTaiteur) {
        this.comptableTaiteur = comptableTaiteur;
    }

    public Comptable getComptableValidateur() {
        return comptableValidateur;
    }

    public void setComptableValidateur(Comptable comptableValidateur) {
        this.comptableValidateur = comptableValidateur;
    }

    public Date getDateOperationSociete() {
        return dateOperationSociete;
    }

    public void setDateOperationSociete(Date dateOperationSociete) {
        this.dateOperationSociete = dateOperationSociete;
    }

    public List<OperationSocieteJustif> getOperationSocieteJustifs() {
        return operationSocieteJustifs;
    }

    public void setOperationSocieteJustifs(List<OperationSocieteJustif> operationSocieteJustifs) {
        this.operationSocieteJustifs = operationSocieteJustifs;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }
}
