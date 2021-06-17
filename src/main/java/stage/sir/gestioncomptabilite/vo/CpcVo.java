package stage.sir.gestioncomptabilite.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CpcVo {
    private  Double totalProdExVo;
    private Double totalChargExVo;
    private Double totalProdFinVo;
    private Double totalChargFinVo;
    private Double totalProdNCourVo;
    private Double totalChargNCourVo;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private Date dateMinVo;

    public Date getDateMinVo() {
        return dateMinVo;
    }

    public void setDateMinVo(Date dateMinVo) {
        this.dateMinVo = dateMinVo;
    }

    public Date getDateMaxVo() {
        return dateMaxVo;
    }

    public void setDateMaxVo(Date dateMaxVo) {
        this.dateMaxVo = dateMaxVo;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    private Date dateMaxVo;

    public Double getTotalProdExVo() {
        return totalProdExVo;
    }

    public void setTotalProdExVo(Double totalProdExVo) {
        this.totalProdExVo = totalProdExVo;
    }

    public Double getTotalChargExVo() {
        return totalChargExVo;
    }

    public void setTotalChargExVo(Double totalChargExVo) {
        this.totalChargExVo = totalChargExVo;
    }

    public Double getTotalProdFinVo() {
        return totalProdFinVo;
    }

    public void setTotalProdFinVo(Double totalProdFinVo) {
        this.totalProdFinVo = totalProdFinVo;
    }

    public Double getTotalChargFinVo() {
        return totalChargFinVo;
    }

    public void setTotalChargFinVo(Double totalChargFinVo) {
        this.totalChargFinVo = totalChargFinVo;
    }

    public Double getTotalProdNCourVo() {
        return totalProdNCourVo;
    }

    public void setTotalProdNCourVo(Double totalProdNCourVo) {
        this.totalProdNCourVo = totalProdNCourVo;
    }

    public Double getTotalChargNCourVo() {
        return totalChargNCourVo;
    }

    public void setTotalChargNCourVo(Double totalChargNCourVo) {
        this.totalChargNCourVo = totalChargNCourVo;
    }
}
