package stage.sir.gestioncomptabilite.vo;

import stage.sir.gestioncomptabilite.bean.Facture;

import java.util.ArrayList;
import java.util.List;

public class DeclarationTvaVo2 {
    private List<Facture> listfacturevente = new ArrayList<Facture>();
    private List<Facture> listfactureachat = new ArrayList<Facture>();
    private double tvacollecter;
    private double tvadeductible;
    private double differencetva;

    public List<Facture> getListfacturevente() {
        return listfacturevente;
    }

    public void setListfacturevente(List<Facture> listfacturevente) {
        this.listfacturevente = listfacturevente;
    }

    public List<Facture> getListfactureachat() {
        return listfactureachat;
    }

    public void setListfactureachat(List<Facture> listfactureachat) {
        this.listfactureachat = listfactureachat;
    }

    public double getTvacollecter() {
        return tvacollecter;
    }

    public void setTvacollecter(double tvacollecter) {
        this.tvacollecter = tvacollecter;
    }

    public double getTvadeductible() {
        return tvadeductible;
    }

    public void setTvadeductible(double tvadeductible) {
        this.tvadeductible = tvadeductible;
    }

    public double getDifferencetva() {
        return differencetva;
    }

    public void setDifferencetva(double differencetva) {
        this.differencetva = differencetva;
    }
}
