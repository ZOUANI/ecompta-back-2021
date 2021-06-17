package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

/*
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String ref;
    private String libelle;
    private double montantHorsTaxe;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private Date dateOperation;
    private double annee;
    private double mois;
    private double trim;
    private double montantTTC;
    private double montantTVA;
    private String typeOperation;
    @ManyToOne
    private Tva tva;
    @ManyToOne
    private Societe societeSource;
    @ManyToOne
    private Societe societeDistination;
    @ManyToOne
    private CompteComptable compteComptable;
    @ManyToOne
    private DeclarationTva declarationTva;
    @ManyToOne
    private DeclarationIR declarationIR;
    @ManyToOne
    private DeclarationIS declarationIS;

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

    public CompteComptable getCompteComptable() {
        return compteComptable;
    }

    public void setCompteComptable(CompteComptable compteComptable) {
        this.compteComptable = compteComptable;
    }

    public DeclarationTva getDeclarationTva() {
        return declarationTva;
    }

    public void setDeclarationTva(DeclarationTva declarationTva) {
        this.declarationTva = declarationTva;
    }

    public DeclarationIR getDeclarationIR() {
        return declarationIR;
    }

    public void setDeclarationIR(DeclarationIR declarationIR) {
        this.declarationIR = declarationIR;
    }

    public DeclarationIS getDeclarationIS() {
        return declarationIS;
    }

    public void setDeclarationIS(DeclarationIS declarationIS) {
        this.declarationIS = declarationIS;
    }
}*/
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private String credit ;
    private String debit;
    @ManyToOne
    private Tva tva;
    @ManyToOne
    private EtatFacture etatFacture;
    @ManyToOne
    private EtatPaiement etatPaiement;
    @ManyToOne
    private Societe societeSource;
    @ManyToOne
    private Societe societeDistination;
    @ManyToOne
    private CompteComptable compteComptable;
    @ManyToOne
    private DeclarationTva declarationTva;
    @ManyToOne
    private DeclarationIR declarationIR;
    @ManyToOne
    private DeclarationIS declarationIS;

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

    public CompteComptable getCompteComptable() {
        return compteComptable;
    }

    public void setCompteComptable(CompteComptable compteComptable) {
        this.compteComptable = compteComptable;
    }

    public DeclarationTva getDeclarationTva() {
        return declarationTva;
    }

    public void setDeclarationTva(DeclarationTva declarationTva) {
        this.declarationTva = declarationTva;
    }

    public DeclarationIR getDeclarationIR() {
        return declarationIR;
    }

    public void setDeclarationIR(DeclarationIR declarationIR) {
        this.declarationIR = declarationIR;
    }

    public DeclarationIS getDeclarationIS() {
        return declarationIS;
    }

    public void setDeclarationIS(DeclarationIS declarationIS) {
        this.declarationIS = declarationIS;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
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
}