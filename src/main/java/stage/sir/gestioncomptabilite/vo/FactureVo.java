package stage.sir.gestioncomptabilite.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FactureVo {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dmax;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dmin;
    private String reference;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    private double totaldebit;
    private double totalcredit ;

    public Date getDmax() {
        return dmax;
    }

    public void setDmax(Date dmax) {
        this.dmax = dmax;
    }

    public Date getDmin() {
        return dmin;
    }

    public void setDmin(Date dmin) {
        this.dmin = dmin;
    }

    public double getTotaldebit() {
        return totaldebit;
    }

    public void setTotaldebit(double totaldebit) {
        this.totaldebit = totaldebit;
    }

    public double getTotalcredit() {
        return totalcredit;
    }

    public void setTotalcredit(double totalcredit) {
        this.totalcredit = totalcredit;
    }
}