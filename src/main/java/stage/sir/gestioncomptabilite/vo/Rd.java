package stage.sir.gestioncomptabilite.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Rd {
    private double ord;
    private String num;
    private String des;
    private double mht;
    private double tva;
    private double ttc;
    private RefF refF;
    private double tx;
    private Mp mp;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dpai,dfac;

    public double getOrd() {
        return ord;
    }

    public void setOrd(double ord) {
        this.ord = ord;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public double getMht() {
        return mht;
    }

    public void setMht(double mht) {
        this.mht = mht;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getTtc() {
        return ttc;
    }

    public void setTtc(double ttc) {
        this.ttc = ttc;
    }

    public RefF getRefF() {
        return refF;
    }

    public void setRefF(RefF refF) {
        this.refF = refF;
    }

    public double getTx() {
        return tx;
    }

    public void setTx(double tx) {
        this.tx = tx;
    }

    public Mp getMp() {
        return mp;
    }

    public void setMp(Mp mp) {
        this.mp = mp;
    }

    public Date getDpai() {
        return dpai;
    }

    public void setDpai(Date dpai) {
        this.dpai = dpai;
    }

    public Date getDfac() {
        return dfac;
    }

    public void setDfac(Date dfac) {
        this.dfac = dfac;
    }
}
