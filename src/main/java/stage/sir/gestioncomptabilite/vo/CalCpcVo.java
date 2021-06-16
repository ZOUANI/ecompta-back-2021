package stage.sir.gestioncomptabilite.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CalCpcVo {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    Date datemin;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "YYYY-MM-dd")
    Date datemax;

    public Date getDatemin() {
        return datemin;
    }

    public void setDatemin(Date datemin) {
        this.datemin = datemin;
    }

    public Date getDatemax() {
        return datemax;
    }

    public void setDatemax(Date datemax) {
        this.datemax = datemax;
    }
}
