package a44screens.arthylene.Objects;

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
