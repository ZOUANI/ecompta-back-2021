package stage.sir.gestioncomptabilite.vo;

public class DeclarationTvaCriteria {
    private String societeref;
    private String annee;
    private String mois;
    private String trim;
    private String difftvamin;
    private String difftvamax;
    private String typedeclarationtva;

    public String getSocieteref() {
        return societeref;
    }

    public void setSocieteref(String societeref) {
        this.societeref = societeref;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public String getDifftvamin() {
        return difftvamin;
    }

    public void setDifftvamin(String difftvamin) {
        this.difftvamin = difftvamin;
    }

    public String getDifftvamax() {
        return difftvamax;
    }

    public void setDifftvamax(String difftvamax) {
        this.difftvamax = difftvamax;
    }

    public String getTypedeclarationtva() {
        return typedeclarationtva;
    }

    public void setTypedeclarationtva(String typedeclarationtva) {
        this.typedeclarationtva = typedeclarationtva;
    }
}
