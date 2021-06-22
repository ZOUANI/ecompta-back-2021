package stage.sir.gestioncomptabilite.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Cpc {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private double totalProdEx;
    private double totalChargEx;
    private double totalProdFin;
    private double totalChargFin;
    private double totalProdNCour;
    private double totalChargNCour;

    @OneToMany(mappedBy ="cpc")
    private List<CpcFacture> cpcFactures;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date dateMinCpc;

    public List<CpcFacture> getCpcFactures() {
        return cpcFactures;
    }

    public void setCpcFactures(List<CpcFacture> cpcFactures) {
        this.cpcFactures = cpcFactures;
    }

    public Date getDateMinCpc() {
        return dateMinCpc;
    }

    public void setDateMinCpc(Date dateCpc) {
        this.dateMinCpc = dateCpc;
    }



    public Date getDateMaxCpc() {
        return dateMaxCpc;
    }

    public void setDateMaxCpc(Date dateMaxCpc) {
        this.dateMaxCpc = dateMaxCpc;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private Date dateMaxCpc;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalProdEx() {
        return totalProdEx;
    }

    public void setTotalProdEx(double totalProdEx) {
        this.totalProdEx = totalProdEx;
    }

    public double getTotalChargEx() {
        return totalChargEx;
    }

    public void setTotalChargEx(double totalChargEx) {
        this.totalChargEx = totalChargEx;
    }

    public double getTotalProdFin() {
        return totalProdFin;
    }

    public void setTotalProdFin(double totalProdFin) {
        this.totalProdFin = totalProdFin;
    }

    public double getTotalChargFin() {
        return totalChargFin;
    }

    public void setTotalChargFin(double totalChargFin) {
        this.totalChargFin = totalChargFin;
    }

    public double getTotalProdNCour() {
        return totalProdNCour;
    }

    public void setTotalProdNCour(double totalProdNCour) {
        this.totalProdNCour = totalProdNCour;
    }

    public double getTotalChargNCour() {
        return totalChargNCour;
    }

    public void setTotalChargNCour(double totalChargNCour) {
        this.totalChargNCour = totalChargNCour;
    }

}
