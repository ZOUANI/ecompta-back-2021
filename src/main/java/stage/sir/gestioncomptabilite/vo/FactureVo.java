package stage.sir.gestioncomptabilite.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class FactureVo {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dmax;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date dmin;

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
}