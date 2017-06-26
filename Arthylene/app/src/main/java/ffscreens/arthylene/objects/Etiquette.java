package ffscreens.arthylene.objects;

/**
 * Created by Thibault on 21/06/2017.
 */

public class Etiquette {

    private Long idEtiquette;
    private String code;
    private Long idCagette;
    private Long idPhoto;
    private String nomProduit;
    private String VarieteProduit;
    private int ete;
    private int automne;
    private int hiver;
    private int printems;
    private int nbCouche;
    private int maturiteMin;
    private int maturiteMax;
    private int emplacementChariot;

    public Etiquette() {
    }

    public Etiquette(Long idEtiquette, String code, Long idCagette, Long idPhoto, String nomProduit, String varieteProduit, int ete, int automne, int hiver, int printems, int nbCouche, int maturiteMin, int maturiteMax, int emplacementChariot) {
        this.idEtiquette = idEtiquette;
        this.code = code;
        this.idCagette = idCagette;
        this.idPhoto = idPhoto;
        this.nomProduit = nomProduit;
        VarieteProduit = varieteProduit;
        this.ete = ete;
        this.automne = automne;
        this.hiver = hiver;
        this.printems = printems;
        this.nbCouche = nbCouche;
        this.maturiteMin = maturiteMin;
        this.maturiteMax = maturiteMax;
        this.emplacementChariot = emplacementChariot;
    }

    public String toString() {
        return "idEtiquette : " + getIdEtiquette() + ", code : " + getCode() + ", idCagette : "
                + getIdCagette() + ", idPhoto : " + getIdPhoto() + ", nomProduit : "
                + getNomProduit() + ", varieteProduit : " + getVarieteProduit() + ", ordreEte : "
                + getEte() + ", ordreAutomne : " + getAutomne() + ", ordreHiver : " + getHiver()
                + ", ordrePrintemps : " + getPrintems() + ", nombreDeCouche : " + getNbCouche()
                + ", maturiteMin : " + getMaturiteMin() + ", maturiteMax : " + getMaturiteMax()
                + ", emplacementChariot : " + getEmplacementChariot();
    }

    public Long getIdEtiquette() {
        return idEtiquette;
    }

    public void setIdEtiquette(Long idEtiquette) {
        this.idEtiquette = idEtiquette;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getIdCagette() {
        return idCagette;
    }

    public void setIdCagette(Long idCagette) {
        this.idCagette = idCagette;
    }

    public Long getIdPhoto() {
        return idPhoto;
    }

    public void setIdPhoto(Long idPhoto) {
        this.idPhoto = idPhoto;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getVarieteProduit() {
        return VarieteProduit;
    }

    public void setVarieteProduit(String varieteProduit) {
        VarieteProduit = varieteProduit;
    }

    public int getEte() {
        return ete;
    }

    public void setEte(int ete) {
        this.ete = ete;
    }

    public int getAutomne() {
        return automne;
    }

    public void setAutomne(int automne) {
        this.automne = automne;
    }

    public int getHiver() {
        return hiver;
    }

    public void setHiver(int hiver) {
        this.hiver = hiver;
    }

    public int getPrintems() {
        return printems;
    }

    public void setPrintems(int printems) {
        this.printems = printems;
    }

    public int getNbCouche() {
        return nbCouche;
    }

    public void setNbCouche(int nbCouche) {
        this.nbCouche = nbCouche;
    }

    public int getMaturiteMin() {
        return maturiteMin;
    }

    public void setMaturiteMin(int maturiteMin) {
        this.maturiteMin = maturiteMin;
    }

    public int getMaturiteMax() {
        return maturiteMax;
    }

    public void setMaturiteMax(int maturiteMax) {
        this.maturiteMax = maturiteMax;
    }

    public int getEmplacementChariot() {
        return emplacementChariot;
    }

    public void setEmplacementChariot(int emplacementChariot) {
        this.emplacementChariot = emplacementChariot;
    }
}
