package stage.sir.gestioncomptabilite.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import stage.sir.gestioncomptabilite.bean.*;

import java.util.Date;

public class FactureXml {
    private Long id;
    private String ref;
    private String libelle;
    private double montantHorsTaxe;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dateOperation;
    private double annee;
    private double mois;
    private double trim;
    private double montantTTC;
    private double montantTVA;
    private String typeOperation;
    private Tva tva;
    private EtatFacture etatFacture;
    private EtatPaiement etatPaiement;
    private Societe societeSource;
    private Societe societeDistination;


    public FactureXml convertToFactXml(Facture facture){
        FactureXml factureXml = new FactureXml();
        factureXml.setId(facture.getId());
        factureXml.setRef(facture.getRef());
        factureXml.setLibelle(facture.getLibelle());
        factureXml.setMontantHorsTaxe(facture.getMontantHorsTaxe());
        factureXml.setDateOperation(facture.getDateOperation());
        factureXml.setAnnee(facture.getAnnee());
        factureXml.setMois(facture.getMois());
        factureXml.setTrim(facture.getTrim());
        factureXml.setMontantTTC(facture.getMontantTTC());
        factureXml.setMontantTVA(facture.getMontantTVA());
        factureXml.setTypeOperation(facture.getTypeOperation());
        factureXml.setTva(facture.getTva());
        factureXml.setEtatFacture(facture.getEtatFacture());
        factureXml.setEtatPaiement(facture.getEtatPaiement());
        factureXml.setSocieteSource(facture.getSocieteSource());
        factureXml.setSocieteDistination(facture.getSocieteDistination());
        return factureXml;
    }


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

    public double getMontantHorsTaxe() {
        return montantHorsTaxe;
    }

    public void setMontantHorsTaxe(double montantHorsTaxe) {
        this.montantHorsTaxe = montantHorsTaxe;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public double getAnnee() {
        return annee;
    }

    public void setAnnee(double annee) {
        this.annee = annee;
    }

    public double getMois() {
        return mois;
    }

    public void setMois(double mois) {
        this.mois = mois;
    }

    public double getTrim() {
        return trim;
    }

    public void setTrim(double trim) {
        this.trim = trim;
    }

    public double getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        this.montantTTC = montantTTC;
    }

    public double getMontantTVA() {
        return montantTVA;
    }

    public void setMontantTVA(double montantTVA) {
        this.montantTVA = montantTVA;
    }

    public String getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(String typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Tva getTva() {
        return tva;
    }

    public void setTva(Tva tva) {
        this.tva = tva;
    }

    public EtatFacture getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(EtatFacture etatFacture) {
        this.etatFacture = etatFacture;
    }

    public EtatPaiement getEtatPaiement() {
        return etatPaiement;
    }

    public void setEtatPaiement(EtatPaiement etatPaiement) {
        this.etatPaiement = etatPaiement;
    }

    public Societe getSocieteSource() {
        return societeSource;
    }

    public void setSocieteSource(Societe societeSource) {
        this.societeSource = societeSource;
    }

    public Societe getSocieteDistination() {
        return societeDistination;
    }

    public void setSocieteDistination(Societe societeDistination) {
        this.societeDistination = societeDistination;
    }

   
}
